package org.nina.vertx.model;

import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetail {

    private Integer id;

    private Integer orderId;

    private BigDecimal price;

    private Integer num;

    private Integer goodsId;

    public OrderDetail() {}

    public OrderDetail(Row row) {
        this.id = row.getInteger("id");
        this.orderId = row.getInteger("orderId");
        this.price = row.getBigDecimal("price");
        this.num = row.getInteger("num");
        this.goodsId = row.getInteger("goodsId");
    }

    public JsonObject toJson() {
        return new JsonObject().put("id", this.id)
                .put("orderId", this.orderId)
                .put("price", price)
                .put("num", num)
                .put("goodsId", goodsId);
    }

}
