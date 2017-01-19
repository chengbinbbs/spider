package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class ZqInjure implements Serializable {
    private Integer id;

    private Integer scheduleid;
    
    private String brief;

    private String recommend;

    private String homeStop;

    private String guestStop;

    private String homeInjure;

    private String guestInjure;
    
    private Boolean isStatis;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Integer scheduleid) {
        this.scheduleid = scheduleid;
    }
    
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }

    public Boolean getIsStatis() {
		return isStatis;
	}

	public void setIsStatis(Boolean isStatis) {
		this.isStatis = isStatis;
	}

	public String getHomeStop() {
        return homeStop;
    }

    public void setHomeStop(String homeStop) {
        this.homeStop = homeStop == null ? null : homeStop.trim();
    }

    public String getGuestStop() {
        return guestStop;
    }

    public void setGuestStop(String guestStop) {
        this.guestStop = guestStop == null ? null : guestStop.trim();
    }

    public String getHomeInjure() {
        return homeInjure;
    }

    public void setHomeInjure(String homeInjure) {
        this.homeInjure = homeInjure == null ? null : homeInjure.trim();
    }

    public String getGuestInjure() {
        return guestInjure;
    }

    public void setGuestInjure(String guestInjure) {
        this.guestInjure = guestInjure == null ? null : guestInjure.trim();
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