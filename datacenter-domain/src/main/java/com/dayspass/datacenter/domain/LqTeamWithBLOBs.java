package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class LqTeamWithBLOBs extends LqTeam implements Serializable {
    private String formergrade;

    private String masterintro;

    private String introduce;

    private String masterinfo;

    private static final long serialVersionUID = 1L;

    public String getFormergrade() {
        return formergrade;
    }

    public void setFormergrade(String formergrade) {
        this.formergrade = formergrade == null ? null : formergrade.trim();
    }

    public String getMasterintro() {
        return masterintro;
    }

    public void setMasterintro(String masterintro) {
        this.masterintro = masterintro == null ? null : masterintro.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getMasterinfo() {
        return masterinfo;
    }

    public void setMasterinfo(String masterinfo) {
        this.masterinfo = masterinfo == null ? null : masterinfo.trim();
    }
}