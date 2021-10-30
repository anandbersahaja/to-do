package com.bersahaja.todo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public enum Status {
  BELUM,
  DIKERJAKAN,
  SELESAI;

  public static Status convert(String status) throws SQLException {
    if(status.equalsIgnoreCase("selesai") ||
            status.equalsIgnoreCase("sudah")){
      return Status.SELESAI;
    }

    else if(status.equalsIgnoreCase("dikerjakan") ||
            status.equalsIgnoreCase("dikerjaain")){
      return Status.DIKERJAKAN;
    }

    else {
      return Status.BELUM;
    }
  }
}
