package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class ZqSubSclassInSeason implements Serializable {
    private Integer id;

    private Integer subsclassid;

    private String matchseason;

    private Integer sortnumber;

    private Integer currRound;

    private Integer countRound;

    private Boolean iscurrentsclass;

    private Boolean ishavescore;

    private Boolean isanalyscore;

    private Boolean iszu;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubsclassid() {
        return subsclassid;
    }

    public void setSubsclassid(Integer subsclassid) {
        this.subsclassid = subsclassid;
    }

    public String getMatchseason() {
        return matchseason;
    }

    public void setMatchseason(String matchseason) {
        this.matchseason = matchseason == null ? null : matchseason.trim();
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

    public Boolean getIshavescore() {
        return ishavescore;
    }

    public void setIshavescore(Boolean ishavescore) {
        this.ishavescore = ishavescore;
    }

    public Boolean getIsanalyscore() {
        return isanalyscore;
    }

    public void setIsanalyscore(Boolean isanalyscore) {
        this.isanalyscore = isanalyscore;
    }

    public Boolean getIszu() {
        return iszu;
    }

    public void setIszu(Boolean iszu) {
        this.iszu = iszu;
    }
}