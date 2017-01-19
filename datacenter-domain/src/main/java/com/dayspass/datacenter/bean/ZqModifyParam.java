package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * ModifyParam.java描述了8小时内的赛程删除、比赛时间修改记录
 * 
 * @author zcb
 * @version 1.0.0, 2016-01-22
 */
public class ZqModifyParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int ID; //比赛id
	@XmlElement
	private String type; //所属联赛id
	@XmlElement
	private String matchtime; //球队简体名
	@XmlElement
	private String oprTime; //球队繁体名
	
	
	public ZqModifyParam() {
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
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	@XmlTransient
	public String getMatchtime() {
		return matchtime;
	}


	public void setMatchtime(String matchtime) {
		this.matchtime = matchtime;
	}

	@XmlTransient
	public String getOprTime() {
		return oprTime;
	}


	public void setOprTime(String oprTime) {
		this.oprTime = oprTime;
	}
	
	
	
}
