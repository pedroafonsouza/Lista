package com.rivendev.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtils {


    static Calendar cal = Calendar.getInstance();
    static final String defaultFormat = "dd/MM/yyyy - HH:mm";


    public  static Date setHourMinCurrentDate(Date date, int hour, int min){


        cal.add(Calendar.DATE, -1);
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        return cal.getTime();

    }

    public static Date setCurrentDate(int year, int month, int day){


        cal.set(year,month, day);
        return  cal.getTime();


    }

    public static String dateToString(Date date, String format){

        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(date);


    }

    public static String dateToString(Date date){

       return dateToString(date, defaultFormat);

    }

}
