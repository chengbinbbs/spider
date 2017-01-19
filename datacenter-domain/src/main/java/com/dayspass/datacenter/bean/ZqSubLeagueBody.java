package com.dayspass.datacenter.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "list")
public class ZqSubLeagueBody implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "Item")
	private List<ZqSubLeagueParam> league;	// xml标签league
	
	@XmlTransient	
	public List<ZqSubLeagueParam> getLeague() {
		return league;
	}
	public void setLeague(List<ZqSubLeagueParam> league) {
		this.league = league;
	}
	
	
	
	
}
