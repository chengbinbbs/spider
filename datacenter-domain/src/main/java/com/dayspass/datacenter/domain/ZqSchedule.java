package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ZqSchedule implements Serializable {
    private Integer scheduleid;

    private Integer sclassid;

    private String matchseason;

    private Short round;

    private String grouping;

    private Integer hometeamid;

    private Integer guestteamid;

    private String hometeam;

    private String guestteam;

    private Boolean neutrality;

    private Date matchtime;

    private Date matchtime2;

    private String location;

    private String homeOrder;

    private String guestOrder;

    private Short matchstate;

    private Short weathericon;

    private String weather;

    private String temperature;

    private String tv;

    private String umpire;

    private Integer visitor;

    private Short homescore;

    private Short guestscore;

    private Short homehalfscore;

    private Short guesthalfscore;

    private String explain;

    private Short homeRed;

    private Short guestRed;
    
    private Short homeYellow;

    private Short guestYellow;

    private Date bfChangetime;

    private Byte shangpan;

    private String grouping2;

    private Integer subsclassid;

    private String explainlist;

    private BigDecimal firstgoal;

    private Integer result;

    private Integer dxresult;

    private Integer sid;

    private static final long serialVersionUID = 1L;

    public Integer getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Integer scheduleid) {
        this.scheduleid = scheduleid;
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

    public Short getRound() {
        return round;
    }

    public void setRound(Short round) {
        this.round = round;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping == null ? null : grouping.trim();
    }

    public Integer getHometeamid() {
        return hometeamid;
    }

    public void setHometeamid(Integer hometeamid) {
        this.hometeamid = hometeamid;
    }

    public Integer getGuestteamid() {
        return guestteamid;
    }

    public void setGuestteamid(Integer guestteamid) {
        this.guestteamid = guestteamid;
    }

    public String getHometeam() {
        return hometeam;
    }

    public void setHometeam(String hometeam) {
        this.hometeam = hometeam == null ? null : hometeam.trim();
    }

    public String getGuestteam() {
        return guestteam;
    }

    public void setGuestteam(String guestteam) {
        this.guestteam = guestteam == null ? null : guestteam.trim();
    }

    public Boolean getNeutrality() {
        return neutrality;
    }

    public void setNeutrality(Boolean neutrality) {
        this.neutrality = neutrality;
    }

    public Date getMatchtime() {
        return matchtime;
    }

    public void setMatchtime(Date matchtime) {
        this.matchtime = matchtime;
    }

    public Date getMatchtime2() {
        return matchtime2;
    }

    public void setMatchtime2(Date matchtime2) {
        this.matchtime2 = matchtime2;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getHomeOrder() {
        return homeOrder;
    }

    public void setHomeOrder(String homeOrder) {
        this.homeOrder = homeOrder == null ? null : homeOrder.trim();
    }

    public String getGuestOrder() {
        return guestOrder;
    }

    public void setGuestOrder(String guestOrder) {
        this.guestOrder = guestOrder == null ? null : guestOrder.trim();
    }

    public Short getMatchstate() {
        return matchstate;
    }

    public void setMatchstate(Short matchstate) {
        this.matchstate = matchstate;
    }

    public Short getWeathericon() {
        return weathericon;
    }

    public void setWeathericon(Short weathericon) {
        this.weathericon = weathericon;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather == null ? null : weather.trim();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv == null ? null : tv.trim();
    }

    public String getUmpire() {
        return umpire;
    }

    public void setUmpire(String umpire) {
        this.umpire = umpire == null ? null : umpire.trim();
    }

    public Integer getVisitor() {
        return visitor;
    }

    public void setVisitor(Integer visitor) {
        this.visitor = visitor;
    }

    public Short getHomescore() {
        return homescore;
    }

    public void setHomescore(Short homescore) {
        this.homescore = homescore;
    }

    public Short getGuestscore() {
        return guestscore;
    }

    public void setGuestscore(Short guestscore) {
        this.guestscore = guestscore;
    }

    public Short getHomehalfscore() {
        return homehalfscore;
    }

    public void setHomehalfscore(Short homehalfscore) {
        this.homehalfscore = homehalfscore;
    }

    public Short getGuesthalfscore() {
        return guesthalfscore;
    }

    public void setGuesthalfscore(Short guesthalfscore) {
        this.guesthalfscore = guesthalfscore;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }

    public Short getHomeRed() {
        return homeRed;
    }

    public void setHomeRed(Short homeRed) {
        this.homeRed = homeRed;
    }

    public Short getGuestRed() {
        return guestRed;
    }

    public void setGuestRed(Short guestRed) {
        this.guestRed = guestRed;
    }

    public Short getHomeYellow() {
        return homeYellow;
    }

    public void setHomeYellow(Short homeYellow) {
        this.homeYellow = homeYellow;
    }

    public Short getGuestYellow() {
        return guestYellow;
    }

    public void setGuestYellow(Short guestYellow) {
        this.guestYellow = guestYellow;
    }

    public Date getBfChangetime() {
        return bfChangetime;
    }

    public void setBfChangetime(Date bfChangetime) {
        this.bfChangetime = bfChangetime;
    }

    public Byte getShangpan() {
        return shangpan;
    }

    public void setShangpan(Byte shangpan) {
        this.shangpan = shangpan;
    }

    public String getGrouping2() {
        return grouping2;
    }

    public void setGrouping2(String grouping2) {
        this.grouping2 = grouping2 == null ? null : grouping2.trim();
    }

    public Integer getSubsclassid() {
        return subsclassid;
    }

    public void setSubsclassid(Integer subsclassid) {
        this.subsclassid = subsclassid;
    }

    public String getExplainlist() {
        return explainlist;
    }

    public void setExplainlist(String explainlist) {
        this.explainlist = explainlist == null ? null : explainlist.trim();
    }

    public BigDecimal getFirstgoal() {
        return firstgoal;
    }

    public void setFirstgoal(BigDecimal firstgoal) {
        this.firstgoal = firstgoal;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getDxresult() {
        return dxresult;
    }

    public void setDxresult(Integer dxresult) {
        this.dxresult = dxresult;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}