package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class LqSchedule implements Serializable {
    private Integer id;

    private Short sclassid;

    private String matchseason;

    private Short matchkind;

    private Integer hometeamid;

    private Integer guestteamid;

    private String hometeam;

    private String guestteam;

    private Date matchtime;

    private String location;

    private Short homeone;

    private Short hometwo;

    private Short homethree;

    private Short homefour;

    private Short guestone;

    private Short guesttwo;

    private Short guestthree;

    private Short guestfour;

    private Short addtime;

    private Short homeaddtime1;

    private Short homeaddtime2;

    private Short homeaddtime3;

    private Short homeaddtime4;

    private Short homeaddtime5;

    private Short guestaddtime1;

    private Short guestaddtime2;

    private Short guestaddtime3;

    private Short guestaddtime4;

    private Short guestaddtime5;

    private Short homescore;

    private Short guestscore;

    private Short matchstate;

    private Short homefast;

    private Short guestfast;

    private Short homeinside;

    private Short guestinside;

    private Short homeexceed;

    private Short guestexceed;

    private String tv;

    private String umpire;

    private String costtime;

    private Integer visitor;

    private Boolean addtechnic;

    private String remaintime;

    private Date bfChangetime;

    private String homeOrder;

    private String guestOrder;

    private String roundtype1;

    private String roundtype2;

    private Byte roundkind;

    private Boolean addstats;

    private Boolean bfshow;

    private Boolean isneutral;

    private String descript;

    private Integer playoffsid;

    private Integer cupqualifyid;

    private String explainlist;

    private Integer sid;
    
    private String explain;

    private String explain2;
    
    private static final long serialVersionUID = 1L;

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }

    public String getExplain2() {
        return explain2;
    }

    public void setExplain2(String explain2) {
        this.explain2 = explain2 == null ? null : explain2.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getSclassid() {
        return sclassid;
    }

    public void setSclassid(Short sclassid) {
        this.sclassid = sclassid;
    }

    public String getMatchseason() {
        return matchseason;
    }

    public void setMatchseason(String matchseason) {
        this.matchseason = matchseason == null ? null : matchseason.trim();
    }

    public Short getMatchkind() {
        return matchkind;
    }

    public void setMatchkind(Short matchkind) {
        this.matchkind = matchkind;
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

    public Date getMatchtime() {
        return matchtime;
    }

    public void setMatchtime(Date matchtime) {
        this.matchtime = matchtime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Short getHomeone() {
        return homeone;
    }

    public void setHomeone(Short homeone) {
        this.homeone = homeone;
    }

    public Short getHometwo() {
        return hometwo;
    }

    public void setHometwo(Short hometwo) {
        this.hometwo = hometwo;
    }

    public Short getHomethree() {
        return homethree;
    }

    public void setHomethree(Short homethree) {
        this.homethree = homethree;
    }

    public Short getHomefour() {
        return homefour;
    }

    public void setHomefour(Short homefour) {
        this.homefour = homefour;
    }

    public Short getGuestone() {
        return guestone;
    }

    public void setGuestone(Short guestone) {
        this.guestone = guestone;
    }

    public Short getGuesttwo() {
        return guesttwo;
    }

    public void setGuesttwo(Short guesttwo) {
        this.guesttwo = guesttwo;
    }

    public Short getGuestthree() {
        return guestthree;
    }

    public void setGuestthree(Short guestthree) {
        this.guestthree = guestthree;
    }

    public Short getGuestfour() {
        return guestfour;
    }

    public void setGuestfour(Short guestfour) {
        this.guestfour = guestfour;
    }

    public Short getAddtime() {
        return addtime;
    }

    public void setAddtime(Short addtime) {
        this.addtime = addtime;
    }

    public Short getHomeaddtime1() {
        return homeaddtime1;
    }

    public void setHomeaddtime1(Short homeaddtime1) {
        this.homeaddtime1 = homeaddtime1;
    }

    public Short getHomeaddtime2() {
        return homeaddtime2;
    }

    public void setHomeaddtime2(Short homeaddtime2) {
        this.homeaddtime2 = homeaddtime2;
    }

    public Short getHomeaddtime3() {
        return homeaddtime3;
    }

    public void setHomeaddtime3(Short homeaddtime3) {
        this.homeaddtime3 = homeaddtime3;
    }

    public Short getHomeaddtime4() {
        return homeaddtime4;
    }

    public void setHomeaddtime4(Short homeaddtime4) {
        this.homeaddtime4 = homeaddtime4;
    }

    public Short getHomeaddtime5() {
        return homeaddtime5;
    }

    public void setHomeaddtime5(Short homeaddtime5) {
        this.homeaddtime5 = homeaddtime5;
    }

    public Short getGuestaddtime1() {
        return guestaddtime1;
    }

    public void setGuestaddtime1(Short guestaddtime1) {
        this.guestaddtime1 = guestaddtime1;
    }

    public Short getGuestaddtime2() {
        return guestaddtime2;
    }

    public void setGuestaddtime2(Short guestaddtime2) {
        this.guestaddtime2 = guestaddtime2;
    }

    public Short getGuestaddtime3() {
        return guestaddtime3;
    }

    public void setGuestaddtime3(Short guestaddtime3) {
        this.guestaddtime3 = guestaddtime3;
    }

    public Short getGuestaddtime4() {
        return guestaddtime4;
    }

    public void setGuestaddtime4(Short guestaddtime4) {
        this.guestaddtime4 = guestaddtime4;
    }

    public Short getGuestaddtime5() {
        return guestaddtime5;
    }

    public void setGuestaddtime5(Short guestaddtime5) {
        this.guestaddtime5 = guestaddtime5;
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

    public Short getMatchstate() {
        return matchstate;
    }

    public void setMatchstate(Short matchstate) {
        this.matchstate = matchstate;
    }

    public Short getHomefast() {
        return homefast;
    }

    public void setHomefast(Short homefast) {
        this.homefast = homefast;
    }

    public Short getGuestfast() {
        return guestfast;
    }

    public void setGuestfast(Short guestfast) {
        this.guestfast = guestfast;
    }

    public Short getHomeinside() {
        return homeinside;
    }

    public void setHomeinside(Short homeinside) {
        this.homeinside = homeinside;
    }

    public Short getGuestinside() {
        return guestinside;
    }

    public void setGuestinside(Short guestinside) {
        this.guestinside = guestinside;
    }

    public Short getHomeexceed() {
        return homeexceed;
    }

    public void setHomeexceed(Short homeexceed) {
        this.homeexceed = homeexceed;
    }

    public Short getGuestexceed() {
        return guestexceed;
    }

    public void setGuestexceed(Short guestexceed) {
        this.guestexceed = guestexceed;
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

    public String getCosttime() {
        return costtime;
    }

    public void setCosttime(String costtime) {
        this.costtime = costtime == null ? null : costtime.trim();
    }

    public Integer getVisitor() {
        return visitor;
    }

    public void setVisitor(Integer visitor) {
        this.visitor = visitor;
    }

    public Boolean getAddtechnic() {
        return addtechnic;
    }

    public void setAddtechnic(Boolean addtechnic) {
        this.addtechnic = addtechnic;
    }

    public String getRemaintime() {
        return remaintime;
    }

    public void setRemaintime(String remaintime) {
        this.remaintime = remaintime == null ? null : remaintime.trim();
    }

    public Date getBfChangetime() {
        return bfChangetime;
    }

    public void setBfChangetime(Date bfChangetime) {
        this.bfChangetime = bfChangetime;
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

    public Byte getRoundkind() {
        return roundkind;
    }

    public void setRoundkind(Byte roundkind) {
        this.roundkind = roundkind;
    }

    public Boolean getAddstats() {
        return addstats;
    }

    public void setAddstats(Boolean addstats) {
        this.addstats = addstats;
    }

    public Boolean getBfshow() {
        return bfshow;
    }

    public void setBfshow(Boolean bfshow) {
        this.bfshow = bfshow;
    }

    public Boolean getIsneutral() {
        return isneutral;
    }

    public void setIsneutral(Boolean isneutral) {
        this.isneutral = isneutral;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript == null ? null : descript.trim();
    }

    public Integer getPlayoffsid() {
        return playoffsid;
    }

    public void setPlayoffsid(Integer playoffsid) {
        this.playoffsid = playoffsid;
    }

    public Integer getCupqualifyid() {
        return cupqualifyid;
    }

    public void setCupqualifyid(Integer cupqualifyid) {
        this.cupqualifyid = cupqualifyid;
    }

    public String getExplainlist() {
        return explainlist;
    }

    public void setExplainlist(String explainlist) {
        this.explainlist = explainlist == null ? null : explainlist.trim();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}