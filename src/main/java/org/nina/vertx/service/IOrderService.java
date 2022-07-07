package org.nina.vertx.service;

import io.vertx.core.Future;

import java.util.List;

/**
 * 描述：订单service接口
 * 作者：zgc
 * 时间：2022/7/7 23:25
 */
public interface IOrderService {

    Future<List<Integer>> queryNoReportId();

}
