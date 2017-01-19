package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class ZqDetailResult implements Serializable {
    private Integer id;

    private Integer scheduleid;

    private Short happentime;

    private Integer teamid;

    private String playername;

    private Integer playerid;

    private Short kind;

    private Date modifytime;

    private String playernameE;

    private String playernameJ;

    private Integer playeridIn;

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

    public Short getHappentime() {
        return happentime;
    }

    public void setHappentime(Short happentime) {
        this.happentime = happentime;
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername == null ? null : playername.trim();
    }

    public Integer getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Integer playerid) {
        this.playerid = playerid;
    }

    public Short getKind() {
        return kind;
    }

    public void setKind(Short kind) {
        this.kind = kind;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getPlayernameE() {
        return playernameE;
    }

    public void setPlayernameE(String playernameE) {
        this.playernameE = playernameE == null ? null : playernameE.trim();
    }

    public String getPlayernameJ() {
        return playernameJ;
    }

    public void setPlayernameJ(String playernameJ) {
        this.playernameJ = playernameJ == null ? null : playernameJ.trim();
    }

    public Integer getPlayeridIn() {
        return playeridIn;
    }

    public void setPlayeridIn(Integer playeridIn) {
        this.playeridIn = playeridIn;
    }
}