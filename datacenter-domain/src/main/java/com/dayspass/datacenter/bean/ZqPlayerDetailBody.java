package com.dayspass.datacenter.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "list")
public class ZqPlayerDetailBody implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "play")
	private List<ZqPlayerDetailParam> play; // xml标签play
	
	@XmlTransient
	public List<ZqPlayerDetailParam> getPlay() {
		return play;
	}
	public void setPlay(List<ZqPlayerDetailParam> play) {
		this.play = play;
	}		
}
