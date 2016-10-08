package com.biz.util;

import com.mysql.jdbc.log.LogUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期处理工具
 * 
 * @author song.chang
 * @create 2015年8月26日
 */
public class DateUtils {
    private static final Logger LOG  = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 日期格式
     * 
     * @author song.chang
     * @create 2015年8月26日
     */
    public static enum DTFormat {
        MM_dd("MM-dd"), //
        yy_MM_dd("yy-MM-dd"), //
        yy_MM_dd_HH("yy-MM-dd HH"), //
        yy_MM_dd_HH_mm("yy-MM-dd HH:mm"), //
        yy_MM_dd_HH_mm_ss("yy-MM-dd HH:mm:ss"), //
        yyyy("yyyy"), //
        yyyy_MM("yyyy-MM"), //
        yyyy_MM_dd("yyyy-MM-dd"), //
        yyyy_MM_dd_HH("yyyy-MM-dd HH"), //
        yyyy_MM_dd_HH_mm("yyyy-MM-dd HH:mm"), //
        yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"), //
        MM_dd_HH_mm("MM-dd HH:mm"), //
        HH_mm("HH:mm"), //
        HH_mm_ss("HH:mm:ss"), //
        yyMMdd("yyMMdd"), //
        yyyyMMdd("yyyyMMdd"), //
        yyyyMMddHHmmss("yyyyMMddHHmmss"), //
        HHmmss("HHmmss"), //
        HH("HH"), //
        yyyy_MM_dd_Chinese("yyyy年MM月dd日"), //
        yyyy_MM_dd_HH_Chinese("yyyy年MM月dd日HH时"), //
        yyyy_MM_dd_HH_mm_Chinese("yyyy年MM月dd日HH时mm分"), //
        yyyy_MM_dd_HH_mm_ss_Chinese("yyyy年MM月dd日HH时mm分ss秒"), //
        ;

        private String format;
        private int length;

        DTFormat(String format) {
            this.format = format;
            this.length = format.length();
        }

        public String getFormat() {
            return format;
        }

        public int getLength() {
            return length;
        }
    }

    /**
     * 1天的毫秒数
     */
    public static final Long MILLISECOND_ONE_DAY = 1000 * 60 * 60 * 24L;

    /**
     * 1小时的毫秒数
     */
    public static final Long MILLISECOND_ONE_HOUR = 1000 * 60 * 60L;

    /**
     * 1分钟的毫秒数
     */
    public static final Long MILLISECOND_ONE_MINUTE = 1000 * 60L;

    /**
     * 星期名称
     */
    public static String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

    /**
     * 简版星期名称
     */
    public static String[] shortWeekDaysName = { "日", "一", "二", "三", "四", "五", "六" };

