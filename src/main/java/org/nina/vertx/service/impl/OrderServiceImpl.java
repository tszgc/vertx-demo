package org.nina.vertx.service.impl;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.sqlclient.SqlConnection;
import org.nina.vertx.dao.IOrderDao;
import org.nina.vertx.dao.impl.OrderDaoImpl;
import org.nina.vertx.service.IOrderService;
import org.nina.vertx.util.SqlUtil;

import java.util.List;

/**
 * 描述：订单service实现类
 * 作者：zgc
 * 时间：2022/7/7 23:26
 */
public class OrderServiceImpl implements IOrderService {

    private IOrderDao orderDao = new OrderDaoImpl();

    @Override
    public Future<List<Integer>> queryNoReportId() {
        Promise<List<Integer>> promise = Promise.promise();
        SqlUtil.pool().getConnection(as -> {
            if (as.succeeded()) {
                SqlConnection sqlConnection = as.result();
                orderDao.selectNoReportId(sqlConnection)
                        .onSuccess(ids -> {
                            promise.complete(ids);
                            sqlConnection.close();
                        })
                        .onFailure(throwable -> {
                           promise.fail(throwable);
                           sqlConnection.close();
                        });
            } else {
                promise.fail(as.cause());
            }
        });
        return promise.future();
    }
}
