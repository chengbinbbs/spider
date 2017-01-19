package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "list")
public class ZqLeagueBody implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "match")
	private List<ZqLeagueParam> league;// xml标签match
	
	@XmlTransient	
	public List<ZqLeagueParam> getLeague() {
		return league;
	}
	public void setLeague(List<ZqLeagueParam> league) {
		this.league = league;
	}		
	
}
