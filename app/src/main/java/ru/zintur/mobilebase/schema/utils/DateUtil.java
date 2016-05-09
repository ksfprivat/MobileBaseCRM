package ru.zintur.mobilebase.schema.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String getCurrentDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.ROOT);

        return sdf.format(new Date());
    }
}
