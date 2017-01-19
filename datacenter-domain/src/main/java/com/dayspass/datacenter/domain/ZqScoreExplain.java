package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class ZqScoreExplain implements Serializable {
    private Integer id;

    private Integer sclassid;

    private String scoreExplain;

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

    public String getScoreExplain() {
        return scoreExplain;
    }

    public void setScoreExplain(String scoreExplain) {
        this.scoreExplain = scoreExplain == null ? null : scoreExplain.trim();
    }
}