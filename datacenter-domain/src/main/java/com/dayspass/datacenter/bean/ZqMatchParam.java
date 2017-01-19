package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * MatchParam.java描述了解析赛事xml对应参数
 * 
 * @author zcb
 * @version 1.0.0, 2016-01-22
 */
public class ZqMatchParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "a")
	private int id; //比赛id
	@XmlElement(name = "b")
	private String color; //颜色值
	@XmlElement(name = "c")
	private String league; //联赛国语名,联赛繁体名,联赛英文名,联赛ID,是否精简版( 0:完全版、1:精简)
	@XmlElement(name = "d")
	private String mtime; //比赛时间
	@XmlElement(name = "e")
	private String type; //联赛子类型
	@XmlElement(name = "f")
	private Short state; //比赛状态
	@XmlElement(name = "h")
	private String homeName; //主队国语名, 主队繁体名, 主队英文名, 主队ID
	@XmlElement(name = "i")
	private String guestName; //客队国语名, 客队繁体名, 客队英文名, 客队ID
	@XmlElement(name = "j")
	private Short homeScore; //主队比分
	@XmlElement(name = "k")
	private Short guestScore; //客队比分
	@XmlElement(name = "l")
	private Short homeHalfScore; //主队半场比分
	@XmlElement(name = "m")
	private Short guestHalfScore; //客队半场比分
	@XmlElement(name = "n")
	private Short homeRed; //主队红牌
	@XmlElement(name = "o")
	private Short guestRed; //客队红牌
	@XmlElement(name = "p")
	private String homeOrder; //主队排名
	@XmlElement(name = "q")
	private String guestOrder; //客队排名
	@XmlElement(name = "r")
	private String explain; //<![CDATA[赛事说明]]>
	@XmlElement(name = "s")
	private String round; //轮次/分组名，例如 4/8强/准决赛
	@XmlElement(name = "t")
	private String location; //比赛地点
	@XmlElement(name = "u")
	private String weather; //天气
	@XmlElement(name = "v")
	private String icon; //天气图标
	@XmlElement(name = "w")
	private String temperature; //温度
	@XmlElement(name = "x")
	private String season; //赛季
	@XmlElement(name = "y")
	private String group; //小组分组
	@XmlElement(name = "z")
	private boolean neutrality; //是否中立场
	
	public ZqMatchParam() {
		super();
	}
	
	@XmlTransient
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@XmlTransient
	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}
	@XmlTransient
	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	@XmlTransient
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@XmlTransient
	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}
	@XmlTransient
	public String getHomeName() {
		return homeName;
	}

	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}
	@XmlTransient
	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	@XmlTransient
	public Short getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(Short homeScore) {
		this.homeScore = homeScore;
	}
	@XmlTransient
	public Short getGuestScore() {
		return guestScore;
	}

	public void setGuestScore(Short guestScore) {
		this.guestScore = guestScore;
	}
	@XmlTransient
	public Short getHomeHalfScore() {
		return homeHalfScore;
	}

	public void setHomeHalfScore(Short homeHalfScore) {
		this.homeHalfScore = homeHalfScore;
	}
	@XmlTransient
	public Short getGuestHalfScore() {
		return guestHalfScore;
	}

	public void setGuestHalfScore(Short guestHalfScore) {
		this.guestHalfScore = guestHalfScore;
	}
	@XmlTransient
	public Short getHomeRed() {
		return homeRed;
	}

	public void setHomeRed(Short homeRed) {
		this.homeRed = homeRed;
	}
	@XmlTransient
	public Short getGuestRed() {
		return guestRed;
	}

	public void setGuestRed(Short guestRed) {
		this.guestRed = guestRed;
	}
	@XmlTransient
	public String getHomeOrder() {
		return homeOrder;
	}

	public void setHomeOrder(String homeOrder) {
		this.homeOrder = homeOrder;
	}
	@XmlTransient
	public String getGuestOrder() {
		return guestOrder;
	}

	public void setGuestOrder(String guestOrder) {
		this.guestOrder = guestOrder;
	}
	@XmlTransient
	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	@XmlTransient
	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}
	@XmlTransient
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	@XmlTransient
	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}
	@XmlTransient
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	@XmlTransient
	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	@XmlTransient
	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}
	@XmlTransient
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	@XmlTransient
	public boolean isNeutrality() {
		return neutrality;
	}

	public void setNeutrality(boolean neutrality) {
		this.neutrality = neutrality;
	}
	
}
