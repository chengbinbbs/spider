package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class ZqCupMatchGrouping implements Serializable {
    private Integer groupid;

    private Integer sclassid;

    private String matchseason;

    private Boolean isgroup;

    private String groupname;

    private String groupnameen;

    private Short groupnum;

    private Boolean iscurrentgroup;

    private Short taxis;

    private Date adddatetime;

    private String lymatch;

    private Short linecount;

    private String groupnameF;

    private Boolean iszu;

    private Boolean ifmain;

    private static final long serialVersionUID = 1L;

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
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

    public Boolean getIsgroup() {
        return isgroup;
    }

    public void setIsgroup(Boolean isgroup) {
        this.isgroup = isgroup;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public String getGroupnameen() {
        return groupnameen;
    }

    public void setGroupnameen(String groupnameen) {
        this.groupnameen = groupnameen == null ? null : groupnameen.trim();
    }

    public Short getGroupnum() {
        return groupnum;
    }

    public void setGroupnum(Short groupnum) {
        this.groupnum = groupnum;
    }

    public Boolean getIscurrentgroup() {
        return iscurrentgroup;
    }

    public void setIscurrentgroup(Boolean iscurrentgroup) {
        this.iscurrentgroup = iscurrentgroup;
    }

    public Short getTaxis() {
        return taxis;
    }

    public void setTaxis(Short taxis) {
        this.taxis = taxis;
    }

    public Date getAdddatetime() {
        return adddatetime;
    }

    public void setAdddatetime(Date adddatetime) {
        this.adddatetime = adddatetime;
    }

    public String getLymatch() {
        return lymatch;
    }

    public void setLymatch(String lymatch) {
        this.lymatch = lymatch == null ? null : lymatch.trim();
    }

    public Short getLinecount() {
        return linecount;
    }

    public void setLinecount(Short linecount) {
        this.linecount = linecount;
    }

    public String getGroupnameF() {
        return groupnameF;
    }

    public void setGroupnameF(String groupnameF) {
        this.groupnameF = groupnameF == null ? null : groupnameF.trim();
    }

    public Boolean getIszu() {
        return iszu;
    }

    public void setIszu(Boolean iszu) {
        this.iszu = iszu;
    }

    public Boolean getIfmain() {
        return ifmain;
    }

    public void setIfmain(Boolean ifmain) {
        this.ifmain = ifmain;
    }
}