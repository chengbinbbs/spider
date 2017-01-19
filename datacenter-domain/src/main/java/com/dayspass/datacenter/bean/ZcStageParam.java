package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

public class ZcStageParam implements Serializable{

	private static final long serialVersionUID = 1L;
	@XmlAttribute(name = "pid")
	private String pid;
	@XmlAttribute(name = "et")
	private String et;
	@XmlAttribute(name = "fet")
	private String fet;
	@XmlAttribute(name = "at")
	private String at;
	@XmlAttribute(name = "sale")
	private int sale;
	
	@XmlTransient
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	@XmlTransient
	public String getEt() {
		return et;
	}
	public void setEt(String et) {
		this.et = et;
	}
	@XmlTransient
	public String getFet() {
		return fet;
	}
	public void setFet(String fet) {
		this.fet = fet;
	}
	@XmlTransient
	public String getAt() {
		return at;
	}
	public void setAt(String at) {
		this.at = at;
	}
	@XmlTransient
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
}
