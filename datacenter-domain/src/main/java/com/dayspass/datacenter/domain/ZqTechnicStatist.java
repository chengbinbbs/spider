package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class ZqTechnicStatist implements Serializable {
    private Integer scheduleid;

    private String techniccount;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Integer scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getTechniccount() {
        return techniccount;
    }

    public void setTechniccount(String techniccount) {
        this.techniccount = techniccount == null ? null : techniccount.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}