package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class LqTeamTechnic implements Serializable {
    private Integer id;

    private Integer scheduleid;

    private Integer teamid;

    private String matchseason;

    private Boolean ishome;

    private Short playtime;

    private Short score;

    private Short lostscore;

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

    private Short twoattack;

    private Short totalmis;

    private Byte result;

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

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public String getMatchseason() {
        return matchseason;
    }

    public void setMatchseason(String matchseason) {
        this.matchseason = matchseason == null ? null : matchseason.trim();
    }

    public Boolean getIshome() {
        return ishome;
    }

    public void setIshome(Boolean ishome) {
        this.ishome = ishome;
    }

    public Short getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Short playtime) {
        this.playtime = playtime;
    }

    public Short getScore() {
        return score;
    }

    public void setScore(Short score) {
        this.score = score;
    }

    public Short getLostscore() {
        return lostscore;
    }

    public void setLostscore(Short lostscore) {
        this.lostscore = lostscore;
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

    public Short getTwoattack() {
        return twoattack;
    }

    public void setTwoattack(Short twoattack) {
        this.twoattack = twoattack;
    }

    public Short getTotalmis() {
        return totalmis;
    }

    public void setTotalmis(Short totalmis) {
        this.totalmis = totalmis;
    }

    public Byte getResult() {
        return result;
    }

    public void setResult(Byte result) {
        this.result = result;
    }
}