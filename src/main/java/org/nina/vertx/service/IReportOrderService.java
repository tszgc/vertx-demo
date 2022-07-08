package org.nina.vertx.service;

import io.vertx.core.Future;
import org.nina.vertx.model.ReportOrder;

import java.util.List;

public interface IReportOrderService {

    Future<Integer> saveBatch(List<ReportOrder> reportOrderList);

}
