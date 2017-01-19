package com.dayspass.datacenter.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "list")
public class ZqLineUpBody implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "match")
	private List<ZqLineUpParam> match;		// xml标签match
	
	@XmlTransient	
	public List<ZqLineUpParam> getMatch() {
		return match;
	}
	public void setMatch(List<ZqLineUpParam> match) {
		this.match = match;
	}
	
}
