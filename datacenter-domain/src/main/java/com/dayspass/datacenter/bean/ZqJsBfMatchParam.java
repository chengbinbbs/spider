package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * JsBfMatchParam.java描述了解析比赛对阵xml对应参数
 * 
 * @author zcb
 * @version 1.0.0, 2016-01-22
 */
public class ZqJsBfMatchParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int ID; //球探id
	@XmlElement
	private String color; //颜色
	@XmlElement
	private int leagueID; //联赛id
	@XmlElement
	private Short kind; //类型：1联赛，2杯赛
	@XmlElement
	private Short level; //是否是重要比赛（1表示重要赛事，0表示一般赛事）
	@XmlElement
	private String league; //赛事类型 简体名，繁体名，英文名 
	@XmlElement
	private String subLeague; 
	@XmlElement
	private String time; //比赛时间
	@XmlElement
	private String time2; //开场时间
	@XmlElement
	private String home; //主队简体名，繁体名， 英文名，主队ID
	@XmlElement
	private String away; //客队 简体名，繁体名， 英文名，客队ID
	@XmlElement
	private Short state; //比赛状态 0:未开,1:上半场,2:中场,3:下半场,4,加时，-11:待定,-12:腰斩,-13:中断,-14:推迟,-1:完场，-10取消
	@XmlElement
	private Short homeScore; //主队比分
	@XmlElement
	private Short awayScore; //客队比分
	@XmlElement
	private Short bc1; //主队上半场比分
	@XmlElement
	private Short bc2; //客队上半场比分
	@XmlElement
	private Short red1; //主队红牌
	@XmlElement
	private Short red2; //客队红牌
	@XmlElement
	private Short yellow1; //主队黄牌
	@XmlElement
	private Short yellow2; //客队黄牌
	@XmlElement
	private String order1; //主队排名
	@XmlElement
	private String order2; //客队排名
	@XmlElement
	private String explain; //比赛说明
	@XmlElement
	private boolean zl; //是否中立场
	@XmlElement
	private String tv; //电视直播
	@XmlElement
	private int lineup; //是否有阵容: 1为有
	@XmlElement
	private String explain2; //比赛说明（加时点球等）
	
	public ZqJsBfMatchParam() {
		super();
	}
	/**
	 * 检查正在进行场次数据有效性
	 * @return
	 */
	public boolean isValidMatch() {
		return state == 0;
	}
	
	@XmlTransient
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	@XmlTransient
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@XmlTransient
	public int getLeagueID() {
		return leagueID;
	}
	public void setLeagueID(int leagueID) {
		this.leagueID = leagueID;
	}
	@XmlTransient
	public Short getKind() {
		return kind;
	}
	public void setKind(Short kind) {
		this.kind = kind;
	}
	@XmlTransient
	public Short getLevel() {
		return level;
	}
	public void setLevel(Short level) {
		this.level = level;
	}
	@XmlTransient
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	@XmlTransient
	public String getSubLeague() {
		return subLeague;
	}
	public void setSubLeague(String subLeague) {
		this.subLeague = subLeague;
	}
	@XmlTransient
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@XmlTransient
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
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
	@XmlTransient
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	@XmlTransient
	public Short getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(Short homeScore) {
		this.homeScore = homeScore;
	}
	@XmlTransient
	public Short getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(Short awayScore) {
		this.awayScore = awayScore;
	}
	@XmlTransient
	public Short getBc1() {
		return bc1;
	}
	public void setBc1(Short bc1) {
		this.bc1 = bc1;
	}
	@XmlTransient
	public Short getBc2() {
		return bc2;
	}
	public void setBc2(Short bc2) {
		this.bc2 = bc2;
	}
	@XmlTransient
	public Short getRed1() {
		return red1;
	}
	public void setRed1(Short red1) {
		this.red1 = red1;
	}
	@XmlTransient
	public Short getRed2() {
		return red2;
	}
	public void setRed2(Short red2) {
		this.red2 = red2;
	}
	@XmlTransient
	public Short getYellow1() {
		return yellow1;
	}
	public void setYellow1(Short yellow1) {
		this.yellow1 = yellow1;
	}
	@XmlTransient
	public Short getYellow2() {
		return yellow2;
	}
	public void setYellow2(Short yellow2) {
		this.yellow2 = yellow2;
	}
	@XmlTransient
	public String getOrder1() {
		return order1;
	}
	public void setOrder1(String order1) {
		this.order1 = order1;
	}
	@XmlTransient
	public String getOrder2() {
		return order2;
	}
	public void setOrder2(String order2) {
		this.order2 = order2;
	}
	@XmlTransient
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	@XmlTransient
	public boolean getZl() {
		return zl;
	}
	public void setZl(boolean zl) {
		this.zl = zl;
	}
	@XmlTransient
	public String getTv() {
		return tv;
	}
	public void setTv(String tv) {
		this.tv = tv;
	}
	@XmlTransient
	public int getLineup() {
		return lineup;
	}
	public void setLineup(int lineup) {
		this.lineup = lineup;
	}
	@XmlTransient
	public String getExplain2() {
		return explain2;
	}
	public void setExplain2(String explain2) {
		this.explain2 = explain2;
	}
	
	public boolean compare(ZqJsBfMatchParam jb){
		return jb.getState() != this.getState() || jb.getHomeScore() != this.getHomeScore() || jb.getAwayScore() != this.getAwayScore()
				|| jb.getRed1() != this.getRed1() || jb.getRed2() != this.getRed2() || jb.getYellow1() != this.getYellow1() || jb.getYellow2() != this.getYellow2();
	}
	
}
