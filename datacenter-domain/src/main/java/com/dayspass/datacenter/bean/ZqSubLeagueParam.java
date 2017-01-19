package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * SubLeagueParam.java描述了解析子联赛xml对应参数
 * 
 * @author zcb
 * @version 1.0.0, 2016-01-22
 */
public class ZqSubLeagueParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int id; //联赛id
	@XmlElement
	private int subID; //子联赛id
	@XmlElement
	private String Name; //子联赛名称
	@XmlElement
	private int Num; //排序
	@XmlElement
	private int sum_round; //总轮数
	@XmlElement
	private int curr_round; //当前轮数
	@XmlElement
	private boolean IsHaveScore; //是否有积分，1：有，0：无
	@XmlElement
	private String IncludeSeason; //包含了的赛季
	@XmlElement
	private boolean IsCurrentSclass; //是否当前子联赛
	@XmlElement
	private String CurrentSeason; //当前赛季
	@XmlElement
	private boolean IsZu; 
	
	
	public ZqSubLeagueParam() {
		super();
	}

	@XmlTransient
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	@XmlTransient
	public int getSubID() {
		return subID;
	}


	public void setSubID(int subID) {
		this.subID = subID;
	}

	@XmlTransient
	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}

	@XmlTransient
	public int getNum() {
		return Num;
	}


	public void setNum(int num) {
		Num = num;
	}

	@XmlTransient
	public int getSum_round() {
		return sum_round;
	}


	public void setSum_round(int sum_round) {
		this.sum_round = sum_round;
	}

	@XmlTransient
	public int getCurr_round() {
		return curr_round;
	}


	public void setCurr_round(int curr_round) {
		this.curr_round = curr_round;
	}

	@XmlTransient
	public boolean getIsHaveScore() {
		return IsHaveScore;
	}


	public void setIsHaveScore(boolean isHaveScore) {
		IsHaveScore = isHaveScore;
	}

	@XmlTransient
	public String getIncludeSeason() {
		return IncludeSeason;
	}


	public void setIncludeSeason(String includeSeason) {
		IncludeSeason = includeSeason;
	}

	@XmlTransient
	public boolean getIsCurrentSclass() {
		return IsCurrentSclass;
	}


	public void setIsCurrentSclass(boolean isCurrentSclass) {
		IsCurrentSclass = isCurrentSclass;
	}

	@XmlTransient
	public String getCurrentSeason() {
		return CurrentSeason;
	}


	public void setCurrentSeason(String currentSeason) {
		CurrentSeason = currentSeason;
	}

	@XmlTransient
	public boolean getIsZu() {
		return IsZu;
	}


	public void setIsZu(boolean isZu) {
		IsZu = isZu;
	}
	
}
