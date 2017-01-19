package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class LqPlayerTechnic implements Serializable {
    private Integer id;

    private Integer playerid;

    private String matchseason;

    private Integer teamid;

    private Short jointime;

    private Short firstjoin;

    private Short playtime;

    private Short shoot;

    private Short shootHit;

    private Short threemin;

    private Short threeminHit;

    private Short punishball;

    private Short punishballHit;

    private Short attack;

    private Short defend;

    private Short helpattack;

    private Short rob;

    private Short cover;

    private Short misplay;

    private Short foul;

    private Short score;

    private Short double2;

    private Short double3;

    private Short double4;

    private Short techkind;

    private Short jointimeWin;

    private Short firstjoinWin;

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

    public String getMatchseason() {
        return matchseason;
    }

    public void setMatchseason(String matchseason) {
        this.matchseason = matchseason == null ? null : matchseason.trim();
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public Short getJointime() {
        return jointime;
    }

    public void setJointime(Short jointime) {
        this.jointime = jointime;
    }

    public Short getFirstjoin() {
        return firstjoin;
    }

    public void setFirstjoin(Short firstjoin) {
        this.firstjoin = firstjoin;
    }

    public Short getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Short playtime) {
        this.playtime = playtime;
    }

    public Short getShoot() {
        return shoot;
    }

    public void setShoot(Short shoot) {
        this.shoot = shoot;
    }

    public Short getShootHit() {
        return shootHit;
    }

    public void setShootHit(Short shootHit) {
        this.shootHit = shootHit;
    }

    public Short getThreemin() {
        return threemin;
    }

    public void setThreemin(Short threemin) {
        this.threemin = threemin;
    }

    public Short getThreeminHit() {
        return threeminHit;
    }

    public void setThreeminHit(Short threeminHit) {
        this.threeminHit = threeminHit;
    }

    public Short getPunishball() {
        return punishball;
    }

    public void setPunishball(Short punishball) {
        this.punishball = punishball;
    }

    public Short getPunishballHit() {
        return punishballHit;
    }

    public void setPunishballHit(Short punishballHit) {
        this.punishballHit = punishballHit;
    }

    public Short getAttack() {
        return attack;
    }

    public void setAttack(Short attack) {
        this.attack = attack;
    }

    public Short getDefend() {
        return defend;
    }

    public void setDefend(Short defend) {
        this.defend = defend;
    }

    public Short getHelpattack() {
        return helpattack;
    }

    public void setHelpattack(Short helpattack) {
        this.helpattack = helpattack;
    }

    public Short getRob() {
        return rob;
    }

    public void setRob(Short rob) {
        this.rob = rob;
    }

    public Short getCover() {
        return cover;
    }

    public void setCover(Short cover) {
        this.cover = cover;
    }

    public Short getMisplay() {
        return misplay;
    }

    public void setMisplay(Short misplay) {
        this.misplay = misplay;
    }

    public Short getFoul() {
        return foul;
    }

    public void setFoul(Short foul) {
        this.foul = foul;
    }

    public Short getScore() {
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }

    public Short getDouble2() {
        return double2;
    }

    public void setDouble2(Short double2) {
        this.double2 = double2;
    }

    public Short getDouble3() {
        return double3;
    }

    public void setDouble3(Short double3) {
        this.double3 = double3;
    }

    public Short getDouble4() {
        return double4;
    }

    public void setDouble4(Short double4) {
        this.double4 = double4;
    }

    public Short getTechkind() {
        return techkind;
    }

    public void setTechkind(Short techkind) {
        this.techkind = techkind;
    }

    public Short getJointimeWin() {
        return jointimeWin;
    }

    public void setJointimeWin(Short jointimeWin) {
        this.jointimeWin = jointimeWin;
    }

    public Short getFirstjoinWin() {
        return firstjoinWin;
    }

    public void setFirstjoinWin(Short firstjoinWin) {
        this.firstjoinWin = firstjoinWin;
    }
}