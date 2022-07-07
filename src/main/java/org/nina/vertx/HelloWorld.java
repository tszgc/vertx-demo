package org.nina.vertx;

import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class HelloWorld {

    public static void main(String[] args) {
        MySQLConnectOptions connectOptions = new MySQLConnectOptions()
            .setPort(3306)
                .setHost("localhost")
                .setDatabase("vertx")
                .setUser("root")
                .setPassword("root");

        PoolOptions poolOptions = new PoolOptions().setMaxSize(5);

        SqlClient client = MySQLPool.client(connectOptions, poolOptions);

        // 从t_order表中获取
        List<Integer> list = selectUnReportOrderId(client);
        System.out.println(list.size());

    }

    public static List<Integer> selectUnReportOrderId(SqlClient client) {
        List<Integer> list = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);
        client.query("select id from t_order o where o.is_report=0")
                .execute(ar -> {
                    if (ar.succeeded()) {
                        RowSet<Row> rows = ar.result();
                        for (Row row : rows) {
                            Integer id = row.getInteger("id");
                            System.out.println("orderId " + id);
                            list.add(id);
                        }
                    } else {
                        System.out.println("Failure: " + ar.cause().getMessage());
                    }
                    latch.countDown();
                });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }


}
