package com.article.management.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String convertLocalDateTimeToISO8601(LocalDateTime now) {
        // covert LocalDateTime to ISO Date String
        String dateString = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now);
        return dateString;
    }

    public static String convertISO8601ToStringDate(String date){
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        LocalDate localDate = localDateTime.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = localDate.format(formatter);
       return formatDateTime.format(date);
    }

}
