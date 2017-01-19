package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class ZqSclass implements Serializable {
    private Integer sclassid;

    private String color;

    private String nameJ;

    private String nameF;

    private String nameE;

    private String nameJs;

    private String nameFs;

    private String nameEs;

    private String nameS;

    private Short kind;

    private Short mode;

    private Short countRound;

    private Short currRound;

    private String currMatchseason;

    private String sclassPic;

    private Byte ifstop;

    private Byte sclassType;

    private Short countGroup;

    private Byte bfSimplyDisp;

    private Short sclassSequence;

    private Short infoid;

    private String infourl;

    private Date modifytime;

    private String beginseason;

    private Integer subsclassid;

    private Boolean ifhavesub;

    private Boolean ifsort;

    private String sclassRule;
    
    private Integer leagueId;

    private static final long serialVersionUID = 1L;
    

    public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}

	public Integer getSclassid() {
        return sclassid;
    }

    public void setSclassid(Integer sclassid) {
        this.sclassid = sclassid;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
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

    public String getNameJs() {
        return nameJs;
    }

    public void setNameJs(String nameJs) {
        this.nameJs = nameJs == null ? null : nameJs.trim();
    }

    public String getNameFs() {
        return nameFs;
    }

    public void setNameFs(String nameFs) {
        this.nameFs = nameFs == null ? null : nameFs.trim();
    }

    public String getNameEs() {
        return nameEs;
    }

    public void setNameEs(String nameEs) {
        this.nameEs = nameEs == null ? null : nameEs.trim();
    }

    public String getNameS() {
        return nameS;
    }

    public void setNameS(String nameS) {
        this.nameS = nameS == null ? null : nameS.trim();
    }

    public Short getKind() {
        return kind;
    }

    public void setKind(Short kind) {
        this.kind = kind;
    }

    public Short getMode() {
        return mode;
    }

    public void setMode(Short mode) {
        this.mode = mode;
    }

    public Short getCountRound() {
        return countRound;
    }

    public void setCountRound(Short countRound) {
        this.countRound = countRound;
    }

    public Short getCurrRound() {
        return currRound;
    }

    public void setCurrRound(Short currRound) {
        this.currRound = currRound;
    }

    public String getCurrMatchseason() {
        return currMatchseason;
    }

    public void setCurrMatchseason(String currMatchseason) {
        this.currMatchseason = currMatchseason == null ? null : currMatchseason.trim();
    }

    public String getSclassPic() {
        return sclassPic;
    }

    public void setSclassPic(String sclassPic) {
        this.sclassPic = sclassPic == null ? null : sclassPic.trim();
    }

    public Byte getIfstop() {
        return ifstop;
    }

    public void setIfstop(Byte ifstop) {
        this.ifstop = ifstop;
    }

    public Byte getSclassType() {
        return sclassType;
    }

    public void setSclassType(Byte sclassType) {
        this.sclassType = sclassType;
    }

    public Short getCountGroup() {
        return countGroup;
    }

    public void setCountGroup(Short countGroup) {
        this.countGroup = countGroup;
    }

    public Byte getBfSimplyDisp() {
        return bfSimplyDisp;
    }

    public void setBfSimplyDisp(Byte bfSimplyDisp) {
        this.bfSimplyDisp = bfSimplyDisp;
    }

    public Short getSclassSequence() {
        return sclassSequence;
    }

    public void setSclassSequence(Short sclassSequence) {
        this.sclassSequence = sclassSequence;
    }

    public Short getInfoid() {
        return infoid;
    }

    public void setInfoid(Short infoid) {
        this.infoid = infoid;
    }

    public String getInfourl() {
        return infourl;
    }

    public void setInfourl(String infourl) {
        this.infourl = infourl == null ? null : infourl.trim();
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getBeginseason() {
        return beginseason;
    }

    public void setBeginseason(String beginseason) {
        this.beginseason = beginseason == null ? null : beginseason.trim();
    }

    public Integer getSubsclassid() {
        return subsclassid;
    }

    public void setSubsclassid(Integer subsclassid) {
        this.subsclassid = subsclassid;
    }

    public Boolean getIfhavesub() {
        return ifhavesub;
    }

    public void setIfhavesub(Boolean ifhavesub) {
        this.ifhavesub = ifhavesub;
    }

    public Boolean getIfsort() {
        return ifsort;
    }

    public void setIfsort(Boolean ifsort) {
        this.ifsort = ifsort;
    }

    public String getSclassRule() {
        return sclassRule;
    }

    public void setSclassRule(String sclassRule) {
        this.sclassRule = sclassRule == null ? null : sclassRule.trim();
    }
}