package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class LqSclassInfo implements Serializable {
    private Integer infoid;

    private String namecn;

    private String namefn;

    private String nameen;

    private String flagpic;

    private Short infoorder;

    private Boolean ifshow;

    private Integer infotype;

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

    public String getNamefn() {
        return namefn;
    }

    public void setNamefn(String namefn) {
        this.namefn = namefn == null ? null : namefn.trim();
    }

    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen == null ? null : nameen.trim();
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

    public Boolean getIfshow() {
        return ifshow;
    }

    public void setIfshow(Boolean ifshow) {
        this.ifshow = ifshow;
    }

    public Integer getInfotype() {
        return infotype;
    }

    public void setInfotype(Integer infotype) {
        this.infotype = infotype;
    }
}