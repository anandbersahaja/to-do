package com.bersahaja.todo.util;

import com.bersahaja.todo.entity.Status;

import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Scan {
  public static void main(String[] args) {
    try {
      var date = getTime("DUE TIME");
      System.out.println(date);
    }catch (Exception e){
      System.out.println(e.getMessage());
//      e.printStackTrace();
    }
  }

  public static String getString(String info){
    Scanner input = new Scanner(System.in);
    System.out.print(info +"\t: ");
    return input.nextLine();
  }

  public static Status getStatus(String info) throws SQLException {
    return  Status.convert(getString(info));
  }

  public static Integer getInteger(String info) throws IllegalArgumentException{
      return Integer.valueOf(getString(info));
  }

  public static LocalDate getDate(String info) throws DateTimeParseException {
    return LocalDate.parse(getString(info));
  }

  public static LocalTime getTime(String info) throws DateTimeParseException {
    return LocalTime.parse(getString(info));
  }

  public static LocalDateTime getDL(String info) throws DateTimeParseException {
    Integer days = getInteger(info);
    var tempDate = LocalDate.now(Clock.system(ZoneId.of("Asia/Jakarta")));
    var lt = LocalTime.parse("00:00:00");

    return tempDate.plusDays(days).atTime(lt);
  }
}
