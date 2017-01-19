package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * LineUpParam.java描述了解析出场阵容信息xml对应参数
 * 
 * @author zcb
 * @version 1.0.0, 2016-01-22
 */
public class ZqLineUpParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int ID; //比赛id
	@XmlElement
	private String homeArray; //主队阵式 例如 442  433等
	@XmlElement
	private String awayArray; //客队阵式
	@XmlElement
	private String HomeLineup_cn; //主队首发
	@XmlElement
	private String AwayLineup_cn; //客队首发
	@XmlElement
	private String HomeBackup_cn; //主队后备
	@XmlElement
	private String AwayBackup_cn; //客队后备
	@XmlElement
	private String HomeLineup_big5; //主队首发 繁体
	@XmlElement
	private int AwayLineup_big5; //客队首发 繁体
	@XmlElement
	private String HomeBackup_big5; //主队后备 繁体
	@XmlElement
	private String AwayBackup_big5; //客队后备 繁体
	
	
	public ZqLineUpParam() {
		super();
	}

	@XmlTransient
	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}

	@XmlTransient
	public String getHomeArray() {
		return homeArray;
	}


	public void setHomeArray(String homeArray) {
		this.homeArray = homeArray;
	}

	@XmlTransient
	public String getAwayArray() {
		return awayArray;
	}


	public void setAwayArray(String awayArray) {
		this.awayArray = awayArray;
	}

	@XmlTransient
	public String getHomeLineup_cn() {
		return HomeLineup_cn;
	}


	public void setHomeLineup_cn(String homeLineup_cn) {
		HomeLineup_cn = homeLineup_cn;
	}

	@XmlTransient
	public String getAwayLineup_cn() {
		return AwayLineup_cn;
	}


	public void setAwayLineup_cn(String awayLineup_cn) {
		AwayLineup_cn = awayLineup_cn;
	}

	@XmlTransient
	public String getHomeBackup_cn() {
		return HomeBackup_cn;
	}


	public void setHomeBackup_cn(String homeBackup_cn) {
		HomeBackup_cn = homeBackup_cn;
	}

	@XmlTransient
	public String getAwayBackup_cn() {
		return AwayBackup_cn;
	}


	public void setAwayBackup_cn(String awayBackup_cn) {
		AwayBackup_cn = awayBackup_cn;
	}

	@XmlTransient
	public String getHomeLineup_big5() {
		return HomeLineup_big5;
	}


	public void setHomeLineup_big5(String homeLineup_big5) {
		HomeLineup_big5 = homeLineup_big5;
	}

	@XmlTransient
	public int getAwayLineup_big5() {
		return AwayLineup_big5;
	}


	public void setAwayLineup_big5(int awayLineup_big5) {
		AwayLineup_big5 = awayLineup_big5;
	}

	@XmlTransient
	public String getHomeBackup_big5() {
		return HomeBackup_big5;
	}


	public void setHomeBackup_big5(String homeBackup_big5) {
		HomeBackup_big5 = homeBackup_big5;
	}

	@XmlTransient
	public String getAwayBackup_big5() {
		return AwayBackup_big5;
	}


	public void setAwayBackup_big5(String awayBackup_big5) {
		AwayBackup_big5 = awayBackup_big5;
	}
	
}
