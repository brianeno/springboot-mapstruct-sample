package com.brianeno.mapstruct.mapper;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface BaseMapper {

  default Timestamp map(String dateStr) {
    if (dateStr == null) {
      return null;
    }
    Timestamp timestamp = null;
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date parsedDate = dateFormat.parse(dateStr);
      timestamp = new java.sql.Timestamp(parsedDate.getTime());
    } catch (Exception e) { //this generic but you can control another types of exception
      e.printStackTrace();
    }
    return timestamp;
  }

  default String map(Timestamp date) {
    if (date == null) {
      return null;
    }
    String parsedDate = null;
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      parsedDate = dateFormat.format(date);
    } catch (Exception e) { //this generic but you can control another types of exception
      e.printStackTrace();
    }
    return parsedDate;
  }
}
