package org.nina.vertx.dto;

import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nina.vertx.model.ReportOrder;

import java.math.BigDecimal;

/**
 * 描述：以订单为维度统计类
 * 作者：zgc
 * 时间：2022/7/7 21:47
 */
@NoArgsConstructor
@Data
public class ReportOrderDto {

    private Integer orderId;
    private BigDecimal totalAmount;
    private Integer totalNum;

    public ReportOrderDto(Row row) {
        this.orderId = row.getInteger("orderId");
        this.totalAmount = row.getBigDecimal("totalAmount");
        this.totalNum = row.getInteger("totalNum");
    }

    public JsonObject toJson() {
        return new JsonObject().put("orderId", this.orderId)
                .put("totalAmount", this.totalAmount)
                .put("totalNum", this.totalNum);
    }

    public ReportOrder toReportOrder() {
        ReportOrder reportOrder = new ReportOrder();
        reportOrder.setOrderId(this.getOrderId());
        reportOrder.setTotalAmount(this.getTotalAmount());
        reportOrder.setTotalNum(this.getTotalNum());
        return reportOrder;
    }
}
