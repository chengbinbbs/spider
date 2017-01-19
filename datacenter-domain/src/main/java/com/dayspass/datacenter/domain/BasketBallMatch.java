package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class BasketBallMatch implements Serializable {
    private Integer ID;

    private String matchCode;

    private Integer matchId;

    private Integer issue;

    private String teamId;

    private Integer weekDay;

    private String hostName;

    private String guestName;

    private String leagueName;

    private Date endTime;

    private Date matchTime;

    private String oneScore;

    private String twoScore;

    private String threeScore;

    private String fourScore;

    private String lastScore;

    private Double rate;

    private Double basePoint;

    private Double sfSp;

    private Double rfsfSp;

    private Double sfcSp;

    private Double dxfSp;

    private String sfResult;

    private String sfggResult;

    private String rfsfResult;

    private String rfsfggResult;

    private String sfcResult;

    private String sfcggResult;

    private String dxfResult;

    private String dxfggResult;

    private Byte sfSingleStatus;

    private Byte sfPassStatus;

    private Byte rfsfSingleStatus;

    private Byte rfsfPassStatus;

    private Byte sfcSingleStatus;

    private Byte sfcPassStatus;

    private Byte dxfSingleStatus;

    private Byte dxfPassStatus;

    private Byte status;

    private String personLock;

    private Integer adminId;

    private Date createTime;

    private Date updateTime;

    private Integer jcwId;

    private Integer hostTeamId;

    private Integer guestTeamId;

    private static final long serialVersionUID = 1L;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode == null ? null : matchCode.trim();
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
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

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getThreeScore() {
        return threeScore;
    }

    public void setThreeScore(String threeScore) {
        this.threeScore = threeScore == null ? null : threeScore.trim();
    }

    public String getFourScore() {
        return fourScore;
    }

    public void setFourScore(String fourScore) {
        this.fourScore = fourScore == null ? null : fourScore.trim();
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

    public Double getBasePoint() {
        return basePoint;
    }

    public void setBasePoint(Double basePoint) {
        this.basePoint = basePoint;
    }

    public Double getSfSp() {
        return sfSp;
    }

    public void setSfSp(Double sfSp) {
        this.sfSp = sfSp;
    }

    public Double getRfsfSp() {
        return rfsfSp;
    }

    public void setRfsfSp(Double rfsfSp) {
        this.rfsfSp = rfsfSp;
    }

    public Double getSfcSp() {
        return sfcSp;
    }

    public void setSfcSp(Double sfcSp) {
        this.sfcSp = sfcSp;
    }

    public Double getDxfSp() {
        return dxfSp;
    }

    public void setDxfSp(Double dxfSp) {
        this.dxfSp = dxfSp;
    }

    public String getSfResult() {
        return sfResult;
    }

    public void setSfResult(String sfResult) {
        this.sfResult = sfResult == null ? null : sfResult.trim();
    }

    public String getSfggResult() {
        return sfggResult;
    }

    public void setSfggResult(String sfggResult) {
        this.sfggResult = sfggResult == null ? null : sfggResult.trim();
    }

    public String getRfsfResult() {
        return rfsfResult;
    }

    public void setRfsfResult(String rfsfResult) {
        this.rfsfResult = rfsfResult == null ? null : rfsfResult.trim();
    }

    public String getRfsfggResult() {
        return rfsfggResult;
    }

    public void setRfsfggResult(String rfsfggResult) {
        this.rfsfggResult = rfsfggResult == null ? null : rfsfggResult.trim();
    }

    public String getSfcResult() {
        return sfcResult;
    }

    public void setSfcResult(String sfcResult) {
        this.sfcResult = sfcResult == null ? null : sfcResult.trim();
    }

    public String getSfcggResult() {
        return sfcggResult;
    }

    public void setSfcggResult(String sfcggResult) {
        this.sfcggResult = sfcggResult == null ? null : sfcggResult.trim();
    }

    public String getDxfResult() {
        return dxfResult;
    }

    public void setDxfResult(String dxfResult) {
        this.dxfResult = dxfResult == null ? null : dxfResult.trim();
    }

    public String getDxfggResult() {
        return dxfggResult;
    }

    public void setDxfggResult(String dxfggResult) {
        this.dxfggResult = dxfggResult == null ? null : dxfggResult.trim();
    }

    public Byte getSfSingleStatus() {
        return sfSingleStatus;
    }

    public void setSfSingleStatus(Byte sfSingleStatus) {
        this.sfSingleStatus = sfSingleStatus;
    }

    public Byte getSfPassStatus() {
        return sfPassStatus;
    }

    public void setSfPassStatus(Byte sfPassStatus) {
        this.sfPassStatus = sfPassStatus;
    }

    public Byte getRfsfSingleStatus() {
        return rfsfSingleStatus;
    }

    public void setRfsfSingleStatus(Byte rfsfSingleStatus) {
        this.rfsfSingleStatus = rfsfSingleStatus;
    }

    public Byte getRfsfPassStatus() {
        return rfsfPassStatus;
    }

    public void setRfsfPassStatus(Byte rfsfPassStatus) {
        this.rfsfPassStatus = rfsfPassStatus;
    }

    public Byte getSfcSingleStatus() {
        return sfcSingleStatus;
    }

    public void setSfcSingleStatus(Byte sfcSingleStatus) {
        this.sfcSingleStatus = sfcSingleStatus;
    }

    public Byte getSfcPassStatus() {
        return sfcPassStatus;
    }

    public void setSfcPassStatus(Byte sfcPassStatus) {
        this.sfcPassStatus = sfcPassStatus;
    }

    public Byte getDxfSingleStatus() {
        return dxfSingleStatus;
    }

    public void setDxfSingleStatus(Byte dxfSingleStatus) {
        this.dxfSingleStatus = dxfSingleStatus;
    }

    public Byte getDxfPassStatus() {
        return dxfPassStatus;
    }

    public void setDxfPassStatus(Byte dxfPassStatus) {
        this.dxfPassStatus = dxfPassStatus;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getPersonLock() {
        return personLock;
    }

    public void setPersonLock(String personLock) {
        this.personLock = personLock == null ? null : personLock.trim();
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

    public Integer getJcwId() {
        return jcwId;
    }

    public void setJcwId(Integer jcwId) {
        this.jcwId = jcwId;
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