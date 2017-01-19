package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class ZqPlayerDetail implements Serializable {
    private Integer id;

    private Integer scheduleid;

    private Integer playerId;

    private Integer teamId;

    private Integer number;

    private String playerName;

    private String position;

    private Integer shotsNum;

    private Integer shotsOn;

    private Integer keyPass;

    private Float passRate;

    private Integer aerialWin;

    private Integer touches;

    private Integer dribblesWin;

    private Integer wasFouled;

    private Integer dispossessed;

    private Integer turnOver;

    private Integer offSides;

    private Integer tackles;

    private Integer interception;

    private Integer clearances;

    private Integer clearanceWin;

    private Integer shotsBlocked;

    private Integer offsideProvoked;

    private Integer fouls;

    private Integer totalPass;

    private Integer accuratePass;

    private Integer crossNum;

    private Integer crossWin;

    private Integer longBall;

    private Integer longballWin;

    private Integer throughBall;

    private Integer throughballWin;

    private Float rating;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Integer scheduleid) {
        this.scheduleid = scheduleid;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName == null ? null : playerName.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Integer getShotsNum() {
        return shotsNum;
    }

    public void setShotsNum(Integer shotsNum) {
        this.shotsNum = shotsNum;
    }

    public Integer getShotsOn() {
        return shotsOn;
    }

    public void setShotsOn(Integer shotsOn) {
        this.shotsOn = shotsOn;
    }

    public Integer getKeyPass() {
        return keyPass;
    }

    public void setKeyPass(Integer keyPass) {
        this.keyPass = keyPass;
    }

    public Float getPassRate() {
        return passRate;
    }

    public void setPassRate(Float passRate) {
        this.passRate = passRate;
    }

    public Integer getAerialWin() {
        return aerialWin;
    }

    public void setAerialWin(Integer aerialWin) {
        this.aerialWin = aerialWin;
    }

    public Integer getTouches() {
        return touches;
    }

    public void setTouches(Integer touches) {
        this.touches = touches;
    }

    public Integer getDribblesWin() {
        return dribblesWin;
    }

    public void setDribblesWin(Integer dribblesWin) {
        this.dribblesWin = dribblesWin;
    }

    public Integer getWasFouled() {
        return wasFouled;
    }

    public void setWasFouled(Integer wasFouled) {
        this.wasFouled = wasFouled;
    }

    public Integer getDispossessed() {
        return dispossessed;
    }

    public void setDispossessed(Integer dispossessed) {
        this.dispossessed = dispossessed;
    }

    public Integer getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(Integer turnOver) {
        this.turnOver = turnOver;
    }

    public Integer getOffSides() {
        return offSides;
    }

    public void setOffSides(Integer offSides) {
        this.offSides = offSides;
    }

    public Integer getTackles() {
        return tackles;
    }

    public void setTackles(Integer tackles) {
        this.tackles = tackles;
    }

    public Integer getInterception() {
        return interception;
    }

    public void setInterception(Integer interception) {
        this.interception = interception;
    }

    public Integer getClearances() {
        return clearances;
    }

    public void setClearances(Integer clearances) {
        this.clearances = clearances;
    }

    public Integer getClearanceWin() {
        return clearanceWin;
    }

    public void setClearanceWin(Integer clearanceWin) {
        this.clearanceWin = clearanceWin;
    }

    public Integer getShotsBlocked() {
        return shotsBlocked;
    }

    public void setShotsBlocked(Integer shotsBlocked) {
        this.shotsBlocked = shotsBlocked;
    }

    public Integer getOffsideProvoked() {
        return offsideProvoked;
    }

    public void setOffsideProvoked(Integer offsideProvoked) {
        this.offsideProvoked = offsideProvoked;
    }

    public Integer getFouls() {
        return fouls;
    }

    public void setFouls(Integer fouls) {
        this.fouls = fouls;
    }

    public Integer getTotalPass() {
        return totalPass;
    }

    public void setTotalPass(Integer totalPass) {
        this.totalPass = totalPass;
    }

    public Integer getAccuratePass() {
        return accuratePass;
    }

    public void setAccuratePass(Integer accuratePass) {
        this.accuratePass = accuratePass;
    }

    public Integer getCrossNum() {
        return crossNum;
    }

    public void setCrossNum(Integer crossNum) {
        this.crossNum = crossNum;
    }

    public Integer getCrossWin() {
        return crossWin;
    }

    public void setCrossWin(Integer crossWin) {
        this.crossWin = crossWin;
    }

    public Integer getLongBall() {
        return longBall;
    }

    public void setLongBall(Integer longBall) {
        this.longBall = longBall;
    }

    public Integer getLongballWin() {
        return longballWin;
    }

    public void setLongballWin(Integer longballWin) {
        this.longballWin = longballWin;
    }

    public Integer getThroughBall() {
        return throughBall;
    }

    public void setThroughBall(Integer throughBall) {
        this.throughBall = throughBall;
    }

    public Integer getThroughballWin() {
        return throughballWin;
    }

    public void setThroughballWin(Integer throughballWin) {
        this.throughballWin = throughballWin;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
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
}