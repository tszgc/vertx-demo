package org.nina.vertx.service.impl;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.sqlclient.SqlConnection;
import org.nina.vertx.dao.IOrderDetailDao;
import org.nina.vertx.dao.impl.OrderDetailDaoImpl;
import org.nina.vertx.dto.ReportOrderDto;
import org.nina.vertx.service.IOrderDetailService;
import org.nina.vertx.util.SqlUtil;

import java.util.List;

/**
 * 描述：订单详情service实现类
 * 作者：zgc
 * 时间：2022/7/7 23:50
 */
public class OrderDetailServiceImpl implements IOrderDetailService {

    private IOrderDetailDao orderDetailDao = new OrderDetailDaoImpl();

    @Override
    public Future<List<ReportOrderDto>> selectReportOrderByOrderIds(List<Integer> orderIds) {
        Promise<List<ReportOrderDto>> promise = Promise.promise();
        SqlUtil.pool().getConnection(ar -> {
            if (ar.succeeded()) {
                SqlConnection sqlConnection = ar.result();
                orderDetailDao.selectReportOrderByOrderIds(sqlConnection, orderIds)
                        .onSuccess(reportOrderDtos -> {
                            promise.complete(reportOrderDtos);
                            sqlConnection.close();
                        })
                        .onFailure(promise::fail);
            } else {
                promise.fail(ar.cause());
            }
        });
        return promise.future();
    }
}
