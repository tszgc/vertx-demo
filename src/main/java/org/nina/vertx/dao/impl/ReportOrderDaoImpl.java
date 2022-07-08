package org.nina.vertx.dao.impl;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.mysqlclient.MySQLClient;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.Tuple;
import org.nina.vertx.dao.IReportOrderDao;
import org.nina.vertx.model.ReportOrder;

import java.util.ArrayList;
import java.util.List;


public class ReportOrderDaoImpl implements IReportOrderDao {

    @Override
    public Future<Integer> insert(SqlConnection sqlConnection, ReportOrder reportOrder) {
        Promise<Integer> promise = Promise.promise();
        String sql = "insert into t_report_order (order_id, total_num, total_amount) values (?, ?, ?)";
        Tuple params = Tuple.of(reportOrder.getOrderId(), reportOrder.getTotalNum(), reportOrder.getTotalAmount());
        sqlConnection.preparedQuery(sql)
                .execute(params, ar -> {
                    if (ar.succeeded()) {
                        RowSet<Row> rows = ar.result();
                        Long lastInsertId = rows.property(MySQLClient.LAST_INSERTED_ID);
                        promise.complete(lastInsertId.intValue());
                    } else {
                        promise.fail(ar.cause());
                    }
                });
        return promise.future();
    }

    @Override
    public Future<Integer> insertBatch(SqlConnection sqlConnection, List<ReportOrder> reportOrderList) {
        Promise<Integer> promise = Promise.promise();
        String sql = "insert into t_report_order (order_id, total_num, total_amount) values (?, ?, ?)";
        List<Tuple> batch = new ArrayList<>(reportOrderList.size());
        reportOrderList.forEach(reportOrder -> batch.add(Tuple.of(reportOrder.getOrderId(), reportOrder.getTotalNum(), reportOrder.getTotalAmount())));
        sqlConnection.preparedQuery(sql)
                .executeBatch(batch, ar-> {
                    if (ar.succeeded()) {
                        RowSet<Row> rows = ar.result();
                        for (Row row : rows) {
                            System.out.println(row.toJson().toString());
                        }
                        Long lastInsertId = rows.property(MySQLClient.LAST_INSERTED_ID);
                        promise.complete(lastInsertId.intValue());
                    } else {
                        promise.fail(ar.cause());
                    }
                });
        return promise.future();
    }

    @Override
    public Future<ReportOrder> selectByOrderId(SqlConnection sqlConnection, Integer orderId) {
        return null;
    }
}
