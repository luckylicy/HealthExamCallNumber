package com.xyj.hecn.utils;


import androidx.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * CalendarUtils
 * description: TODO
 *
 * @author : Licy
 * @date : 2020/3/15
 * email ：licy3051@qq.com
 */
public class CalendarUtils {

    public static final int TYPE_ALL = 1;
    public static final int TYPE_YEAR_MONTH_DAY = 2;
    public static final int TYPE_ALL_WITH_ZONE = 3;
    public static final int TYPE_YYYYMMDD = 4;
    public static final int TYPE_YYYY = 5;
    public static final int TYPE_YYYYMMDDHHMMSS = 6;
    public static final int TYPE_YYYY_MM_DD_HH_MM = 7;
    public static final int TYPE_MONTH_DAY = 8;
    public static final int TYPE_MONTH_DAY_HOUR_MIN = 9;

    @Documented
    @IntDef({TYPE_ALL, TYPE_YEAR_MONTH_DAY, TYPE_ALL_WITH_ZONE,
            TYPE_YYYYMMDD, TYPE_YYYY, TYPE_YYYYMMDDHHMMSS,
            TYPE_YYYY_MM_DD_HH_MM, TYPE_MONTH_DAY, TYPE_MONTH_DAY_HOUR_MIN})
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FormatType {
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDateString(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - past);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(today);
        return result;
    }

    public static Date getPastDate(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - past);
        return calendar.getTime();
    }

    public static Date getFutureDate(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + past);
        return calendar.getTime();
    }

    public static Calendar getPastMonth(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - past);
        return calendar;
    }

    public static Calendar getPastWeek(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) - past);
        return calendar;
    }

    public static Date getPastYear(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - past);
        return calendar.getTime();
    }

    public static Date getFutureMonth(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + past);
        return calendar.getTime();
    }

    public static Date getFutureYear(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + past);
        return calendar.getTime();
    }

    /**
     * 获取过去7天内的日期数组
     *
     * @return 日期数组
     */
    public static ArrayList<String> pastDay(String time) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        try {
            //我这里传来的时间是个string类型的，所以要先转为date类型的。
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(time);
            for (int i = 6; i >= 0; i--) {
                pastDaysList.add(getPastDateString(i, date));
            }
        } catch (ParseException e) {
            e.printStackTrace();


        }
        return pastDaysList;
    }

    /**
     * SimpleDateFormat 线程不安全， 故每次使用都new新的
     *
     * @param type
     * @param date
     * @return
     */
    public static String parseDate(@FormatType int type, Date date) {
        if (date == null) {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            mSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            return mSimpleDateFormat.format(new Date());
        }
        SimpleDateFormat dateFormat;
        switch (type) {
            case TYPE_YEAR_MONTH_DAY:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                break;
            case TYPE_MONTH_DAY:
                dateFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
                break;
            case TYPE_ALL_WITH_ZONE:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z", Locale.getDefault());
                break;
            case TYPE_YYYYMMDD:
                dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
                break;
            case TYPE_YYYY:
                dateFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
                break;
            case TYPE_YYYYMMDDHHMMSS:
                dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
                break;
            case TYPE_YYYY_MM_DD_HH_MM:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                break;
            case TYPE_MONTH_DAY_HOUR_MIN:
                dateFormat = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
                break;
            case TYPE_ALL:
            default:
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                break;
        }
        String time = dateFormat.format(date);
        dateFormat = null;
        return time;
    }

    public static Calendar getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar;
    }

    /**
     * @param time "2020-03-16T01:57:44.000Z"
     * @return
     */
    public static Date parseLinuxTimeFormatToDate(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z", Locale.getDefault());
        Date date;
        try {
            time = time.replace("Z", " UTC");
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date();
        }
        return date;
    }

    /**
     * @param birth "2020-03-16"
     * @return
     */
    public static Date parseBirthDayToDate(String birth) {
        SimpleDateFormat dateFormat;
        if (birth.length() == 8) {
            dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        } else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
        Date date;
        try {
            date = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date();
        }
        return date;
    }

    /**
     * @param time "2020-03-16T01:57:44.000Z"
     * @return
     */
    public static String parseLinuxTimeFormatToString(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z", Locale.getDefault());
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date;
        try {
            time = time.replace("Z", " UTC");
            date = dateFormat.parse(time);
            return mSimpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * @param time "2020-03-16T01:57:44.000Z"
     * @return
     */
    public static String parseLinuxTimeFormatToYMDHMS(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z", Locale.getDefault());
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date;
        try {
            time = time.replace("Z", " UTC");
            date = dateFormat.parse(time);
            return mSimpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * @param time "2020-03-16T01:57:44.000Z"
     * @return
     */
    public static String parseLinuxTimeFormatToYMD(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z", Locale.getDefault());
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date;
        try {
            time = time.replace("Z", " UTC");
            date = dateFormat.parse(time);
            return mSimpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 把年月日时分秒 格式的时间戳  转换成 月日时分 的类型
     *
     * @param time "2020-03-16T01:57:44.000Z"
     * @return
     */
    public static String parseYMDHMSToMDHM(String time) {
        SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        SimpleDateFormat resultFormat = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
        Date date;
        try {
            date = fromFormat.parse(time);
            return resultFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 把年月日时分秒 格式的时间戳  转换成 date
     *
     * @param time "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date parseTimeToDate(String time) {
        SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            return fromFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把年月日时分秒 格式的时间戳  转换成 date
     *
     * @param time "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date parseMinuteTimeToDate(String time) {
        SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        Date date;
        try {
            return date = fromFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把年月日 格式的时间戳  转换成 date
     *
     * @param time "yyyy-MM-dd"
     * @return
     */
    public static Date parseTimeOfYMDToDate(String time) {
        SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            return fromFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }

    }

    public static String getTodayToYMD() {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return mSimpleDateFormat.format(new Date());
    }

    /**
     * 把年月日时分秒 格式的时间戳  转换成 date
     *
     * @param currentTime long time
     * @return
     */
    public static String parseTimeToDate(long currentTime) {
        SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss", Locale.getDefault());
        return fromFormat.format(new Date());
    }
}
