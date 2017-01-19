package com.dayspass.datacenter.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "list")
public class ZqTeamBody implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "i")
	private List<ZqTeamParam> team;		// xml标签match
	
	@XmlTransient	
	public List<ZqTeamParam> getTeam() {
		return team;
	}
	public void setTeam(List<ZqTeamParam> team) {
		this.team = team;
	}
	
}