    /**
     * 用指定的格式解析日期
     * 
     * @author song.chang
     * @param date
     * @param format
     * @return
     */
    public static Date parse(String date, DTFormat format) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(date, format.getFormat());
        } catch (ParseException e) {
            LOG.error("DateUtils.parse 异常，返回 null", e);
        }
        return null;
    }

    /**
     * 用指定的格式转成日期字符串
     * 
     * @author song.chang
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, DTFormat format) {
        FastDateFormat sdf = FastDateFormat.getInstance(format.getFormat());
        return sdf.format(date);
    }

    /**
     * 将 before 格式的时间格式化为 after
     * 
     * @author song.chang
     * @param date
     * @param before
     * @param after
     * @return
     */
    public static String parse(String date, DTFormat before, DTFormat after) {
        return format(parse(date, before), after);
    }

    /**
     * 转仅保留指定格式的日期。如取当前时间的 yyyy_MM_dd 日期:
     * 
     * <pre>
     * // 得到 一个日期仅包含年月日，时间为 00:00:00
     * DateUtils.formatDate(new Date(), DTFormat.yyyy_MM_dd);
     * </pre>
     * 
     * @author song.chang
     * @param date
     * @param format
     * @return
     */
    public static Date formatDate(Date date, DTFormat format) {
        return parse(format(date, format), format);
    }

    /**
     * 按年增加日期
     * 
     * @author chaochec.long
     * @param date
     * @param year
     * @return
     */
    public static Date addDateByYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 把日期增加相应年数。整数往后推,负数往前移动
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 按月增加日期
     * 
     * @author chaochec.long
     * @param date
     * @param month
     * @return
     */
    public static Date addDateByMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 把日期增加相应月数。整数往后推,负数往前移动
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 按天数增加日期
     * 
     * @author haibin.xiong
     * @param date
     * @param days
     * @return
     */
    public static Date addDateByDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 把日期增加相应天数。整数往后推,负数往前移动
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 按小时增加日期
     * 
     * @author haibin.xiong
     * @param date
     * @param hours
     * @return
     */
    public static Date addDateByHour(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 把小时增加相应小时数。整数往后推,负数往前移动
        calendar.add(Calendar.HOUR, hours);
        return calendar.getTime();
    }

    /**
     * 按分钟增加日期
     * 
     * @author haibin.xiong
     * @param date
     * @return
     */
    public static Date addDateByMinute(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 把分钟增加相应分钟数。整数往后推,负数往前移动
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * 按秒钟增加日期
     *
     * @author haibin.xiong
     * @param date
     * @param second
     * @return
     */
    public static Date addDateBySecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 把分钟增加相应分钟数。整数往后推,负数往前移动
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 判断某一日期是否早于今天(仅日期比较)
     * 
     * @author haibin.xiong
     * @param date
     * @return
     */
    public static boolean isBeforeToday(Date date) {
        // 获取当前日期
        Date newDate = formatDate(new Date(), DTFormat.yyyy_MM_dd);
        return date.before(newDate);
    }

    /**
     * 取得两个日期间的所有"日期"，包括开始、结束日期
     *
     * @author phoenixu
     * @param startDate
     * @param endDate
     * @return 日期格式为 yyyy_MM_dd
     */
    public static List<Date> getDateListOfTwoDate(Date startDate, Date endDate) {
        // 时间格式统一
        Date start = formatDate(startDate, DTFormat.yyyy_MM_dd);
        Date end = formatDate(endDate, DTFormat.yyyy_MM_dd);

        List<Date> dateList = new ArrayList<Date>();
        while (!start.after(end)) {
            dateList.add(start);
            start = addDateByDay(start, 1);
        }
        return dateList;
    }

    /**
     * 取得两个日期间的所有"日期"字符串，包括开始、结束日期
     * 
     * @author haibin.xiong
     * @param startDate
     * @param endDate
     * @return 日期格式为 yyyy_MM_dd
     */
    public static List<String> getDayListOfTwoDate(Date startDate, Date endDate) {
        // 时间格式统一
        Date start = formatDate(startDate, DTFormat.yyyy_MM_dd);
        Date end = formatDate(endDate, DTFormat.yyyy_MM_dd);

        List<String> retDateList = new ArrayList<String>();
        while (start.compareTo(end) <= 0) {
            retDateList.add(format(start, DTFormat.yyyy_MM_dd));
            start = addDateByDay(start, 1);
        }
        return retDateList;
    }

    /**
     * 取得两个日期间的所有日期 包括开始日期，不包括结束日期
     * 
     * @author haibin.xiong
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getDaysIncludeStartExcludeEnd(Date startDate, Date endDate) {
        endDate = addDateByDay(endDate, -1);
        return getDayListOfTwoDate(startDate, endDate);
    }

    /**
     * 取得两个日期间的天数
     * 
     * @author changhao.shao
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDaysOfTwoDate(Date startDate, Date endDate) {
        // 时间格式统一
        startDate = formatDate(startDate, DTFormat.yyyy_MM_dd);
        endDate = formatDate(endDate, DTFormat.yyyy_MM_dd);
        return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    }

    /**
     * 根据日期获取星期
     * 
     * @author changhao.shao
     * @param date
     * @return
     */
    public static String getWeekNameOfDate(Date date) {
        // 时间格式统一
        date = formatDate(date, DTFormat.yyyy_MM_dd);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    /**
     * 根据日期获取简版星期
     * 
     * @author changhao.shao
     * @param date
     * @return
     */
    public static String getShortWeekNameOfDate(Date date) {
        // 时间格式统一
        date = formatDate(date, DTFormat.yyyy_MM_dd);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return shortWeekDaysName[intWeek];
    }

    /**
     * 获取年的第一天
     * 
     * @author chaochec.long
     * @param year
     * @return
     */
    public static Date getFirstDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 获取年的最后一天
     * 
     * @author chaochec.long
     * @param date
     * @return
     */
    public static Date getLastDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DATE, 31);
        return calendar.getTime();
    }

    /**
     * 获取月的第一天
     * 
     * @author chaochec.long
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 获取月的最后一天
     * 
     * @author chaochec.long
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回日期的月份
     * 
     * @author chaochec.long
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回月天数
     * 
     * @author chaochec.long
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据year和month组装date
     * 
     * @author chaochec.long
     * @param year
     * @param month
     * @return
     */
    public static Date getMonthDate(Date year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getTime();
    }

    /**
     * 获取本周一date, 按一周从周一开始
     * 
     * @author chaochec.long
     * @return
     */
    public static Date getMonday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 一周第几天
        if (dayOfWeek == 1) { // 周日
            calendar.add(Calendar.DATE, -6);
        } else {
            calendar.add(Calendar.DATE, -(dayOfWeek - 2));
        }
        return calendar.getTime();
    }
}
