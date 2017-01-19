package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class ZqMatchLottery implements Serializable {
    private Integer id;

    private String lotteryname;

    private String sort;

    private Integer stageid;

    private Integer scheduleid;

    private Date mtime;

    private String hometeamname;

    private Integer hometeamid;

    private String guestteamname;

    private Integer guestteamid;

    private Boolean swapteam;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLotteryname() {
        return lotteryname;
    }

    public void setLotteryname(String lotteryname) {
        this.lotteryname = lotteryname == null ? null : lotteryname.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public Integer getStageid() {
        return stageid;
    }

    public void setStageid(Integer stageid) {
        this.stageid = stageid;
    }

    public Integer getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Integer scheduleid) {
        this.scheduleid = scheduleid;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public String getHometeamname() {
        return hometeamname;
    }

    public void setHometeamname(String hometeamname) {
        this.hometeamname = hometeamname == null ? null : hometeamname.trim();
    }

    public Integer getHometeamid() {
        return hometeamid;
    }

    public void setHometeamid(Integer hometeamid) {
        this.hometeamid = hometeamid;
    }

    public String getGuestteamname() {
        return guestteamname;
    }

    public void setGuestteamname(String guestteamname) {
        this.guestteamname = guestteamname == null ? null : guestteamname.trim();
    }

    public Integer getGuestteamid() {
        return guestteamid;
    }

    public void setGuestteamid(Integer guestteamid) {
        this.guestteamid = guestteamid;
    }

    public Boolean getSwapteam() {
        return swapteam;
    }

    public void setSwapteam(Boolean swapteam) {
        this.swapteam = swapteam;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}