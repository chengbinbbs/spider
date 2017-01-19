package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class LqTeam implements Serializable {
    private Integer id;

    private Integer sclassid;

    private Integer locationid;

    private Integer matchaddrid;

    private String nameF;

    private String nameJs;

    private String nameJ;

    private String nameE;

    private String url;

    private String city;

    private String gymnasium;

    private Integer capacity;

    private Short joinyear;

    private Short firsttime;

    private String drillmaster;

    private String masterpic;

    private String flag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSclassid() {
        return sclassid;
    }

    public void setSclassid(Integer sclassid) {
        this.sclassid = sclassid;
    }

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public Integer getMatchaddrid() {
        return matchaddrid;
    }

    public void setMatchaddrid(Integer matchaddrid) {
        this.matchaddrid = matchaddrid;
    }

    public String getNameF() {
        return nameF;
    }

    public void setNameF(String nameF) {
        this.nameF = nameF == null ? null : nameF.trim();
    }

    public String getNameJs() {
        return nameJs;
    }

    public void setNameJs(String nameJs) {
        this.nameJs = nameJs == null ? null : nameJs.trim();
    }

    public String getNameJ() {
        return nameJ;
    }

    public void setNameJ(String nameJ) {
        this.nameJ = nameJ == null ? null : nameJ.trim();
    }

    public String getNameE() {
        return nameE;
    }

    public void setNameE(String nameE) {
        this.nameE = nameE == null ? null : nameE.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getGymnasium() {
        return gymnasium;
    }

    public void setGymnasium(String gymnasium) {
        this.gymnasium = gymnasium == null ? null : gymnasium.trim();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Short getJoinyear() {
        return joinyear;
    }

    public void setJoinyear(Short joinyear) {
        this.joinyear = joinyear;
    }

    public Short getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(Short firsttime) {
        this.firsttime = firsttime;
    }

    public String getDrillmaster() {
        return drillmaster;
    }

    public void setDrillmaster(String drillmaster) {
        this.drillmaster = drillmaster == null ? null : drillmaster.trim();
    }

    public String getMasterpic() {
        return masterpic;
    }

    public void setMasterpic(String masterpic) {
        this.masterpic = masterpic == null ? null : masterpic.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}