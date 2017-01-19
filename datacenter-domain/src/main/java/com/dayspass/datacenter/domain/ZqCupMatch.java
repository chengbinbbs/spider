package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class ZqCupMatch implements Serializable {
    private Integer id;

    private Integer sclassid;

    private Integer cupmatchType;

    private String grouping;

    private Byte area;

    private String content;

    private String strcontent;

    private String matchseason;

    private Short linecount;

    private Boolean isupdate;

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

    public Integer getCupmatchType() {
        return cupmatchType;
    }

    public void setCupmatchType(Integer cupmatchType) {
        this.cupmatchType = cupmatchType;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping == null ? null : grouping.trim();
    }

    public Byte getArea() {
        return area;
    }

    public void setArea(Byte area) {
        this.area = area;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getStrcontent() {
        return strcontent;
    }

    public void setStrcontent(String strcontent) {
        this.strcontent = strcontent == null ? null : strcontent.trim();
    }

    public String getMatchseason() {
        return matchseason;
    }

    public void setMatchseason(String matchseason) {
        this.matchseason = matchseason == null ? null : matchseason.trim();
    }

    public Short getLinecount() {
        return linecount;
    }

    public void setLinecount(Short linecount) {
        this.linecount = linecount;
    }

    public Boolean getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(Boolean isupdate) {
        this.isupdate = isupdate;
    }
}