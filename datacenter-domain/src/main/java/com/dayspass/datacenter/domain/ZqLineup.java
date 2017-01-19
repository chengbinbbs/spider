package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class ZqLineup implements Serializable {
    private Integer id;

    private Integer scheduleid;

    private String homeForm;

    private String guestForm;

    private String homeLineup;

    private String guestLineup;

    private String homeBackup;

    private String guestBackup;

    private Date createTime;

    private Date updateTime;
    
    private Boolean isStatis;

    private static final long serialVersionUID = 1L;
    
    
    public Boolean getIsStatis() {
		return isStatis;
	}

	public void setIsStatis(Boolean isStatis) {
		this.isStatis = isStatis;
	}

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

    public String getHomeForm() {
        return homeForm;
    }

    public void setHomeForm(String homeForm) {
        this.homeForm = homeForm == null ? null : homeForm.trim();
    }

    public String getGuestForm() {
        return guestForm;
    }

    public void setGuestForm(String guestForm) {
        this.guestForm = guestForm == null ? null : guestForm.trim();
    }

    public String getHomeLineup() {
        return homeLineup;
    }

    public void setHomeLineup(String homeLineup) {
        this.homeLineup = homeLineup == null ? null : homeLineup.trim();
    }

    public String getGuestLineup() {
        return guestLineup;
    }

    public void setGuestLineup(String guestLineup) {
        this.guestLineup = guestLineup == null ? null : guestLineup.trim();
    }

    public String getHomeBackup() {
        return homeBackup;
    }

    public void setHomeBackup(String homeBackup) {
        this.homeBackup = homeBackup == null ? null : homeBackup.trim();
    }

    public String getGuestBackup() {
        return guestBackup;
    }

    public void setGuestBackup(String guestBackup) {
        this.guestBackup = guestBackup == null ? null : guestBackup.trim();
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