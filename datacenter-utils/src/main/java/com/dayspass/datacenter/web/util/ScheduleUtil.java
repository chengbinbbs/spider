package com.dayspass.datacenter.web.util;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.domain.Match;

public class ScheduleUtil {
	public static Map<String, String> show = new HashMap<>();
	public static String BEGINFORMAT = "yyyy-MM-dd 08:00:00";
	public static String ENDFORMAT = "yyyy-MM-dd 10:30:00";
	public static String CHARTSET_UTF8 = "UTF-8";
	public static String CHARTSET_GBK = "GBK";
	public static int MATCHEXITIME = 30*60*60;
	public static int JSBFEXITIME = 60*60;
	public static String KEYSCHDULE = "a_";
	public static String KEYJSBF = "b_";
	public static String KEYODDSDX = "c_";
	public static String KEYODDSID = "d_";
	public static String KEYANALYSIS = "e_";
	public static String KEYMODEL = "f_";
	
	static {
		show.put("sclassId", "sclassId");
		show.put("matchSeason", "matchSeason");
		show.put("homeTeamId", "homeTeamId");
		show.put("guestTeamId", "guestTeamId");
		show.put("matchTime", "matchTime");
		show.put("homeOrder", "homeOrder");
		show.put("guestOrder", "guestOrder");
		show.put("recommendCount", "recommendCount");
		show.put("hot", "hot");
		show.put("updateTime", "updateTime");
		show.put("flag", "flag");
	}
	
	/**
	 * 当前对阵数据与缓存中数据同步
	 * @param preschedule
	 * @param cursche
	 */
	public static void schesynchro(Match preschedule, Match curschedule) {
		if(StringUtils.isEmpty(preschedule) || StringUtils.isEmpty(curschedule)) {
			return;
		}
		if(!StringUtils.isEmpty(curschedule.getMatchTime()) && !StringUtils.isEmpty(preschedule.getMatchTime()) && preschedule.getMatchTime().getTime() != curschedule.getMatchTime().getTime()) {
			preschedule.setMatchTime(curschedule.getMatchTime());
		}
		if(!StringUtils.isEmpty(curschedule.getMatchBeginTime()) && !StringUtils.isEmpty(preschedule.getMatchBeginTime()) && preschedule.getMatchBeginTime().getTime() != curschedule.getMatchBeginTime().getTime()) {
			preschedule.setMatchBeginTime(curschedule.getMatchBeginTime());
		}
		if(preschedule.getMatchState() != -1 && preschedule.getMatchState() != curschedule.getMatchState()) {
			preschedule.setMatchState(curschedule.getMatchState());
		}
		if(preschedule.getHomeScore() != curschedule.getHomeScore()) {
			preschedule.setHomeScore(curschedule.getHomeScore());
		}
		if(preschedule.getGuestScore() != curschedule.getGuestScore()) {
			preschedule.setGuestScore(curschedule.getGuestScore());
		}
		if(preschedule.getHomeHalfScore() != curschedule.getHomeHalfScore()) {
			preschedule.setHomeHalfScore(curschedule.getHomeHalfScore());
		}
		if(preschedule.getGuestHalfScore() != curschedule.getGuestHalfScore()) {
			preschedule.setGuestHalfScore(curschedule.getGuestHalfScore());
		}
		if(preschedule.getHomeRed() != curschedule.getHomeRed()) {
			preschedule.setHomeRed(curschedule.getHomeRed());
		}
		if(preschedule.getGuestRed() != curschedule.getGuestRed()) {
			preschedule.setGuestRed(curschedule.getGuestRed());
		}
		if(preschedule.getHomeYellow() != curschedule.getHomeYellow()) {
			preschedule.setHomeYellow(curschedule.getHomeYellow());
		}
		if(preschedule.getGuestYellow() != curschedule.getGuestYellow()) {
			preschedule.setGuestYellow(curschedule.getGuestYellow());
		}
	}
	
	/**
	 * 对阵数据是否发生变化-当日对阵缓存对比
	 * 
	 * @param sche
	 * @return
	 */
	public static String samedm(Match sche) {
		if (sche == null) {
			return "";
		}
		return sche.getScheduleId() + "_" + sche.getMatchTime() + "_" + sche.getMatchBeginTime() + "_"
				+ sche.getMatchState() + "_" + sche.getHomeScore() + "_" + sche.getGuestScore() + "_"
				+ sche.getHomeHalfScore() + "_" + sche.getGuestHalfScore() + "_" + sche.getHomeRed() + "_"
				+ sche.getGuestRed() + "_" + sche.getHomeYellow() + "_" + sche.getGuestYellow() + "_"
				+ sche.getRemark();
	}
	
	/**
	 * 对阵数据是否发生变化-change直播与当日对阵缓存对比
	 * 
	 * @param sche
	 * @return
	 */
	public static String samezm(Match sche) {
		if (sche == null) {
			return "";
		}
		return sche.getScheduleId() + "_" + sche.getMatchBeginTime() + "_" + sche.getMatchState() + "_"
				+ sche.getHomeScore() + "_" + sche.getGuestScore() + "_" + sche.getHomeHalfScore() + "_"
				+ sche.getGuestHalfScore() + "_" + sche.getHomeRed() + "_" + sche.getGuestRed() + "_"
				+ sche.getHomeYellow() + "_" + sche.getGuestYellow();
	}
	
	/**
	 * 即时比分当前期键值
	 * @return
	 */
	public static String jsbfkey(int n) {
		return KEYJSBF + todaystr(n);
	}
	
	/**
	 * 当天格式字符串
	 * @return
	 */
	public static String todaystr(int n) {
		return today().plusDays(n).toString("yyyyMMdd");
	}
	
	/**
	 * 当前期
	 * @return
	 */
	public static DateTime today() {
		DateTime today = new DateTime();
		if (Integer.parseInt(today.toString("HHmm")) < 1030) {
			today = today.minusDays(1);
		}
		return today;
	}
	
	
	/**
	 * 获取查询日期
	 * @param day
	 * @return
	 */
	public static String matchTime(int day) {
		if(day != 0) {
			return today().plusDays(day).toString(ENDFORMAT);
		} else {
			return today().toString(BEGINFORMAT);
		}
	}
	
}
