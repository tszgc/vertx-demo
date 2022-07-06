package org.nina.vertx;

import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlClient;

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

        client.query("select * from t_order")
                .execute(ar -> {
                    if (ar.succeeded()) {
                        RowSet<Row> rows = ar.result();
                        for (Row row : rows) {
                            System.out.println("Order " + row.getInteger(0) + " " + row.getInteger(1) + " " + row.getLocalDateTime(2));
                        }
                    } else {
                        System.out.println("Failure: " + ar.cause().getMessage());
                    }
                    client.close();
                });

    }


}
