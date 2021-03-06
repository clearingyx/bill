package com.biz.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");

	private final static SimpleDateFormat sdfYearMonth = new SimpleDateFormat("yyyy-MM");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
	"yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfDayTimes = new SimpleDateFormat(
	"yyyyMMddmmHHsszzz");
	private final static SimpleDateFormat sdfDayMsec = new SimpleDateFormat(
	"yyyyMMddHHmmssSSS");
	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	public static String getMonth() {
		return sdfMonth.format(new Date());
	}
	public static String getYearMonth() {
		return sdfYearMonth.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}
	/**
	 * 获取YYYYMMDDHHMMSSZZZ格式
	 * 
	 * @return
	 */
	public static String getDayTimes(){
		return sdfDayTimes.format(new Date());
	}
	/**
	 * 获取YYYYMMDDHHMMSSSSS格式
	 * 
	 * @return
	 */
	public static String getDayMsec(){
		return sdfDayMsec.format(new Date());
	}
	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    /*
     * Timestamp->String
     */
    public String getTimestampString(Timestamp t){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.z");
		String str=sdf.format(t);
		return str;
	}
    /*
     * String->Date
     */
	public Date getDate(String str) throws ParseException{
		 Date date=sdfTime.parse(str);
		 return date;
	}

	//得到现在的格式化时间
	public static String getNowTime() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String date = year + "-" + month + "-" + day + " " + hour + ":"
				+ minute + ":" + second;
		return date;
	}
	//得到今天开始的时间
	public static String today_begin() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String date = year + "-" + month + "-" + day + " 00:00:00";
		return date;
	}
	//得到今天结束的时间
	public static String today_end() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH)+1;
		String date = year + "-" + month + "-" + day + " 00:00:00";
		return date;
	}
	//得到昨天开始的时间
	public static String yestoday_begin() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH)-1;
		String date = year + "-" + month + "-" + day + " 00:00:00";
		return date;
	}
	//得到昨天结束的时间
	public static String yestoday_end() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String date = year + "-" + month + "-" + day + " 00:00:00";
		return date;
	}
	//本周开始时间
	public static String weekBeginDate(){
		  Calendar cal = Calendar.getInstance();
		  int date = cal.get(Calendar.DAY_OF_MONTH);
		  int n = cal.get(Calendar.DAY_OF_WEEK);
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  cal.set(Calendar.DAY_OF_MONTH, date + 2 - n);
		  String weekBeginDate=sdf.format(cal.getTime())+" 00:00:00";
		  return weekBeginDate;
	}
	//本周结束时间
	public static String weekEndDate(){
		  Calendar cal = Calendar.getInstance();
		  int date = cal.get(Calendar.DAY_OF_MONTH);
		  int n = cal.get(Calendar.DAY_OF_WEEK);
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  cal.set(Calendar.DAY_OF_MONTH,date+9-n);		  
		  String weekEndDate=sdf.format(cal.getTime())+" 00:00:00";
		  return weekEndDate;
	}
	/*
	 * 本月开始时间
	 */
	public static String thisMonthBegin(){
		Calendar cal = Calendar.getInstance();
		int month=cal.get(Calendar.MONTH)+1;
		int year=cal.get(Calendar.YEAR);
//		String monthBeginDate=year+"-"+month+"-01"+" 00:00:00";
		String monthBeginDate=year+"-"+month+"-01";
		return monthBeginDate;
	}
	public static String thisMonthEnd(){
//		Calendar calendar = Calendar.getInstance();
//	    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
//	    calendar.set(Calendar.DATE, 1);
//	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//	    String monthEndDate=format.format(calendar.getTime())+" 00:00:00";
//		return monthEndDate;
		Calendar cal = Calendar.getInstance();
		int month=cal.get(Calendar.MONTH)+2;
		int year=cal.get(Calendar.YEAR);
//		String monthBeginDate=year+"-"+month+"-01"+" 00:00:00";
		String monthBeginDate= year + "-" + month + "-01";
		return monthBeginDate;
	}

	//Stirng 转 Date
	public static Date DateByStringWithYearMonth(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.parse(time);
	}
	
	/**
     * 产生随机的三位数
     * @return
     */
    public static String getThree() {
        Random rad = new Random();
        return rad.nextInt(1000) + "";
    }

	/**
	 * 是否为同一月
	 * @param date1
	 * @param date2
     * @return
     */
	public static Object isSameDate(String date1, String date2) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM");
		try {
			Date d1 = fmt.parse(date1);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(d1);

			Date d2 = fmt.parse(date2);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(d2);

			boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
					.get(Calendar.YEAR);
			boolean isSameMonth = isSameYear
					&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
//		boolean isSameDate = isSameMonth
//				&& cal1.get(Calendar.DAY_OF_MONTH) == cal2
//				.get(Calendar.DAY_OF_MONTH);

			return isSameMonth;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static final List<Integer> getMonthList(){
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < 13; i++){
			list.add(i);
		}
		return list;
	}

	public static final List<Integer> getYearList(){
		Integer year = Integer.valueOf(DateUtil.getYear());
		List<Integer> list = new ArrayList<Integer>();
		int i = 0;
		while (i < 3){
			list.add(year-i);
			i++;
		}
		return list;
	}

	/**
	 * date 订单创建日期，begin_date查询的开始日期，订单日期超过开始日期，能查出来，返回1
	 * @param date
	 * @param begin_date
     * @return
     */
	public static int compare_date(Date date, String begin_date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date begin = df.parse(begin_date);
			if (date.getTime() > begin.getTime()) {
				return 1;
			} else if (date.getTime() < begin.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
}
