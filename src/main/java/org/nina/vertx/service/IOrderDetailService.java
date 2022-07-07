package org.nina.vertx.service;

import io.vertx.core.Future;
import org.nina.vertx.dto.ReportOrderDto;

import java.util.List;

/**
 * 描述：订单详情service接口
 * 作者：zgc
 * 时间：2022/7/7 23:47
 */
public interface IOrderDetailService {
    Future<List<ReportOrderDto>> selectReportOrderByOrderIds(List<Integer> orderIds);
}
