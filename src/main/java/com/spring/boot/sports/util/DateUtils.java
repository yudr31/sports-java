package com.spring.boot.sports.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author yuderen
 * @version 2017/10/26 16:48
 */
public class DateUtils {

    public static final SimpleDateFormat dayDateFormat = new SimpleDateFormat("dd HH:mm:ss");
    public static final SimpleDateFormat hourDateFormat = new SimpleDateFormat("HH:mm:ss");
    public static final String[] longDateFormatArray = new String[]{"yyyy-MM-dd HH:mm:ss"};
    public static final String[] shortDateFormatArray = new String[]{"yyyy-MM-dd"};
    public static final String[] unsignedDateFormatArray = new String[]{"yyyyMMddHHmmss"};
    public static final String[] dateFormatArray = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyyMMddHHmmss"};
    public static final ThreadLocal<SimpleDateFormat> longDateFormat = new ThreadLocal<SimpleDateFormat>() {
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    public static final ThreadLocal<SimpleDateFormat> shortDateFormat = new ThreadLocal<SimpleDateFormat>() {
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    public static final ThreadLocal<SimpleDateFormat> unsignedDateFormat = new ThreadLocal<SimpleDateFormat>() {
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
    };

    public DateUtils() {
    }

    public static long getCurrentMilliseconds() {
        return System.currentTimeMillis();
    }

    public static Date convertMillisecondToDate(long millisecond) {
        return new Date(millisecond);
    }

    public static String convertMillisecondToLongDateString(long millisecond) {
        return ((SimpleDateFormat)longDateFormat.get()).format(convertMillisecondToDate(millisecond));
    }

    public static String convertMillisecondToShortDateString(long millisecond) {
        return ((SimpleDateFormat)shortDateFormat.get()).format(convertMillisecondToDate(millisecond));
    }

    public static String convertMillisecondToUnsignedDateString(long millisecond) {
        return ((SimpleDateFormat)unsignedDateFormat.get()).format(convertMillisecondToDate(millisecond));
    }

    public static String convertMillisecondToHourDateString(long millisecond) {
        return hourDateFormat.format(convertMillisecondToDate(millisecond));
    }

    public static String convertMillisecondToDayDateString(long millisecond) {
        return dayDateFormat.format(convertMillisecondToDate(millisecond));
    }

    public static String convertMillisecondToDateString(long millisecond, String pattern) {
        return DateFormatUtils.format(millisecond, pattern);
    }

    public static long convertDateStringToMillisecond(String dateString) {
        return parseDateTime(dateString, dateFormatArray);
    }

    public static long convertLongDateStringToMillisecond(String dateString) {
        return parseDateTime(dateString, longDateFormatArray);
    }

    public static long convertShortDateStringToMillisecond(String dateString) {
        return parseDateTime(dateString, shortDateFormatArray);
    }

    public static long convertUnsignedDateStringToMillisecond(String dateString) {
        return parseDateTime(dateString, unsignedDateFormatArray);
    }

    public static long convertDateStringToMillisecond(String dateString, String[] parsePatterns) {
        return parseDateTime(dateString, parsePatterns);
    }

    private static Long parseDateTime(String dateString,String[] formatArray){
        long ret = 0L;
        if(!StringUtils.isBlank(dateString)) {
            try {
                Date date = org.apache.commons.lang3.time.DateUtils.parseDate(dateString, formatArray);
                ret = date.getTime();
            } catch (Exception var4) {
                throw new RuntimeException(var4);
            }
        }

        return ret;
    }

    public static String getCurrentShortDateString() {
        return convertMillisecondToShortDateString(getCurrentMilliseconds());
    }

    public static String getCurrentLongDateString() {
        return convertMillisecondToLongDateString(getCurrentMilliseconds());
    }

    public static String getCurrentUnsignedDateString() {
        return convertMillisecondToUnsignedDateString(getCurrentMilliseconds());
    }

    public static String getCurrentHourDateString() {
        return convertMillisecondToHourDateString(getCurrentMilliseconds());
    }

    public static String getCurrentDayDateString() {
        return convertMillisecondToDayDateString(getCurrentMilliseconds());
    }

    public static long getMillisecondAfter(long milliseconds, int timeUnitType, int timeUnitCount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        switch(timeUnitType) {
            case 1:
                return 9223372036854775807L;
            case 2:
                calendar.add(14, timeUnitCount);
                break;
            case 3:
                calendar.add(13, timeUnitCount);
                break;
            case 4:
                calendar.add(12, timeUnitCount);
                break;
            case 5:
                calendar.add(10, timeUnitCount);
                break;
            case 6:
                calendar.add(6, timeUnitCount);
                break;
            case 7:
                calendar.add(3, timeUnitCount);
                break;
            case 8:
                calendar.add(2, timeUnitCount);
                break;
            case 9:
                calendar.add(1, timeUnitCount);
                break;
            default:
                throw new RuntimeException("do not support time unit type: " + timeUnitType);
        }

        return calendar.getTimeInMillis();
    }

    public static boolean validateLongDateFormat(String dateString) {
        return validateDateFormat(dateString, (SimpleDateFormat)longDateFormat.get());
    }

    public static boolean validateShortDateFormat(String dateString) {
        return validateDateFormat(dateString, (SimpleDateFormat)shortDateFormat.get());
    }

    public static boolean validateUnsignedDateFormat(String dateString) {
        return validateDateFormat(dateString, (SimpleDateFormat)unsignedDateFormat.get());
    }

    public static boolean validateDateFormat(String dateString, String dateFormatString) {
        return validateDateFormat(dateString, new SimpleDateFormat(dateFormatString));
    }

    public static boolean validateDateFormat(String dateString, SimpleDateFormat dateFormat) {
        if(dateString == null) {
            return false;
        } else {
            try {
                dateFormat.setLenient(false);
                dateFormat.parse(dateString);
                return true;
            } catch (Exception var3) {
                return false;
            }
        }
    }

    public static long[] getCoverageTime(Long startTime, Long endTime, int range) {
        long[] times = new long[2];
        if((startTime == null || startTime.longValue() <= 0L) && (endTime == null || endTime.longValue() <= 0L)) {
            long nowTime = getMillisecondEnd(System.currentTimeMillis(), 6);
            times[0] = getMillisecondStart(nowTime - (long)(range - 1) * 86400000L, 6);
            times[1] = nowTime;
        } else if(startTime != null && startTime.longValue() > 0L && endTime != null && endTime.longValue() > 0L) {
            if(endTime.longValue() - startTime.longValue() <= (long)range * 86400000L && endTime.longValue() - startTime.longValue() >= 0L) {
                times[0] = startTime.longValue();
                times[1] = endTime.longValue();
            } else {
                times[0] = startTime.longValue();
                times[1] = getMillisecondEnd(startTime.longValue() + (long)(range - 1) * 86400000L, 6);
            }
        } else if(startTime != null && startTime.longValue() > 0L) {
            times[0] = startTime.longValue();
            times[1] = getMillisecondEnd(startTime.longValue() + (long)(range - 1) * 86400000L, 6);
        } else {
            times[0] = getMillisecondStart(endTime.longValue() - (long)(range - 1) * 86400000L, 6);
            times[1] = endTime.longValue();
        }

        return times;
    }

    public static long getMillisecondsForMonthBegin(int offset) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(2, offset);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long getMillisecondsForYearBegin(int offset) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(1, offset);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long getMillisecondsBegin(int year, int month, int day) {
        Calendar calendar = GregorianCalendar.getInstance();
        if(year != 0) {
            calendar.set(1, year);
        }

        if(month != 0) {
            calendar.set(2, month - 1);
        }

        if(day != 0) {
            calendar.set(5, day);
        }

        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long getMillisecondsBegin(long millisecond) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(millisecond);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long getMillisecondsEnd(long millisecond) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(millisecond);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTimeInMillis();
    }

    public static long getExcursionMillisecondsOfDay(int days) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(5, days);
        return calendar.getTimeInMillis();
    }

    public static int getRemainingTimeOfDay() {
        Calendar calendar = GregorianCalendar.getInstance();
        int h = calendar.get(11);
        int m = calendar.get(12);
        int s = calendar.get(13);
        int now_s = h * 60 * 60 + m * 60 + s;
        int end_s = 86400;
        return end_s - now_s;
    }

    public static long getMillisecondStart(long nowInMilliseconds, int timeUnitType) {
        Calendar calendar;
        switch(timeUnitType) {
            case 1:
                return 0L;
            case 2:
                return nowInMilliseconds;
            case 3:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 0);
                return calendar.getTimeInMillis();
            case 4:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 0);
                calendar.set(13, 0);
                return calendar.getTimeInMillis();
            case 5:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 0);
                calendar.set(13, 0);
                calendar.set(12, 0);
                return calendar.getTimeInMillis();
            case 6:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 0);
                calendar.set(13, 0);
                calendar.set(12, 0);
                calendar.set(11, 0);
                return calendar.getTimeInMillis();
            case 7:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 0);
                calendar.set(13, 0);
                calendar.set(12, 0);
                calendar.set(11, 0);
                calendar.set(7, 2);
                return calendar.getTimeInMillis();
            case 8:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 0);
                calendar.set(13, 0);
                calendar.set(12, 0);
                calendar.set(11, 0);
                calendar.set(5, 1);
                return calendar.getTimeInMillis();
            case 9:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 0);
                calendar.set(13, 0);
                calendar.set(12, 0);
                calendar.set(11, 0);
                calendar.set(6, 1);
                return calendar.getTimeInMillis();
            default:
                throw new RuntimeException("do not support time unit type: " + timeUnitType);
        }
    }

    public static long getMillisecondEnd(long nowInMilliseconds, int timeUnitType) {
        Calendar calendar;
        switch(timeUnitType) {
            case 1:
                return 9223372036854775807L;
            case 2:
                return nowInMilliseconds;
            case 3:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 999);
                return calendar.getTimeInMillis();
            case 4:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 999);
                calendar.set(13, 59);
                return calendar.getTimeInMillis();
            case 5:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 999);
                calendar.set(13, 59);
                calendar.set(12, 59);
                return calendar.getTimeInMillis();
            case 6:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 999);
                calendar.set(13, 59);
                calendar.set(12, 59);
                calendar.set(11, 23);
                return calendar.getTimeInMillis();
            case 7:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 999);
                calendar.set(13, 59);
                calendar.set(12, 59);
                calendar.set(11, 23);
                calendar.set(7, 1);
                return calendar.getTimeInMillis();
            case 8:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 999);
                calendar.set(13, 59);
                calendar.set(12, 59);
                calendar.set(11, 23);
                calendar.set(5, calendar.getActualMaximum(5));
                return calendar.getTimeInMillis();
            case 9:
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(nowInMilliseconds);
                calendar.set(14, 999);
                calendar.set(13, 59);
                calendar.set(12, 59);
                calendar.set(11, 23);
                calendar.set(6, calendar.getActualMaximum(6));
                return calendar.getTimeInMillis();
            default:
                throw new RuntimeException("do not support time unit type: " + timeUnitType);
        }
    }

    public static String formatValidTimes(long validTimes) {
        return validTimes % 1000L != 0L?String.format("%s毫秒", new Object[]{Long.valueOf(validTimes)}):(validTimes % 60000L != 0L?String.format("%s秒", new Object[]{Long.valueOf((long)Math.floor((double)(validTimes / 1000L)))}):(validTimes % 3600000L != 0L?String.format("%s分钟", new Object[]{Long.valueOf((long)Math.floor((double)(validTimes / 60000L)))}):(validTimes % 86400000L != 0L?String.format("%s小时", new Object[]{Long.valueOf((long)Math.floor((double)(validTimes / 3600000L)))}):String.format("%s天", new Object[]{Long.valueOf((long)Math.floor((double)(validTimes / 86400000L)))}))));
    }

}
