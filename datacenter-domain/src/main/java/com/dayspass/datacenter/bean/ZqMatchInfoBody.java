package com.dayspass.datacenter.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "match")
public class ZqMatchInfoBody implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "i")
	private List<ZqMatchInfoParam> match;		// xml标签i
	
	@XmlTransient	
	public List<ZqMatchInfoParam> getMatch() {
		return match;
	}
	public void setMatch(List<ZqMatchInfoParam> match) {
		this.match = match;
	} 
	
}
