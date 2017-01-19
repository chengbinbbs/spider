package com.dayspass.datacenter.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "list")
public class ZqPlayerBody implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "i")
	private List<ZqPlayerParam> player;		// xml标签match
	
	@XmlTransient	
	public List<ZqPlayerParam> getPlayer() {
		return player;
	}
	public void setPlayer(List<ZqPlayerParam> player) {
		this.player = player;
	}
}
