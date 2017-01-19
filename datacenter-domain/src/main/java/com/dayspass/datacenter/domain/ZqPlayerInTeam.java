package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class ZqPlayerInTeam implements Serializable {
    private Integer id;

    private Integer playerid;

    private String playername;

    private Integer teamid;

    private String teamname;

    private String place;

    private String number;

    private Short score;

    private Date modifytime;

    private Integer coachtypeid;

    private Integer captaintypeid;

    private Integer playerpositionid;

    private Date starttime;

    private Date endtime;

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

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername == null ? null : playername.trim();
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname == null ? null : teamname.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Short getScore() {
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Integer getCoachtypeid() {
        return coachtypeid;
    }

    public void setCoachtypeid(Integer coachtypeid) {
        this.coachtypeid = coachtypeid;
    }

    public Integer getCaptaintypeid() {
        return captaintypeid;
    }

    public void setCaptaintypeid(Integer captaintypeid) {
        this.captaintypeid = captaintypeid;
    }

    public Integer getPlayerpositionid() {
        return playerpositionid;
    }

    public void setPlayerpositionid(Integer playerpositionid) {
        this.playerpositionid = playerpositionid;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}