package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * TechStaticsParam.java描述了解析统计统计xml对应参数
 * 
 * @author zcb
 * @version 1.0.0, 2016-01-22
 */
public class ZqTechStaticsParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int id; //球探id
	@XmlElement
	private String TechnicCount; //颜色
	
	public ZqTechStaticsParam() {
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
	public String getTechnicCount() {
		return TechnicCount;
	}
	public void setTechnicCount(String technicCount) {
		TechnicCount = technicCount;
	}
	
}
