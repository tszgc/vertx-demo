package org.nina.vertx.dao.impl;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlConnection;
import org.apache.commons.lang3.StringUtils;
import org.nina.vertx.dao.IOrderDetailDao;
import org.nina.vertx.dto.ReportOrderDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：订单详情dao实现类
 * 作者：zgc
 * 时间：2022/7/7 21:53
 */
public class OrderDetailDaoImpl implements IOrderDetailDao {

    @Override
    public Future<List<ReportOrderDto>> selectReportOrderByOrderIds(SqlConnection sqlConnection, List<Integer> orderIds) {
        Promise<List<ReportOrderDto>> promise = Promise.promise();
        String sql = "select d.order_id as orderId, sum(d.price) as totalAmount, sum(d.num) as totalNum from t_order_detail d where d.order_id in (" +
                StringUtils.join(orderIds, ",") +
                ") group by d.order_id";
        System.out.println(sql);
        sqlConnection.preparedQuery(sql)
                .execute(ar -> {
                    if (ar.succeeded()) {
                        RowSet<Row> rows = ar.result();
                        List<ReportOrderDto> list = new ArrayList<>(rows.size());
                        for (Row row : rows) {
                            list.add(new ReportOrderDto(row));
                        }
                        promise.complete(list);
                    } else {
                        promise.fail(ar.cause());
                    }
                });
        return promise.future();
    }
}
