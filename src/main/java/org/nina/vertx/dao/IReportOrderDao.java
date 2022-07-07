package org.nina.vertx.dao;

import io.vertx.core.Future;
import io.vertx.sqlclient.SqlConnection;
import org.nina.vertx.model.ReportOrder;

import java.util.List;

/**
 * 描述：以订单为维度的统计报表dao接口
 * 作者：zgc
 * 时间：2022/7/7 22:14
 */
public interface IReportOrderDao {

    Future<Integer> insert(SqlConnection sqlConnection, ReportOrder reportOrder);

    Future<Integer> insertBatch(SqlConnection sqlConnection, List<ReportOrder> reportOrderList);

    Future<ReportOrder> selectByOrderId(SqlConnection sqlConnection, Integer orderId);
}
