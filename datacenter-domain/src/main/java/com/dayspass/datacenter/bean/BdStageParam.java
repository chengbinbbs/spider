package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

public class BdStageParam implements Serializable{

	private static final long serialVersionUID = 1L;
	@XmlAttribute(name = "pid")
	private String pid;
	@XmlAttribute(name = "et")
	private String et;
	@XmlAttribute(name = "fet")
	private String fet;
	@XmlAttribute(name = "flag")
	private int flag;
	@XmlAttribute(name = "st")
	private int st;
	@XmlAttribute(name = "opencode")
	private String opencode;
	@XmlAttribute(name = "at")
	private String at;
	
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
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@XmlTransient
	public int getSt() {
		return st;
	}
	public void setSt(int st) {
		this.st = st;
	}
	@XmlTransient
	public String getOpencode() {
		return opencode;
	}
	public void setOpencode(String opencode) {
		this.opencode = opencode;
	}
	@XmlTransient
	public String getAt() {
		return at;
	}
	public void setAt(String at) {
		this.at = at;
	}
	
}
