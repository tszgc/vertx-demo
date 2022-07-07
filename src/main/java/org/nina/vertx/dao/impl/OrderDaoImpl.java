package org.nina.vertx.dao.impl;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlConnection;
import org.nina.vertx.dao.IOrderDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：订单dao实现类
 * 作者：zgc
 * 时间：2022/7/7 21:36
 */
public class OrderDaoImpl implements IOrderDao {
    @Override
    public Future<List<Integer>> selectNoReportId(SqlConnection sqlConnection) {
        Promise<List<Integer>> promise = Promise.promise();
        String sql = "select id from t_order o where o.is_report=0";
        sqlConnection.preparedQuery(sql)
                .execute(ar -> {
                    if (ar.succeeded()) {
                        List<Integer> list = new ArrayList<>();
                        RowSet<Row> rows = ar.result();
                        for (Row row : rows) {
                            list.add(row.getInteger("id"));
                        }
                        promise.complete(list);
                    }
                });
        return promise.future();
    }
}
