package org.nina.vertx.dao;

import io.vertx.core.Future;
import io.vertx.sqlclient.SqlConnection;
import org.nina.vertx.dto.ReportOrderDto;

import java.util.List;

/**
 * 描述：订单详情表dao接口
 * 作者：zgc
 * 时间：2022/7/7 21:44
 */
public interface IOrderDetailDao {
    Future<List<ReportOrderDto>> selectReportOrderByOrderIds(SqlConnection sqlConnection, List<Integer> orderIds);
}
