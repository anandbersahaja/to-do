package com.bersahaja.todo.util;

import com.bersahaja.todo.entity.Status;
import com.bersahaja.todo.entity.Todo;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

public class ConnectionTest {

  @Test
  void testConnection() {
    HikariDataSource dataSource = DatabaseUtil.getDataSource();
  }

}
