package org.nina.vertx;

import io.vertx.core.Future;
import org.nina.vertx.dto.ReportOrderDto;
import org.nina.vertx.model.ReportOrder;
import org.nina.vertx.service.IOrderDetailService;
import org.nina.vertx.service.IOrderService;
import org.nina.vertx.service.IReportOrderService;
import org.nina.vertx.service.impl.OrderDetailServiceImpl;
import org.nina.vertx.service.impl.OrderServiceImpl;
import org.nina.vertx.service.impl.ReportOrderServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.*;

/**
 * 描述：启动类
 * 作者：zgc
 * 时间：2022/7/7 22:36
 */
public class App {

    public static void main(String[] args) {
        IOrderService orderService = new OrderServiceImpl();
        IOrderDetailService orderDetailService = new OrderDetailServiceImpl();
        IReportOrderService reportOrderService = new ReportOrderServiceImpl();

        Future<List<Integer>> queryNoReportIdFuture = orderService.queryNoReportId();
        queryNoReportIdFuture.compose(orderDetailService::selectReportOrderByOrderIds)
                .compose(reportOrderDtos -> reportOrderService.saveBatch(toReportOrderList(reportOrderDtos)))
                .onSuccess(out::println)
                .onFailure(out::println);

    }

    private static List<ReportOrder> toReportOrderList(List<ReportOrderDto> reportOrderDtos) {
        return reportOrderDtos.stream().map(ReportOrderDto::toReportOrder).collect(Collectors.toList());
    }
}
