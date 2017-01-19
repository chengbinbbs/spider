package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 赛事对阵表
 * @user kouyi
 * @date 2016年12月5日
 */
public class Match implements Serializable, Comparable<Match> {
	private static final long serialVersionUID = 1L;
	private Integer scheduleId;	//赛程id
    private Integer sclassId; //赛事id
    private String league;//赛事名称
    private String matchSeason; //赛季
    private Integer homeTeamId; //主队id
    private Integer guestTeamId; //客队id
    private String homeTeam; //主队名称
    private String guestTeam; //客队名称
    private Boolean neutrality=false; //中立场默认否
    private Date matchTime; //开赛时间
    private Date matchBeginTime; //上下半场开赛时间
    private String homeOrder; //主队排名
    private String guestOrder; //客队排名
    private Short matchState; //比赛状态 0:未开,1:上半场,2:中场,3:下半场,4,加时，-11:待定,-12:腰斩,-13:中断,-14:推迟,-1:完场，-10取消
    private Short homeScore; //主队比分
    private Short guestScore; //客队比分
    private Short homeHalfScore; //主队半场比分
    private Short guestHalfScore; //客队半场比分
    private Short homeRed; //主队红牌数
    private Short guestRed; //客队红牌数
    private Short homeYellow; //主队黄牌数
    private Short guestYellow; //客队黄牌数
    private String remark; //比分说明
    private Boolean ismodel; //是否经过模型处理
    private Boolean hot; //0 不热门 1 热门
    private Date updateTime;
    private Integer flag; //标识位
    private String bei; //备用属性
    
	public String getBei() {
		return bei;
	}

	public void setBei(String bei) {
		this.bei = bei;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getSclassId() {
		return sclassId;
	}

	public void setSclassId(Integer sclassId) {
		this.sclassId = sclassId;
	}

	public String getMatchSeason() {
		return matchSeason;
	}

	public void setMatchSeason(String matchSeason) {
		this.matchSeason = matchSeason;
	}

	public Integer getHomeTeamId() {
		return homeTeamId;
	}

	public void setHomeTeamId(Integer homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public Integer getGuestTeamId() {
		return guestTeamId;
	}

	public void setGuestTeamId(Integer guestTeamId) {
		this.guestTeamId = guestTeamId;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getGuestTeam() {
		return guestTeam;
	}

	public void setGuestTeam(String guestTeam) {
		this.guestTeam = guestTeam;
	}

	public Boolean getNeutrality() {
		return neutrality;
	}

	public void setNeutrality(Boolean neutrality) {
		this.neutrality = neutrality;
	}

	public Date getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}

	public Date getMatchBeginTime() {
		return matchBeginTime;
	}

	public void setMatchBeginTime(Date matchBeginTime) {
		this.matchBeginTime = matchBeginTime;
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

	public Short getMatchState() {
		return matchState;
	}

	public void setMatchState(Short matchState) {
		this.matchState = matchState;
	}

	public Short getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(Short homeScore) {
		this.homeScore = homeScore;
	}

	public Short getGuestScore() {
		return guestScore;
	}

	public void setGuestScore(Short guestScore) {
		this.guestScore = guestScore;
	}

	public Short getHomeHalfScore() {
		return homeHalfScore;
	}

	public void setHomeHalfScore(Short homeHalfScore) {
		this.homeHalfScore = homeHalfScore;
	}

	public Short getGuestHalfScore() {
		return guestHalfScore;
	}

	public void setGuestHalfScore(Short guestHalfScore) {
		this.guestHalfScore = guestHalfScore;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getIsmodel() {
		return ismodel;
	}

	public void setIsmodel(Boolean ismodel) {
		this.ismodel = ismodel;
	}

	public Boolean getHot() {
		return hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public int compareTo(Match o) {
		int m = 0;
		if(o.getMatchState() < 0) {
			m = Integer.valueOf(Math.abs(this.matchState.intValue())).compareTo(Integer.valueOf(Math.abs(o.getMatchState().intValue())));
			if(m == 0) {
				m = this.matchTime.compareTo(o.getMatchTime());
			}
		} else {
			m = this.matchTime.compareTo(o.getMatchTime());
		}
		return m;
	}
	
}