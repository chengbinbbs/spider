package com.dayspass.datacenter.date.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 日期操作类
 * 
 * @author kouyi
 * @version 2.0 2016-09-26
 * 
 */
public class DateUtils {
	public static final String HOURMIN_FORMAT = "HH:mm";
	public static final String MONTHDAY_FORMAT = "MM-dd";
	public static final String PERIOD_FORMAT="yyyyMMdd";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATETIME_FORMAT_SLASH = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 将日期转换为指定格式的日期字符串
	 * 
	 * @author kouyi
	 * @param date
	 *            日期
	 * @param format
	 *            格式化模型,如："yyyy-MM-dd HH:mm:ss"
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(Date date, String format) {
		if (date == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat(format);
		String dateString = df.format(date);
		return dateString;
	}

	/**
	 * 将日期字符串转换成指定格式的日期
	 * 
	 * @author kouyi
	 * @param dateString
	 *            日期字符串
	 * @param format
	 *            格式化模型,如："yyyy-MM-dd HH:mm:ss"
	 * @return date 指定格式的日期
	 */
	public static Date parseDate(String dateString, String format) {
		Date date = null;
		if (!StringUtils.isEmpty(dateString)) {
			try {
				DateFormat df = new SimpleDateFormat(format);
				date = df.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * 获取指定日期前n天的日期
	 * 
	 * @author kouyi
	 * @param date
	 *            日期参数
	 * @param amount
	 *            天数
	 * @return 指定日期前n天的日期
	 */
	public static Date getLastDay(Date date, int amount) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, amount * -1);
			return cal.getTime();
		}
		return null;
	}

	/**
	 * 获取指定日期后n天的日期
	 * 
	 * @author kouyi
	 * @param date
	 *            日期参数
	 * @param amount
	 *            天数
	 * @return 指定日期后n天的日期
	 */
	public static Date getAfterDay(Date date, int amount) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, amount);
			return cal.getTime();
		}
		return null;
	}

	/**
	 * 求两个日期相隔的天数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDifferDay(Date start, Date end) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			DateFormat formate = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			start = df.parse(df.format(start));
			end = formate.parse(formate.format(end));
			int day = (int) ((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24));
			return day;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取两个日期相差的分钟
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getInterval(Date start, Date end) {
		return (end.getTime() - start.getTime()) / (1000 * 60);
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String getWeekOf(Date dt) {
		SimpleDateFormat sf = new SimpleDateFormat("EEEE");
		return sf.format(dt);
	}

	/**
	 * 一天的0点的时间戳
	 * 
	 * @param date
	 * @return
	 */
	public static long getDayBeginTime(Date date) {
		long timestamp = parseDate(formatDate(date, DATE_FORMAT), DATE_FORMAT).getTime();
		return timestamp;
	}

	/**
	 * js时间格式格式化
	 * 
	 * @author kouyi
	 * @param time
	 * @return
	 */
	public static Date getDateTime(String time) {
		if (!StringUtils.isBlank(time)) {
			String[] temp = time.split(",");
			int M = Integer.valueOf(temp[1]) + 1;
			int d = Integer.valueOf(temp[2]);
			int h = Integer.valueOf(temp[3]);
			int m = Integer.valueOf(temp[4]);
			int s = Integer.valueOf(temp[5]);
			return parseDate(temp[0] + "-" + M + "-" + d + " " + h + ":" + m + ":" + s, DATETIME_FORMAT);
		}
		return null;
	}
	
	/**
     * 获取指定时间差的日期
     *
     * @param date date 基准日期
     * @param day  相差天数
     * @return Date, 返回的日期
     */
    public static Date addDay(Date date, int day) {
        Date result = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        result = new Date(cal.getTime().getTime());
        return result;
    }
	
	/**
	 * 指定日期加减N个月
	 * 
	 * @author kouyi
	 * @param date 日期参数
	 * @param month 月数
	 * @return
	 */
	public static Date addMonth(Date date, int monthnum) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, monthnum);
			return cal.getTime();
		}
		return null;
	}
	
	/**
	 * 指定日期加减N年
	 * 
	 * @author kouyi
	 * @param date 日期参数
	 * @param month 年数
	 * @return
	 */
	public static Date addYear(Date date, int yearnum) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.YEAR, yearnum);
			return cal.getTime();
		}
		return null;
	}
	
	public static void main(String[] args) throws ParseException {
		System.out.println(formatDate(new Date(), "yyyyMMdd"));
		System.out.println(addMonth(new Date(), 1));
	}
}