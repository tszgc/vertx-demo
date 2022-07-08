package org.nina.vertx.service.impl;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.sqlclient.SqlConnection;
import org.nina.vertx.dao.IReportOrderDao;
import org.nina.vertx.dao.impl.ReportOrderDaoImpl;
import org.nina.vertx.model.ReportOrder;
import org.nina.vertx.service.IReportOrderService;
import org.nina.vertx.util.SqlUtil;

import java.util.List;

public class ReportOrderServiceImpl implements IReportOrderService {

    private IReportOrderDao reportOrderDao = new ReportOrderDaoImpl();

    @Override
    public Future<Integer> saveBatch(List<ReportOrder> reportOrderList) {

        Promise<Integer> promise = Promise.promise();
        SqlUtil.pool().getConnection(ar -> {
            if (ar.succeeded()) {
                SqlConnection sqlConnection = ar.result();
                reportOrderDao.insertBatch(sqlConnection, reportOrderList)
                        .onSuccess(lastId -> {
                            promise.complete(lastId);
                            sqlConnection.close();
                        })
                        .onFailure(throwable -> {
                            promise.fail(throwable);
                            sqlConnection.close();
                        });
            } else {
                promise.fail(ar.cause());
            }
        });
        return promise.future();
    }

}
