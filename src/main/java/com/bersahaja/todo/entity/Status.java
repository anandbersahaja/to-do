package com.bersahaja.todo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public enum Status {
  BELUM,
  DIKERJAKAN,
  SELESAI;

  public static Status convert(ResultSet resultSet) throws SQLException {
    String status = resultSet.getString("status");
    if(status.equalsIgnoreCase("belum")){
      return Status.BELUM;
    }else if(status.equalsIgnoreCase("dikerjakan")){
      return Status.DIKERJAKAN;
    }else {
      return Status.SELESAI;
    }
  }
}
