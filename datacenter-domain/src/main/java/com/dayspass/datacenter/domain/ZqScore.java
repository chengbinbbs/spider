package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class ZqScore implements Serializable {
    private Integer id;

    private Integer teamid;
    
    private Integer rank;

    private Integer sclassid;

    private Integer winScore;

    private Integer flatScore;

    private Integer failScore;

    private Integer totalHomescore;

    private Integer totalGuestscore;
    
    private Integer totalScore;

    private Byte homeorguest;

    private String matchseason;

    private Integer deduct;

    private String cause;

    private Integer goal;

    private String causeen;

    private Integer subsclassid;

    private Integer redcard;

    private static final long serialVersionUID = 1L;
    

    public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public Integer getSclassid() {
        return sclassid;
    }

    public void setSclassid(Integer sclassid) {
        this.sclassid = sclassid;
    }

    public Integer getWinScore() {
        return winScore;
    }

    public void setWinScore(Integer winScore) {
        this.winScore = winScore;
    }

    public Integer getFlatScore() {
        return flatScore;
    }

    public void setFlatScore(Integer flatScore) {
        this.flatScore = flatScore;
    }

    public Integer getFailScore() {
        return failScore;
    }

    public void setFailScore(Integer failScore) {
        this.failScore = failScore;
    }

    public Integer getTotalHomescore() {
        return totalHomescore;
    }

    public void setTotalHomescore(Integer totalHomescore) {
        this.totalHomescore = totalHomescore;
    }

    public Integer getTotalGuestscore() {
        return totalGuestscore;
    }

    public void setTotalGuestscore(Integer totalGuestscore) {
        this.totalGuestscore = totalGuestscore;
    }

    public Byte getHomeorguest() {
        return homeorguest;
    }

    public void setHomeorguest(Byte homeorguest) {
        this.homeorguest = homeorguest;
    }

    public String getMatchseason() {
        return matchseason;
    }

    public void setMatchseason(String matchseason) {
        this.matchseason = matchseason == null ? null : matchseason.trim();
    }

    public Integer getDeduct() {
        return deduct;
    }

    public void setDeduct(Integer deduct) {
        this.deduct = deduct;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public String getCauseen() {
        return causeen;
    }

    public void setCauseen(String causeen) {
        this.causeen = causeen == null ? null : causeen.trim();
    }

    public Integer getSubsclassid() {
        return subsclassid;
    }

    public void setSubsclassid(Integer subsclassid) {
        this.subsclassid = subsclassid;
    }

    public Integer getRedcard() {
        return redcard;
    }

    public void setRedcard(Integer redcard) {
        this.redcard = redcard;
    }
}