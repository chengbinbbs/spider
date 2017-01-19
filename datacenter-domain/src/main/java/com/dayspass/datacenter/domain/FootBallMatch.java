package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class FootBallMatch implements Serializable {
    private Integer ID;

    private Integer matchId;

    private String matchCode;

    private Integer issue;

    private String teamId;

    private String hostName;

    private String guestName;

    private String leagueName;

    private Date matchTime;

    private String oneScore;

    private String twoScore;

    private String lastScore;

    private Double rate;

    private Double spfSp;

    private Double rqSpfSp;

    private Double zjqSp;

    private Double bfSp;

    private Double bqcSp;

    private String spfResult;

    private String rqSpfResult;

    private String zjqResult;

    private String bfResult;

    private String bqcResult;

    private Integer spfSingleStatus;

    private Integer spfPassStatus;

    private Integer rqSpfSingleStatus;

    private Integer rqSpfPassStatus;

    private Integer zjqSingleStatus;

    private Integer zjqPassStatus;

    private Integer bfSingleStatus;

    private Integer bfPassStatus;

    private Integer bqcSingleStatus;

    private Integer bqcPassStatus;

    private Integer status;

    private Integer personLock;

    private Integer adminId;

    private Date createTime;

    private Date updateTime;

    private Integer weekday;

    private Integer jcwId;

    private Integer outId;

    private Integer hostTeamId;

    private Integer guestTeamId;

    private static final long serialVersionUID = 1L;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public String getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode == null ? null : matchCode.trim();
    }

    public Integer getIssue() {
        return issue;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId == null ? null : teamId.trim();
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName == null ? null : hostName.trim();
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName == null ? null : guestName.trim();
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName == null ? null : leagueName.trim();
    }

    public Date getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }

    public String getOneScore() {
        return oneScore;
    }

    public void setOneScore(String oneScore) {
        this.oneScore = oneScore == null ? null : oneScore.trim();
    }

    public String getTwoScore() {
        return twoScore;
    }

    public void setTwoScore(String twoScore) {
        this.twoScore = twoScore == null ? null : twoScore.trim();
    }

    public String getLastScore() {
        return lastScore;
    }

    public void setLastScore(String lastScore) {
        this.lastScore = lastScore == null ? null : lastScore.trim();
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getSpfSp() {
        return spfSp;
    }

    public void setSpfSp(Double spfSp) {
        this.spfSp = spfSp;
    }

    public Double getRqSpfSp() {
        return rqSpfSp;
    }

    public void setRqSpfSp(Double rqSpfSp) {
        this.rqSpfSp = rqSpfSp;
    }

    public Double getZjqSp() {
        return zjqSp;
    }

    public void setZjqSp(Double zjqSp) {
        this.zjqSp = zjqSp;
    }

    public Double getBfSp() {
        return bfSp;
    }

    public void setBfSp(Double bfSp) {
        this.bfSp = bfSp;
    }

    public Double getBqcSp() {
        return bqcSp;
    }

    public void setBqcSp(Double bqcSp) {
        this.bqcSp = bqcSp;
    }

    public String getSpfResult() {
        return spfResult;
    }

    public void setSpfResult(String spfResult) {
        this.spfResult = spfResult == null ? null : spfResult.trim();
    }

    public String getRqSpfResult() {
        return rqSpfResult;
    }

    public void setRqSpfResult(String rqSpfResult) {
        this.rqSpfResult = rqSpfResult == null ? null : rqSpfResult.trim();
    }

    public String getZjqResult() {
        return zjqResult;
    }

    public void setZjqResult(String zjqResult) {
        this.zjqResult = zjqResult == null ? null : zjqResult.trim();
    }

    public String getBfResult() {
        return bfResult;
    }

    public void setBfResult(String bfResult) {
        this.bfResult = bfResult == null ? null : bfResult.trim();
    }

    public String getBqcResult() {
        return bqcResult;
    }

    public void setBqcResult(String bqcResult) {
        this.bqcResult = bqcResult == null ? null : bqcResult.trim();
    }

    public Integer getSpfSingleStatus() {
        return spfSingleStatus;
    }

    public void setSpfSingleStatus(Integer spfSingleStatus) {
        this.spfSingleStatus = spfSingleStatus;
    }

    public Integer getSpfPassStatus() {
        return spfPassStatus;
    }

    public void setSpfPassStatus(Integer spfPassStatus) {
        this.spfPassStatus = spfPassStatus;
    }

    public Integer getRqSpfSingleStatus() {
        return rqSpfSingleStatus;
    }

    public void setRqSpfSingleStatus(Integer rqSpfSingleStatus) {
        this.rqSpfSingleStatus = rqSpfSingleStatus;
    }

    public Integer getRqSpfPassStatus() {
        return rqSpfPassStatus;
    }

    public void setRqSpfPassStatus(Integer rqSpfPassStatus) {
        this.rqSpfPassStatus = rqSpfPassStatus;
    }

    public Integer getZjqSingleStatus() {
        return zjqSingleStatus;
    }

    public void setZjqSingleStatus(Integer zjqSingleStatus) {
        this.zjqSingleStatus = zjqSingleStatus;
    }

    public Integer getZjqPassStatus() {
        return zjqPassStatus;
    }

    public void setZjqPassStatus(Integer zjqPassStatus) {
        this.zjqPassStatus = zjqPassStatus;
    }

    public Integer getBfSingleStatus() {
        return bfSingleStatus;
    }

    public void setBfSingleStatus(Integer bfSingleStatus) {
        this.bfSingleStatus = bfSingleStatus;
    }

    public Integer getBfPassStatus() {
        return bfPassStatus;
    }

    public void setBfPassStatus(Integer bfPassStatus) {
        this.bfPassStatus = bfPassStatus;
    }

    public Integer getBqcSingleStatus() {
        return bqcSingleStatus;
    }

    public void setBqcSingleStatus(Integer bqcSingleStatus) {
        this.bqcSingleStatus = bqcSingleStatus;
    }

    public Integer getBqcPassStatus() {
        return bqcPassStatus;
    }

    public void setBqcPassStatus(Integer bqcPassStatus) {
        this.bqcPassStatus = bqcPassStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPersonLock() {
        return personLock;
    }

    public void setPersonLock(Integer personLock) {
        this.personLock = personLock;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    public Integer getJcwId() {
        return jcwId;
    }

    public void setJcwId(Integer jcwId) {
        this.jcwId = jcwId;
    }

    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    public Integer getHostTeamId() {
        return hostTeamId;
    }

    public void setHostTeamId(Integer hostTeamId) {
        this.hostTeamId = hostTeamId;
    }

    public Integer getGuestTeamId() {
        return guestTeamId;
    }

    public void setGuestTeamId(Integer guestTeamId) {
        this.guestTeamId = guestTeamId;
    }
}