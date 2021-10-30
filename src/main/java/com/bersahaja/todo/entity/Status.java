package com.bersahaja.todo.entity;

import java.sql.SQLException;

public enum Status {
  BELUM,
  DIKERJAKAN,
  SUDAH;

  public static Status convert(String status) throws SQLException {
    if(status.equalsIgnoreCase("selesai") ||
            status.equalsIgnoreCase("sudah")){
      return Status.SUDAH;
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
