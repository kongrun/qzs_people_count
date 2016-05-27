package com.qzs.utility;



import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/14.
 */
public class DateFormat {

    public static long getTimeStamp(String dateString)
    {
        long timestamp = 0;
        try {
            //Date或者String转化为时间戳
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(dateString);
            timestamp = date.getTime();
        }catch(Exception e){}
        return timestamp;
    }
    public static String getTime(long timestamp)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(new Date(timestamp));
        return dateString;
    }

    @SuppressWarnings("deprecation")
    public static int getDay(long timestamp)
    {
        int day = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(new Date(timestamp));

        //System.out.println(dateString);
        try{
            Date date = sdf.parse(dateString);
            day = date.getDate();
        }catch(Exception e){}

        return day;
    }


    @SuppressWarnings("deprecation")
    public static int getWeek(long timestamp)
    {
        int week = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(new Date(timestamp));

        //System.out.println(dateString);
        try{
            Date date = sdf.parse(dateString);
            week = date.getDay();
        }catch(Exception e){}

        return week;
    }


    @SuppressWarnings("deprecation")
    public static int getMonth(long timestamp)
    {
        int month = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(new Date(timestamp));

        //System.out.println(dateString);
        try{
            Date date = sdf.parse(dateString);
            month = date.getMonth();
        }catch(Exception e){}

        return month;
    }

    @SuppressWarnings("deprecation")
    public static int getYear(long timestamp)
    {
        int year = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(new Date(timestamp));

        //System.out.println(dateString);
        try{
            Date date = sdf.parse(dateString);
            year = date.getYear();
        }catch(Exception e){}

        return year;
    }

    @SuppressWarnings("deprecation")
    public static int getHour(long timestamp)
    {
        int year = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(new Date(timestamp));

        //System.out.println(dateString);
        try{
            Date date = sdf.parse(dateString);
            year = date.getHours();
        }catch(Exception e){}

        return year;
    }
    @SuppressWarnings("deprecation")
    public static int getMinute(long timestamp)
    {
        int year = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(new Date(timestamp));

        //System.out.println(dateString);
        try{
            Date date = sdf.parse(dateString);
            year = date.getMinutes();
        }catch(Exception e){}

        return year;
    }
    @SuppressWarnings("deprecation")
    public static int getSecond(long timestamp)
    {
        int year = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(new Date(timestamp));

        //System.out.println(dateString);
        try{
            Date date = sdf.parse(dateString);
            year = date.getSeconds();
        }catch(Exception e){}

        return year;
    }
}
