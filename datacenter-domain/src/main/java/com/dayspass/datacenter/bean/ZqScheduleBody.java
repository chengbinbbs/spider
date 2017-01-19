package com.dayspass.datacenter.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "list")
public class ZqScheduleBody implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "match")
	private List<ZqScheduleParam> match;		// xml标签match
	
	@XmlTransient	
	public List<ZqScheduleParam> getMatch() {
		return match;
	}
	public void setMatch(List<ZqScheduleParam> match) {
		this.match = match;
	}
	
}
