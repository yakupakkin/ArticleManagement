package com.article.management.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static String convertLocalDateTimeToISO8601(LocalDateTime now) {
        // covert LocalDateTime to ISO Date String
        String dateString = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now);
        return dateString;
    }

}
