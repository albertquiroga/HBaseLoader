package com.convector.hbaseloader.parser.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pyro_ on 13/05/2016.
 */
public abstract class TimeParser {

    private static SimpleDateFormat dateFormat;
    private static Date parsedDate;

    public static Timestamp parseDate(String timeFormat, String timestamp) {
        dateFormat = new SimpleDateFormat(timeFormat);
        parsedDate = null;
        try {
            parsedDate = dateFormat.parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new java.sql.Timestamp(parsedDate.getTime());
    }

}
