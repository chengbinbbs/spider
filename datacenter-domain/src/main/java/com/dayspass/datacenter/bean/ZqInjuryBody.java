package com.dayspass.datacenter.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 伤停、预测、赛前简报
 * @author aaronxu
 * @date 2016-01-29
 */
@XmlRootElement(name = "list")
public class ZqInjuryBody implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "match")
	private List<ZqInjuryParam> injury;

	@XmlTransient
	public List<ZqInjuryParam> getInjury() {
		return injury;
	}

	public void setInjury(List<ZqInjuryParam> injury) {
		this.injury = injury;
	}

}
