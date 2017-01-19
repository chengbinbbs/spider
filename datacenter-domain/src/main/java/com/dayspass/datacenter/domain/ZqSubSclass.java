package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class ZqSubSclass implements Serializable {
    private Integer subsclassid;

    private Integer sclassid;

    private Boolean ishavescore;

    private Integer sortnumber;

    private Integer currRound;

    private Integer countRound;

    private Boolean iscurrentsclass;

    private String subsclassname;

    private String subsclassnameen;

    private String subnameJs;

    private String subnameEs;

    private String subnameFs;

    private String includeseason;

    private Boolean isanalyscore;

    private String subsclassF;

    private Boolean iszu;

    private String currentseason;

    private String refOrderseason;

    private Integer refOrdersubid;

    private static final long serialVersionUID = 1L;

    public Integer getSubsclassid() {
        return subsclassid;
    }

    public void setSubsclassid(Integer subsclassid) {
        this.subsclassid = subsclassid;
    }

    public Integer getSclassid() {
        return sclassid;
    }

    public void setSclassid(Integer sclassid) {
        this.sclassid = sclassid;
    }

    public Boolean getIshavescore() {
        return ishavescore;
    }

    public void setIshavescore(Boolean ishavescore) {
        this.ishavescore = ishavescore;
    }

    public Integer getSortnumber() {
        return sortnumber;
    }

    public void setSortnumber(Integer sortnumber) {
        this.sortnumber = sortnumber;
    }

    public Integer getCurrRound() {
        return currRound;
    }

    public void setCurrRound(Integer currRound) {
        this.currRound = currRound;
    }

    public Integer getCountRound() {
        return countRound;
    }

    public void setCountRound(Integer countRound) {
        this.countRound = countRound;
    }

    public Boolean getIscurrentsclass() {
        return iscurrentsclass;
    }

    public void setIscurrentsclass(Boolean iscurrentsclass) {
        this.iscurrentsclass = iscurrentsclass;
    }

    public String getSubsclassname() {
        return subsclassname;
    }

    public void setSubsclassname(String subsclassname) {
        this.subsclassname = subsclassname == null ? null : subsclassname.trim();
    }

    public String getSubsclassnameen() {
        return subsclassnameen;
    }

    public void setSubsclassnameen(String subsclassnameen) {
        this.subsclassnameen = subsclassnameen == null ? null : subsclassnameen.trim();
    }

    public String getSubnameJs() {
        return subnameJs;
    }

    public void setSubnameJs(String subnameJs) {
        this.subnameJs = subnameJs == null ? null : subnameJs.trim();
    }

    public String getSubnameEs() {
        return subnameEs;
    }

    public void setSubnameEs(String subnameEs) {
        this.subnameEs = subnameEs == null ? null : subnameEs.trim();
    }

    public String getSubnameFs() {
        return subnameFs;
    }

    public void setSubnameFs(String subnameFs) {
        this.subnameFs = subnameFs == null ? null : subnameFs.trim();
    }

    public String getIncludeseason() {
        return includeseason;
    }

    public void setIncludeseason(String includeseason) {
        this.includeseason = includeseason == null ? null : includeseason.trim();
    }

    public Boolean getIsanalyscore() {
        return isanalyscore;
    }

    public void setIsanalyscore(Boolean isanalyscore) {
        this.isanalyscore = isanalyscore;
    }

    public String getSubsclassF() {
        return subsclassF;
    }

    public void setSubsclassF(String subsclassF) {
        this.subsclassF = subsclassF == null ? null : subsclassF.trim();
    }

    public Boolean getIszu() {
        return iszu;
    }

    public void setIszu(Boolean iszu) {
        this.iszu = iszu;
    }

    public String getCurrentseason() {
        return currentseason;
    }

    public void setCurrentseason(String currentseason) {
        this.currentseason = currentseason == null ? null : currentseason.trim();
    }

    public String getRefOrderseason() {
        return refOrderseason;
    }

    public void setRefOrderseason(String refOrderseason) {
        this.refOrderseason = refOrderseason == null ? null : refOrderseason.trim();
    }

    public Integer getRefOrdersubid() {
        return refOrdersubid;
    }

    public void setRefOrdersubid(Integer refOrdersubid) {
        this.refOrdersubid = refOrdersubid;
    }
}