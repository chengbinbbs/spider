package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class LqSclass implements Serializable {
    private Integer sclassid;

    private String nameEn;

    private String nameCn;

    private String nameTw;

    private String color;

    private Byte sclasstype;

    private String sclasstime;

    private String currmatchseason;

    private Integer curryear;

    private Integer currmonth;

    private Integer listid;

    private Byte sclasskind;

    private Byte infoid;

    private String flagpic;

    private Boolean ifshow;

    private String roundtypes;

    private Byte orderkind;

    private String beginseason;

    private String nameFs;

    private Boolean bfSimplyDisp;

    private Integer leagueId;

    private static final long serialVersionUID = 1L;

    public Integer getSclassid() {
        return sclassid;
    }

    public void setSclassid(Integer sclassid) {
        this.sclassid = sclassid;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn == null ? null : nameCn.trim();
    }

    public String getNameTw() {
        return nameTw;
    }

    public void setNameTw(String nameTw) {
        this.nameTw = nameTw == null ? null : nameTw.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public Byte getSclasstype() {
        return sclasstype;
    }

    public void setSclasstype(Byte sclasstype) {
        this.sclasstype = sclasstype;
    }

    public String getSclasstime() {
        return sclasstime;
    }

    public void setSclasstime(String sclasstime) {
        this.sclasstime = sclasstime == null ? null : sclasstime.trim();
    }

    public String getCurrmatchseason() {
        return currmatchseason;
    }

    public void setCurrmatchseason(String currmatchseason) {
        this.currmatchseason = currmatchseason == null ? null : currmatchseason.trim();
    }

    public Integer getCurryear() {
        return curryear;
    }

    public void setCurryear(Integer curryear) {
        this.curryear = curryear;
    }

    public Integer getCurrmonth() {
        return currmonth;
    }

    public void setCurrmonth(Integer currmonth) {
        this.currmonth = currmonth;
    }

    public Integer getListid() {
        return listid;
    }

    public void setListid(Integer listid) {
        this.listid = listid;
    }

    public Byte getSclasskind() {
        return sclasskind;
    }

    public void setSclasskind(Byte sclasskind) {
        this.sclasskind = sclasskind;
    }

    public Byte getInfoid() {
        return infoid;
    }

    public void setInfoid(Byte infoid) {
        this.infoid = infoid;
    }

    public String getFlagpic() {
        return flagpic;
    }

    public void setFlagpic(String flagpic) {
        this.flagpic = flagpic == null ? null : flagpic.trim();
    }

    public Boolean getIfshow() {
        return ifshow;
    }

    public void setIfshow(Boolean ifshow) {
        this.ifshow = ifshow;
    }

    public String getRoundtypes() {
        return roundtypes;
    }

    public void setRoundtypes(String roundtypes) {
        this.roundtypes = roundtypes == null ? null : roundtypes.trim();
    }

    public Byte getOrderkind() {
        return orderkind;
    }

    public void setOrderkind(Byte orderkind) {
        this.orderkind = orderkind;
    }

    public String getBeginseason() {
        return beginseason;
    }

    public void setBeginseason(String beginseason) {
        this.beginseason = beginseason == null ? null : beginseason.trim();
    }

    public String getNameFs() {
        return nameFs;
    }

    public void setNameFs(String nameFs) {
        this.nameFs = nameFs == null ? null : nameFs.trim();
    }

    public Boolean getBfSimplyDisp() {
        return bfSimplyDisp;
    }

    public void setBfSimplyDisp(Boolean bfSimplyDisp) {
        this.bfSimplyDisp = bfSimplyDisp;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }
}