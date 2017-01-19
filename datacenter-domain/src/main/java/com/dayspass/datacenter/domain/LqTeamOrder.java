package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class LqTeamOrder implements Serializable {
    private Integer id;

    private Integer teamid;

    private Integer sclassid;

    private String matchseason;

    private Short homewin;

    private Short homeloss;

    private Short guestwin;

    private Short guestloss;

    private Float winscale;

    private Short state;

    private Short homeorder;

    private Short guestorder;

    private Short totalorder;

    private Integer homescore;

    private Integer homelossscore;

    private Integer guestscore;

    private Integer guestlossscore;

    private Short near10win;

    private Short near10loss;

    private Short near10score;

    private Short near10lossscore;

    private String roundtype1;

    private String roundtype2;

    private Integer leaguewin;

    private Integer leagueloss;

    private Integer divisionwin;

    private Integer divisionloss;

    private Float wincha;

    private Integer divisionorder;

    private static final long serialVersionUID = 1L;

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

    public String getMatchseason() {
        return matchseason;
    }

    public void setMatchseason(String matchseason) {
        this.matchseason = matchseason == null ? null : matchseason.trim();
    }

    public Short getHomewin() {
        return homewin;
    }

    public void setHomewin(Short homewin) {
        this.homewin = homewin;
    }

    public Short getHomeloss() {
        return homeloss;
    }

    public void setHomeloss(Short homeloss) {
        this.homeloss = homeloss;
    }

    public Short getGuestwin() {
        return guestwin;
    }

    public void setGuestwin(Short guestwin) {
        this.guestwin = guestwin;
    }

    public Short getGuestloss() {
        return guestloss;
    }

    public void setGuestloss(Short guestloss) {
        this.guestloss = guestloss;
    }

    public Float getWinscale() {
        return winscale;
    }

    public void setWinscale(Float winscale) {
        this.winscale = winscale;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Short getHomeorder() {
        return homeorder;
    }

    public void setHomeorder(Short homeorder) {
        this.homeorder = homeorder;
    }

    public Short getGuestorder() {
        return guestorder;
    }

    public void setGuestorder(Short guestorder) {
        this.guestorder = guestorder;
    }

    public Short getTotalorder() {
        return totalorder;
    }

    public void setTotalorder(Short totalorder) {
        this.totalorder = totalorder;
    }

    public Integer getHomescore() {
        return homescore;
    }

    public void setHomescore(Integer homescore) {
        this.homescore = homescore;
    }

    public Integer getHomelossscore() {
        return homelossscore;
    }

    public void setHomelossscore(Integer homelossscore) {
        this.homelossscore = homelossscore;
    }

    public Integer getGuestscore() {
        return guestscore;
    }

    public void setGuestscore(Integer guestscore) {
        this.guestscore = guestscore;
    }

    public Integer getGuestlossscore() {
        return guestlossscore;
    }

    public void setGuestlossscore(Integer guestlossscore) {
        this.guestlossscore = guestlossscore;
    }

    public Short getNear10win() {
        return near10win;
    }

    public void setNear10win(Short near10win) {
        this.near10win = near10win;
    }

    public Short getNear10loss() {
        return near10loss;
    }

    public void setNear10loss(Short near10loss) {
        this.near10loss = near10loss;
    }

    public Short getNear10score() {
        return near10score;
    }

    public void setNear10score(Short near10score) {
        this.near10score = near10score;
    }

    public Short getNear10lossscore() {
        return near10lossscore;
    }

    public void setNear10lossscore(Short near10lossscore) {
        this.near10lossscore = near10lossscore;
    }

    public String getRoundtype1() {
        return roundtype1;
    }

    public void setRoundtype1(String roundtype1) {
        this.roundtype1 = roundtype1 == null ? null : roundtype1.trim();
    }

    public String getRoundtype2() {
        return roundtype2;
    }

    public void setRoundtype2(String roundtype2) {
        this.roundtype2 = roundtype2 == null ? null : roundtype2.trim();
    }

    public Integer getLeaguewin() {
        return leaguewin;
    }

    public void setLeaguewin(Integer leaguewin) {
        this.leaguewin = leaguewin;
    }

    public Integer getLeagueloss() {
        return leagueloss;
    }

    public void setLeagueloss(Integer leagueloss) {
        this.leagueloss = leagueloss;
    }

    public Integer getDivisionwin() {
        return divisionwin;
    }

    public void setDivisionwin(Integer divisionwin) {
        this.divisionwin = divisionwin;
    }

    public Integer getDivisionloss() {
        return divisionloss;
    }

    public void setDivisionloss(Integer divisionloss) {
        this.divisionloss = divisionloss;
    }

    public Float getWincha() {
        return wincha;
    }

    public void setWincha(Float wincha) {
        this.wincha = wincha;
    }

    public Integer getDivisionorder() {
        return divisionorder;
    }

    public void setDivisionorder(Integer divisionorder) {
        this.divisionorder = divisionorder;
    }
}