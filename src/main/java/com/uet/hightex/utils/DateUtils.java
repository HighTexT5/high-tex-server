package com.uet.hightex.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}
