package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * ScheduleParam.java描述了球员统计的比赛列表信息xml对应参数
 * 
 * @author zcb
 * @version 1.0.0, 2016-01-22
 */
public class ZqScheduleParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int ScheduleID; //比赛id
	@XmlElement
	private String league; //联赛名称
	@XmlElement
	private String matchtime; //比赛时间
	@XmlElement
	private String home; //主队名称
	@XmlElement
	private String away; //客队名称
	
	public ZqScheduleParam() {
		super();
	}
	@XmlTransient
	public int getScheduleID() {
		return ScheduleID;
	}

	public void setScheduleID(int scheduleID) {
		ScheduleID = scheduleID;
	}
	@XmlTransient
	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}
	@XmlTransient
	public String getMatchtime() {
		return matchtime;
	}

	public void setMatchtime(String matchtime) {
		this.matchtime = matchtime;
	}
	@XmlTransient
	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}
	@XmlTransient
	public String getAway() {
		return away;
	}

	public void setAway(String away) {
		this.away = away;
	}
	
}
