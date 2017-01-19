package com.dayspass.datacenter.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * LeagueParam.java描述了解析联赛杯赛xml对应参数
 * 
 * @author zcb
 * @version 1.0.0, 2016-01-22
 */
public class ZqLeagueParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int id; //联赛id
	@XmlElement
	private String color; //颜色值
	@XmlElement
	private String gb_short; //简体名简称
	@XmlElement
	private String big_short; //繁体名简称
	@XmlElement
	private String en_short; //英文名简称
	@XmlElement
	private String gb; //简体名全称
	@XmlElement
	private String big; //繁体名全称
	@XmlElement
	private String en; //英文全称
	@XmlElement
	private Short type; //类型，1：联赛，2：杯赛
	@XmlElement
	private Short sum_round; //总轮次
	@XmlElement
	private Short curr_round; //当前轮
	@XmlElement
	private String Curr_matchSeason; //当前赛季
	@XmlElement
	private int countryID; //所属国家id
	@XmlElement
	private String country; //所属国家名
	@XmlElement
	private Short areaID; //所属区域，1欧洲联赛,2美洲联赛,3亚洲联赛,4大洋洲联赛,5非洲联赛
	
	public ZqLeagueParam() {
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
	public String getGb_short() {
		return gb_short;
	}

	public void setGb_short(String gb_short) {
		this.gb_short = gb_short;
	}
	@XmlTransient
	public String getBig_short() {
		return big_short;
	}

	public void setBig_short(String big_short) {
		this.big_short = big_short;
	}
	@XmlTransient
	public String getEn_short() {
		return en_short;
	}

	public void setEn_short(String en_short) {
		this.en_short = en_short;
	}
	@XmlTransient
	public String getGb() {
		return gb;
	}

	public void setGb(String gb) {
		this.gb = gb;
	}
	@XmlTransient
	public String getBig() {
		return big;
	}

	public void setBig(String big) {
		this.big = big;
	}
	@XmlTransient
	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}
	@XmlTransient
	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}
	@XmlTransient
	public Short getSum_round() {
		return sum_round;
	}

	public void setSum_round(Short sum_round) {
		this.sum_round = sum_round;
	}
	@XmlTransient
	public Short getCurr_round() {
		return curr_round;
	}

	public void setCurr_round(Short curr_round) {
		this.curr_round = curr_round;
	}
	@XmlTransient
	public String getCurr_matchSeason() {
		return Curr_matchSeason;
	}

	public void setCurr_matchSeason(String curr_matchSeason) {
		Curr_matchSeason = curr_matchSeason;
	}
	@XmlTransient
	public int getCountryID() {
		return countryID;
	}

	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}
	@XmlTransient
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	@XmlTransient
	public Short getAreaID() {
		return areaID;
	}

	public void setAreaID(Short areaID) {
		this.areaID = areaID;
	}
	
}
