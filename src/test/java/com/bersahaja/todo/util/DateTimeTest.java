package com.bersahaja.todo.util;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;


public class DateTimeTest {
  @Test
  void testDateTime() {
    LocalDateTime dateTime = LocalDateTime.now();
    System.out.println(dateTime.toString());

    String pattern = "YYYY-mmm-dd HH:mm:ss";

    LocalDateTime parse = LocalDateTime.parse("2021-10-29T21:19:38.647883");
    System.out.println(parse.toString());

    LocalDate localDate = LocalDate.parse("2021-10-10");
    LocalTime localTime = LocalTime.parse("22:20:30");
    LocalDateTime of = LocalDateTime.of(localDate, localTime);
    System.out.println(of);

  }

  @Test
  void testClock() {
    var ld = LocalDate.now(Clock.system(ZoneId.of("Asia/Jakarta")));
    var lt = LocalTime.now(Clock.system(ZoneId.of("UTC")));

    System.out.println(lt);

    LocalDate ld1 = ld.plusDays(2);

    System.out.println(ld);
    System.out.println(ld1);

  }
}
