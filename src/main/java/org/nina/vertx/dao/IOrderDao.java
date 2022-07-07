package org.nina.vertx.dao;

import io.vertx.core.Future;
import io.vertx.sqlclient.SqlConnection;

import java.util.List;

/**
 * 描述：订单dao
 * 作者：zgc
 * 时间：2022/7/7 21:35
 */
public interface IOrderDao {

    Future<List<Integer>> selectNoReportId(SqlConnection sqlConnection);

}
