package com.bersahaja.todo.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


public class DateTimeTest {
  @Test
  void testDateTime() {
    LocalDateTime dateTime = LocalDateTime.now();
    System.out.println(dateTime.toString());
  }
}
