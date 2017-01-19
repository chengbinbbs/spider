package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ZqPlayerTech implements Serializable {
    private Integer id;

    private Integer playerid;

    private Integer teamid;

    private String season;

    private Integer sclassid;

    private Date modifytime;

    private Integer rounds;

    private Integer backrounds;

    private Integer playtime;

    private Integer goals;

    private Integer shotsnum;

    private Integer shotson;

    private Integer bestsum;

    private Integer interception;

    private Integer assist;

    private Integer pass;

    private Integer passsuc;

    private Integer clearance;

    private Integer clearancesuc;

    private BigDecimal rating;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Integer playerid) {
        this.playerid = playerid;
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season == null ? null : season.trim();
    }

    public Integer getSclassid() {
        return sclassid;
    }

    public void setSclassid(Integer sclassid) {
        this.sclassid = sclassid;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Integer getRounds() {
        return rounds;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    public Integer getBackrounds() {
        return backrounds;
    }

    public void setBackrounds(Integer backrounds) {
        this.backrounds = backrounds;
    }

    public Integer getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Integer playtime) {
        this.playtime = playtime;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getShotsnum() {
        return shotsnum;
    }

    public void setShotsnum(Integer shotsnum) {
        this.shotsnum = shotsnum;
    }

    public Integer getShotson() {
        return shotson;
    }

    public void setShotson(Integer shotson) {
        this.shotson = shotson;
    }

    public Integer getBestsum() {
        return bestsum;
    }

    public void setBestsum(Integer bestsum) {
        this.bestsum = bestsum;
    }

    public Integer getInterception() {
        return interception;
    }

    public void setInterception(Integer interception) {
        this.interception = interception;
    }

    public Integer getAssist() {
        return assist;
    }

    public void setAssist(Integer assist) {
        this.assist = assist;
    }

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    public Integer getPasssuc() {
        return passsuc;
    }

    public void setPasssuc(Integer passsuc) {
        this.passsuc = passsuc;
    }

    public Integer getClearance() {
        return clearance;
    }

    public void setClearance(Integer clearance) {
        this.clearance = clearance;
    }

    public Integer getClearancesuc() {
        return clearancesuc;
    }

    public void setClearancesuc(Integer clearancesuc) {
        this.clearancesuc = clearancesuc;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }
}