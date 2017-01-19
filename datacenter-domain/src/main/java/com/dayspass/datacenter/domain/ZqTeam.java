package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class ZqTeam implements Serializable {
    private Integer teamid;

    private Short kind;

    private Integer sclassid;

    private String nameShort;

    private String nameJ;

    private String nameF;

    private String nameE;

    private String foundDate;

    private String area;

    private String gymnasium;

    private Integer capacity;

    private String flag;

    private String address;

    private String url;

    private String introduce;

    private String drillmaster;

    private String masterpic;

    private String guestpoloshirt;

    private String homepoloshirt;

    private Date modifytime;

    private String masterintro;

    private static final long serialVersionUID = 1L;

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public Short getKind() {
        return kind;
    }

    public void setKind(Short kind) {
        this.kind = kind;
    }

    public Integer getSclassid() {
        return sclassid;
    }

    public void setSclassid(Integer sclassid) {
        this.sclassid = sclassid;
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort == null ? null : nameShort.trim();
    }

    public String getNameJ() {
        return nameJ;
    }

    public void setNameJ(String nameJ) {
        this.nameJ = nameJ == null ? null : nameJ.trim();
    }

    public String getNameF() {
        return nameF;
    }

    public void setNameF(String nameF) {
        this.nameF = nameF == null ? null : nameF.trim();
    }

    public String getNameE() {
        return nameE;
    }

    public void setNameE(String nameE) {
        this.nameE = nameE == null ? null : nameE.trim();
    }

    public String getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(String foundDate) {
        this.foundDate = foundDate == null ? null : foundDate.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
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

    public String getGuestpoloshirt() {
        return guestpoloshirt;
    }

    public void setGuestpoloshirt(String guestpoloshirt) {
        this.guestpoloshirt = guestpoloshirt == null ? null : guestpoloshirt.trim();
    }

    public String getHomepoloshirt() {
        return homepoloshirt;
    }

    public void setHomepoloshirt(String homepoloshirt) {
        this.homepoloshirt = homepoloshirt == null ? null : homepoloshirt.trim();
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getMasterintro() {
        return masterintro;
    }

    public void setMasterintro(String masterintro) {
        this.masterintro = masterintro == null ? null : masterintro.trim();
    }
}