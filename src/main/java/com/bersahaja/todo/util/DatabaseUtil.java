package com.bersahaja.todo.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseUtil {
  private static final HikariDataSource dataSource;

  public static HikariDataSource getDataSource() {
    return dataSource;
  }

  static {
    HikariConfig config = new HikariConfig();
    config.setDriverClassName("com.mysql.cj.jdbc.Driver");
    config.setUsername("root");
    config.setPassword("");
    config.setJdbcUrl("jdbc:mysql://localhost:3306/todo");

    // pool
    config.setMaximumPoolSize(10);
    config.setMinimumIdle(5);
    config.setIdleTimeout(60_000);
    config.setMaxLifetime(60 * 60 * 1000);

    dataSource = new HikariDataSource(config);
  }
}
