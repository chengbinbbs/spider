package com.dayspass.datacenter.command;

import java.io.Serializable;
import java.util.Date;

/**
 * 及时变化的比分
 * @user zhangcb
 * @date 2016年2月16日
 */
public class ChangeRoundCommand implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int sid; //球探比赛id
	private short matchstate; //比赛状态 0:未开,1:上半场,2:中场,3:下半场,4,加时，-11:待定,-12:腰斩,-13:中断,-14:推迟,-1:完场，-10取消
	private short homescore; //主队进球数
	private short guestscore; //客队进球数
	private short homeRed; //主队红牌
	private short guestRed; //客队红牌
	private short homeYellow; //主队黄牌
	private short guestYellow; //客队黄牌
	private String matchtime; //比赛时间
	private String half; //半场比分
	private Date date; //比赛结束时间
	
	public short getMatchstate() {
		return matchstate;
	}
	public void setMatchstate(short matchstate) {
		this.matchstate = matchstate;
	}
	public short getHomescore() {
		return homescore;
	}
	public void setHomescore(short homescore) {
		this.homescore = homescore;
	}
	public short getGuestscore() {
		return guestscore;
	}
	public void setGuestscore(short guestscore) {
		this.guestscore = guestscore;
	}
	public String getMatchtime() {
		return matchtime;
	}
	public void setMatchtime(String matchtime) {
		this.matchtime = matchtime;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public short getHomeRed() {
		return homeRed;
	}
	public void setHomeRed(short homeRed) {
		this.homeRed = homeRed;
	}
	public short getGuestRed() {
		return guestRed;
	}
	public void setGuestRed(short guestRed) {
		this.guestRed = guestRed;
	}
	public short getHomeYellow() {
		return homeYellow;
	}
	public void setHomeYellow(short homeYellow) {
		this.homeYellow = homeYellow;
	}
	public short getGuestYellow() {
		return guestYellow;
	}
	public void setGuestYellow(short guestYellow) {
		this.guestYellow = guestYellow;
	}
	
	public String getHalf() {
		return half;
	}
	public void setHalf(String half) {
		this.half = half;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
