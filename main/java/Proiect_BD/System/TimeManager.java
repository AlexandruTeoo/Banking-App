package Proiect_BD.System;

import java.util.concurrent.TimeUnit;

public class TimeManager {
    public static java.sql.Date getCurrentDate(){
        long millis=System.currentTimeMillis();
        return new java.sql.Date(millis);
    }
    public static long getDateUtilDiff(java.util.Date date1, java.util.Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    public static long getDateSqlDiff(java.sql.Date date1, java.sql.Date date2, TimeUnit timeUnit) {
        return getDateUtilDiff(convertFromSqlToUtil(date1),convertFromSqlToUtil(date2),timeUnit);
    }

    public static java.util.Date convertFromSqlToUtil(java.sql.Date date){
        return new java.util.Date(date.getTime());
    }
    //formatul este "yyyy-mm-dd"
    public static java.sql.Date convertFromStringToDate(String date)
    {
        return java.sql.Date.valueOf(date);
    }
}
