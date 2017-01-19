package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 伤停、预测、赛前简报
 * @author aaronxu
 * @date 2016-01-29
 */
public class ZqInjuryParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement(name = "ID")
	private int matchId;
	@XmlElement(name = "Briefing")
	private String briefing;
	@XmlElement(name = "Recommend")
	private String recommend;
	@XmlElement(name = "HomeStop")
	private String homeStop;
	@XmlElement(name = "AwayStop")
	private String awayStop;
	@XmlElement(name = "HomeInjure")
	private String homeInjure;
	@XmlElement(name = "AwayInjure")
	private String awayInjure;

	@XmlTransient
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	@XmlTransient
	public String getBriefing() {
		return briefing;
	}
	public void setBriefing(String briefing) {
		this.briefing = briefing;
	}
	@XmlTransient
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	@XmlTransient
	public String getHomeStop() {
		return homeStop;
	}
	public void setHomeStop(String homeStop) {
		this.homeStop = homeStop;
	}
	@XmlTransient
	public String getAwayStop() {
		return awayStop;
	}
	public void setAwayStop(String awayStop) {
		this.awayStop = awayStop;
	}
	@XmlTransient
	public String getHomeInjure() {
		return homeInjure;
	}
	public void setHomeInjure(String homeInjure) {
		this.homeInjure = homeInjure;
	}
	@XmlTransient
	public String getAwayInjure() {
		return awayInjure;
	}
	public void setAwayInjure(String awayInjure) {
		this.awayInjure = awayInjure;
	}
	
	
}
