package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * MatchInfoParam.java描述了解析彩票赛事与球探网的关联关系
 * 
 * @author zcb
 * @version 1.0.0, 2016-01-22
 */
public class ZqMatchInfoParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement
	private String LotteryName; //彩种名称
	@XmlElement
	private String IssueNum; //彩种期次
	@XmlElement
	private String ID; //赛事编号
	@XmlElement
	private int ID_bet007; //球探id
	@XmlElement
	private String sport; //赛事类型，篮球、足球
	@XmlElement
	private String time; //比赛时间
	@XmlElement
	private String Home; //主队名称
	@XmlElement
	private String Away; //客队名称
	@XmlElement
	private int HomeID; //主队id
	@XmlElement
	private int AwayID; //客队id
	@XmlElement
	private boolean Turn; //是否与球探的比赛主客相反
	
	
	public ZqMatchInfoParam() {
		super();
	}
	
	@XmlTransient
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	@XmlTransient
	public String getLotteryName() {
		return LotteryName;
	}

	public void setLotteryName(String lotteryName) {
		LotteryName = lotteryName;
	}
	@XmlTransient
	public String getIssueNum() {
		return IssueNum;
	}

	public void setIssueNum(String issueNum) {
		IssueNum = issueNum;
	}
	@XmlTransient
	public int getID_bet007() {
		return ID_bet007;
	}

	public void setID_bet007(int iD_bet007) {
		ID_bet007 = iD_bet007;
	}
	@XmlTransient
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	@XmlTransient
	public String getHome() {
		return Home;
	}

	public void setHome(String home) {
		Home = home;
	}
	@XmlTransient
	public String getAway() {
		return Away;
	}

	public void setAway(String away) {
		Away = away;
	}
	@XmlTransient
	public int getHomeID() {
		return HomeID;
	}

	public void setHomeID(int homeID) {
		HomeID = homeID;
	}
	@XmlTransient
	public int getAwayID() {
		return AwayID;
	}

	public void setAwayID(int awayID) {
		AwayID = awayID;
	}
	@XmlTransient
	public boolean isTurn() {
		return Turn;
	}

	public void setTurn(boolean turn) {
		Turn = turn;
	}
	@XmlTransient
	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}
	
	
}
