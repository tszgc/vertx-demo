package org.nina.vertx;

import org.nina.vertx.service.IOrderDetailService;
import org.nina.vertx.service.IOrderService;
import org.nina.vertx.service.impl.OrderDetailServiceImpl;
import org.nina.vertx.service.impl.OrderServiceImpl;

/**
 * 描述：启动类
 * 作者：zgc
 * 时间：2022/7/7 22:36
 */
public class App {

    public static void main(String[] args) {
        IOrderService orderService = new OrderServiceImpl();
        IOrderDetailService orderDetailService = new OrderDetailServiceImpl();

        orderService.queryNoReportId()
                .onSuccess(ids -> {
                    orderDetailService.selectReportOrderByOrderIds(ids)
                            .onSuccess(reportOrderDtos -> {
                                reportOrderDtos.forEach(System.out::println);
                            })
                            .onFailure(throwable -> {});
                })
                .onFailure(throwable -> {});

    }
}
