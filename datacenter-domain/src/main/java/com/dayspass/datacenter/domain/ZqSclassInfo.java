package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class ZqSclassInfo implements Serializable {
    private Integer infoid;

    private String namecn;

    private String nameen;

    private String namefn;

    private String flagpic;

    private Short infoorder;

    private Byte infoType;

    private Date modifytime;

    private Short allorder;

    private String nameth;

    private String namekr;

    private static final long serialVersionUID = 1L;

    public Integer getInfoid() {
        return infoid;
    }

    public void setInfoid(Integer infoid) {
        this.infoid = infoid;
    }

    public String getNamecn() {
        return namecn;
    }

    public void setNamecn(String namecn) {
        this.namecn = namecn == null ? null : namecn.trim();
    }

    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen == null ? null : nameen.trim();
    }

    public String getNamefn() {
        return namefn;
    }

    public void setNamefn(String namefn) {
        this.namefn = namefn == null ? null : namefn.trim();
    }

    public String getFlagpic() {
        return flagpic;
    }

    public void setFlagpic(String flagpic) {
        this.flagpic = flagpic == null ? null : flagpic.trim();
    }

    public Short getInfoorder() {
        return infoorder;
    }

    public void setInfoorder(Short infoorder) {
        this.infoorder = infoorder;
    }

    public Byte getInfoType() {
        return infoType;
    }

    public void setInfoType(Byte infoType) {
        this.infoType = infoType;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Short getAllorder() {
        return allorder;
    }

    public void setAllorder(Short allorder) {
        this.allorder = allorder;
    }

    public String getNameth() {
        return nameth;
    }

    public void setNameth(String nameth) {
        this.nameth = nameth == null ? null : nameth.trim();
    }

    public String getNamekr() {
        return namekr;
    }

    public void setNamekr(String namekr) {
        this.namekr = namekr == null ? null : namekr.trim();
    }
}