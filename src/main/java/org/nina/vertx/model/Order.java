package org.nina.vertx.model;

import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Order {

    private Integer id;

    private Integer userId;

    private LocalDateTime createTime;

    private Integer isReport;

    public Order() {}

    public Order(Row row) {
        this.id = row.getInteger("id");
        this.userId = row.getInteger("userId");
        this.createTime = row.getLocalDateTime("createTime");
        this.isReport = row.getInteger("isReport");
    }

    public JsonObject toJson() {
        return new JsonObject().put("id", this.id)
                .put("title", this.userId)
                .put("createTime", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(createTime))
                .put("isReport", isReport);
    }
}
