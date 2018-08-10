package com.library.base.utils;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class DateUtils {


    public static long getCurrentTime() {
        Calendar mDate = Calendar.getInstance();
        long mTime = mDate.getTimeInMillis();
        return mTime;
    }

    @SuppressLint("SimpleDateFormat")
    public static long getMillis(String date, String divide) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new SimpleDateFormat("yyyy" + divide + "MM" + divide
                    + "dd").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.getTimeInMillis();
    }

    public static String getMonthForRunningWater(long millis) {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        calendar.setTimeInMillis(millis);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (currentMonth == month) {
            return "本月账单";
        } else {
            return month + "月账单";
        }
    }

    public static String getMonthToSecond(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return getNumberStr((calendar.get(Calendar.MONTH) + 1)) + "-"
                + getNumberStr(calendar.get(Calendar.DAY_OF_MONTH)) + "  "
                + getNumberStr(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                + getNumberStr(calendar.get(Calendar.MINUTE)) + ":"
                + getNumberStr(calendar.get(Calendar.SECOND));
    }

    public static String getYearToSecond(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR) + "-"
                + getNumberStr((calendar.get(Calendar.MONTH) + 1)) + "-"
                + getNumberStr(calendar.get(Calendar.DAY_OF_MONTH)) + " "
                + getNumberStr(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                + getNumberStr(calendar.get(Calendar.MINUTE)) + ":"
                + getNumberStr(calendar.get(Calendar.SECOND));
    }

    public static String getNumberStr(int date) {
        if (date < 10) {
            return "0" + date;
        } else {
            return String.valueOf(date);
        }
    }

    public static String getYearToDay(long millis, String divide) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR) + divide
                + getNumberStr((calendar.get(Calendar.MONTH) + 1)) + divide
                + getNumberStr(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static String getYearToDay(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR) + "年"
                + getNumberStr((calendar.get(Calendar.MONTH) + 1)) + "月"
                + getNumberStr(calendar.get(Calendar.DAY_OF_MONTH)) + "日";
    }

    public static String getYearToDayFor(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR) + "/"
                + getNumberStr((calendar.get(Calendar.MONTH) + 1)) + "/"
                + getNumberStr(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static String getMonthToDay(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return (calendar.get(Calendar.MONTH) + 1) + "月"
                + calendar.get(Calendar.DAY_OF_MONTH) + "日";
    }


    public static String getMonthToDayWeek(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return (calendar.get(Calendar.MONTH) + 1) + "月"
                + calendar.get(Calendar.DAY_OF_MONTH) + "日 " + calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getMonthToYear(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        String month = (calendar.get(Calendar.MONTH) + 1) + "";
        if (month.length() == 1) {
            month = "0" + month;
        }
        return (calendar.get(Calendar.YEAR)) + "-" + month;
    }

    public static String getMonthToYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String month = (calendar.get(Calendar.MONTH) + 1) + "";
        if (month.length() == 1) {
            month = "0" + month;
        }
        return (calendar.get(Calendar.YEAR)) + "-" + month;
    }

    public static String getMonthToYearTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return (calendar.get(Calendar.YEAR)) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
    }

    public static String getMonthToYearTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.YEAR)) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
    }

    public static String getTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.YEAR)) + "-" +
                (calendar.get(Calendar.MONTH) + 1) + "-"+
                calendar.get(Calendar.DAY_OF_MONTH)+"  "+
                calendar.get(Calendar.HOUR_OF_DAY)+":"+"00";
    }


    /**
     * 将长时间格式字符串转换为时间 MM-dd EE
     */
    public static String getStringDate(Long date) {
        String strDate = (String) DateFormat.format("MM月dd日 EE", date);
        return strDate;
    }

    /**
     * 将长时间格式字符串转换为时间 MM-dd
     */
    public static String getStringDateToDay(Long date) {
        String strDate = (String) DateFormat.format("MM月dd日", date);
        return strDate;
    }

    public static String getHourToMinute(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return getNumberStr(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                + getNumberStr(calendar.get(Calendar.MINUTE));
    }

    public static String getYearToMinute(long millis, String divide) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR) + divide
                + getNumberStr((calendar.get(Calendar.MONTH) + 1)) + divide
                + getNumberStr(calendar.get(Calendar.DAY_OF_MONTH)) + "  "
                + getNumberStr(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
                + getNumberStr(calendar.get(Calendar.MINUTE));
    }

    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater2.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    @SuppressWarnings("unused")
    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm
     */
    public static String getToStringTime(Long date) {
        String strDate = (String) DateFormat.format("yyyy-MM-dd HH:mm", date);
        return strDate;
    }


    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getCurrentTime());
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getCurrentTime());
        return calendar.get(Calendar.MONTH);
    }

    public static String getCurrentYearToDayToWeek() {
        Calendar calendar = Calendar.getInstance();
        long millis = calendar.getTimeInMillis();
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.YEAR) + "年"
                + getNumberStr((calendar.get(Calendar.MONTH) + 1)) + "月"
                + getNumberStr(calendar.get(Calendar.DAY_OF_MONTH)) + "日"
                +"，"
                +getWeek();
    }

    public static String getWeek() {
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            default:
                return "";
        }
    }

}
