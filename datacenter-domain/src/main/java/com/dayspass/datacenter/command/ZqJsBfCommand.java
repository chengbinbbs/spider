package com.dayspass.datacenter.command;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 即时比分赛事详情
 * @user zhangcb
 * @date 2016年1月30日
 */
public class ZqJsBfCommand implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer sid;	//比赛id
	
	private Integer leagueId;	//联赛id
    
    private String league;  //联赛名称
    
    private String sort;   //竞彩编号，周一001
    
    private int number;		//比赛排序编号

    private Integer hometeamid; //主队id

    private Integer guestteamid; //客队id

    private String hometeam; //主队名称

    private String guestteam; //客队名称

    private Boolean neutrality; //中立场
    
    @JSONField (format="yyyy-MM-dd HH:mm:ss")  
    private Date matchtime; //开赛时间

    @JSONField (format="yyyy-MM-dd HH:mm:ss")  
    private Date matchtime2; //下半场开赛时间

    private String homeOrder; //主队排名

    private String guestOrder; //客队排名

    private Short matchstate; //比赛状态 0:未开,1:上半场,2:中场,3:下半场,4,加时，-11:待定,-12:腰斩,-13:中断,-14:推迟,-1:完场，-10取消

    private Short homescore; //主队比分

    private Short guestscore; //客队比分

    private Short homehalfscore; //主队半场比分

    private Short guesthalfscore; //客队半场比分

    private Short homeRed; //主队红牌数

    private Short guestRed; //客队红牌数
    
    private Short homeYellow;	//主队黄牌数

    private Short guestYellow;	//客队黄牌数
    
    private String location;	//球场
    
    private String remark;		//比赛说明
    
	public Short getHomeYellow() {
		return homeYellow;
	}

	public void setHomeYellow(Short homeYellow) {
		this.homeYellow = homeYellow;
	}

	public Short getGuestYellow() {
		return guestYellow;
	}

	public void setGuestYellow(Short guestYellow) {
		this.guestYellow = guestYellow;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getHometeamid() {
		return hometeamid;
	}

	public void setHometeamid(Integer hometeamid) {
		this.hometeamid = hometeamid;
	}

	public Integer getGuestteamid() {
		return guestteamid;
	}

	public void setGuestteamid(Integer guestteamid) {
		this.guestteamid = guestteamid;
	}

	public String getHometeam() {
		return hometeam;
	}

	public void setHometeam(String hometeam) {
		this.hometeam = hometeam;
	}

	public String getGuestteam() {
		return guestteam;
	}

	public void setGuestteam(String guestteam) {
		this.guestteam = guestteam;
	}

	public Boolean getNeutrality() {
		return neutrality;
	}

	public void setNeutrality(Boolean neutrality) {
		this.neutrality = neutrality;
	}

	public Date getMatchtime() {
		return matchtime;
	}

	public void setMatchtime(Date matchtime) {
		this.matchtime = matchtime;
	}

	public Date getMatchtime2() {
		return matchtime2;
	}

	public void setMatchtime2(Date matchtime2) {
		this.matchtime2 = matchtime2;
	}

	public String getHomeOrder() {
		return homeOrder;
	}

	public void setHomeOrder(String homeOrder) {
		this.homeOrder = homeOrder;
	}

	public String getGuestOrder() {
		return guestOrder;
	}

	public void setGuestOrder(String guestOrder) {
		this.guestOrder = guestOrder;
	}

	public Short getMatchstate() {
		return matchstate;
	}

	public void setMatchstate(Short matchstate) {
		this.matchstate = matchstate;
	}


	public Short getHomescore() {
		return homescore;
	}

	public void setHomescore(Short homescore) {
		this.homescore = homescore;
	}

	public Short getGuestscore() {
		return guestscore;
	}

	public void setGuestscore(Short guestscore) {
		this.guestscore = guestscore;
	}

	public Short getHomehalfscore() {
		return homehalfscore;
	}

	public void setHomehalfscore(Short homehalfscore) {
		this.homehalfscore = homehalfscore;
	}

	public Short getGuesthalfscore() {
		return guesthalfscore;
	}

	public void setGuesthalfscore(Short guesthalfscore) {
		this.guesthalfscore = guesthalfscore;
	}

	public Short getHomeRed() {
		return homeRed;
	}

	public void setHomeRed(Short homeRed) {
		this.homeRed = homeRed;
	}

	public Short getGuestRed() {
		return guestRed;
	}

	public void setGuestRed(Short guestRed) {
		this.guestRed = guestRed;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
