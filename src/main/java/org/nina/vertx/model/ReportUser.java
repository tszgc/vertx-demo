package org.nina.vertx.model;

import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportUser {

    private Integer id;

    private Integer userId;

    private Integer totalNum;

    private BigDecimal totalAmount;

    public ReportUser() {}

    public ReportUser(Row row) {
        this.id = row.getInteger("id");
        this.userId = row.getInteger("userId");
        this.totalNum = row.getInteger("totalNum");
        this.totalAmount = row.getBigDecimal("totalAmount");
    }

    public JsonObject toJson() {
        return new JsonObject().put("id", this.id)
                .put("userId", this.userId)
                .put("totalNum", this.totalNum)
                .put("totalAmount", this.totalAmount);
    }

}
