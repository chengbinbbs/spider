package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class LqPlayer implements Serializable {
    private Integer id;

    private String number;

    private String nameF;

    private String nameFs;

    private String nameJs;

    private String nameJ;

    private String nameE;

    private String nameShort;

    private Integer teamid;

    private String url;

    private String place;

    private Date birthday;

    private Short tallness;

    private Short weight;

    private String comefrom;

    private String photo;

    private Short nbaage;

    private Boolean iskey;

    private Integer positionid;

    private Integer nationalityid;

    private Date endtime;

    private Integer annualsalary;

    private String highschool;

    private String college;

    private String draft;

    private String introduce;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getNameF() {
        return nameF;
    }

    public void setNameF(String nameF) {
        this.nameF = nameF == null ? null : nameF.trim();
    }

    public String getNameFs() {
        return nameFs;
    }

    public void setNameFs(String nameFs) {
        this.nameFs = nameFs == null ? null : nameFs.trim();
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

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort == null ? null : nameShort.trim();
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Short getTallness() {
        return tallness;
    }

    public void setTallness(Short tallness) {
        this.tallness = tallness;
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }

    public String getComefrom() {
        return comefrom;
    }

    public void setComefrom(String comefrom) {
        this.comefrom = comefrom == null ? null : comefrom.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Short getNbaage() {
        return nbaage;
    }

    public void setNbaage(Short nbaage) {
        this.nbaage = nbaage;
    }

    public Boolean getIskey() {
        return iskey;
    }

    public void setIskey(Boolean iskey) {
        this.iskey = iskey;
    }

    public Integer getPositionid() {
        return positionid;
    }

    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }

    public Integer getNationalityid() {
        return nationalityid;
    }

    public void setNationalityid(Integer nationalityid) {
        this.nationalityid = nationalityid;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getAnnualsalary() {
        return annualsalary;
    }

    public void setAnnualsalary(Integer annualsalary) {
        this.annualsalary = annualsalary;
    }

    public String getHighschool() {
        return highschool;
    }

    public void setHighschool(String highschool) {
        this.highschool = highschool == null ? null : highschool.trim();
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    public String getDraft() {
        return draft;
    }

    public void setDraft(String draft) {
        this.draft = draft == null ? null : draft.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}