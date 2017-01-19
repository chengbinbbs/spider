package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * TeamParam.java描述了解析球队信息xml对应参数
 * 
 * @author zcb
 * @version 1.0.0, 2016-01-22
 */
public class ZqTeamParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int id; //球队id
	@XmlElement
	private int lsID; //所属联赛id
	@XmlElement
	private String g; //球队简体名
	@XmlElement
	private String b; //球队繁体名
	@XmlElement
	private String e; //球队英文名
	@XmlElement
	private String Found; //球队成立日期
	@XmlElement
	private String Area; //所在地
	@XmlElement
	private String gym; //球场
	@XmlElement
	private int Capacity; //球场容量
	@XmlElement
	private String Flag; //队标
	@XmlElement
	private String addr; //地址
	@XmlElement
	private String URL; //球队网址
	@XmlElement
	private String master; //主教练
	
	public ZqTeamParam() {
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
	public int getLsID() {
		return lsID;
	}

	public void setLsID(int lsID) {
		this.lsID = lsID;
	}
	@XmlTransient
	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}
	@XmlTransient
	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}
	@XmlTransient
	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}
	@XmlTransient
	public String getFound() {
		return Found;
	}

	public void setFound(String found) {
		Found = found;
	}
	@XmlTransient
	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}
	@XmlTransient
	public String getGym() {
		return gym;
	}

	public void setGym(String gym) {
		this.gym = gym;
	}
	@XmlTransient
	public int getCapacity() {
		return Capacity;
	}

	public void setCapacity(int capacity) {
		Capacity = capacity;
	}
	@XmlTransient
	public String getFlag() {
		return Flag;
	}

	public void setFlag(String flag) {
		Flag = flag;
	}
	@XmlTransient
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	@XmlTransient
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	@XmlTransient
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
	
	
}
