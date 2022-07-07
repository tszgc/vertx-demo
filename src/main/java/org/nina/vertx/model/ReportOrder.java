package org.nina.vertx.model;

import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportOrder {

    private Integer id;

    private Integer orderId;

    private Integer totalNum;

    private BigDecimal totalAmount;

    public ReportOrder() {}

    public ReportOrder(Row row) {
        this.id = row.getInteger("id");
        this.orderId = row.getInteger("orderId");
        this.totalNum = row.getInteger("totalNum");
        this.totalAmount = row.getBigDecimal("totalAmount");
    }

    public JsonObject toJson() {
        return new JsonObject().put("id", this.id)
                .put("orderId", this.orderId)
                .put("totalNum", this.totalNum)
                .put("totalAmount", this.totalAmount);
    }

}
