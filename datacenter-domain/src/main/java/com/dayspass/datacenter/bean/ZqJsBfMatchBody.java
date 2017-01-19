package com.dayspass.datacenter.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "list")
public class ZqJsBfMatchBody implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "match")
	private List<ZqJsBfMatchParam> match;		// xml标签match
	
	@XmlTransient	
	public List<ZqJsBfMatchParam> getMatch() {
		return match;
	}
	public void setMatch(List<ZqJsBfMatchParam> match) {
		this.match = match;
	} 
	
}
