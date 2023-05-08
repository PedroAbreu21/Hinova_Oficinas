package com.example.hinovaoficinas.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager {

    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        return sdf.format(currentDate);
    }
}
