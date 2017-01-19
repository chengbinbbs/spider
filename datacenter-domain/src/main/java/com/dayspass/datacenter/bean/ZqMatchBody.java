package com.dayspass.datacenter.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "list")
public class ZqMatchBody implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "match")
	private List<ZqMatchParam> match;// xml标签match
	
	@XmlTransient	
	public List<ZqMatchParam> getMatch() {
		return match;
	}
	public void setMatch(List<ZqMatchParam> match) {
		this.match = match;
	}
}
