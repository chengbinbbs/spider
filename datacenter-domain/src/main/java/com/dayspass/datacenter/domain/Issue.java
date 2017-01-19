package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class Issue implements Serializable {
    private Long ID;

    private Long lotteryID;

    private String issue;

    private String drawNumber;

    private Integer drawStatus;

    private Date drawTime;

    private Integer sell;

    private Date sellStartTime;

    private Date sellEndTime;

    private Date openEndTime;

    private Integer autoBuyStartDate;

    private Integer autoBuyEndDate;

    private Integer autoBuyStartTime;

    private Integer autoBuyEndTime;

    private String prize;

    private String parameter;

    private Integer lastProcess;

    private Date lastProcessTime;

    private Integer encashProcess;

    private Date encashProcessTime;

    private Integer issueOrder;

    private static final long serialVersionUID = 1L;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getLotteryID() {
        return lotteryID;
    }

    public void setLotteryID(Long lotteryID) {
        this.lotteryID = lotteryID;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue == null ? null : issue.trim();
    }

    public String getDrawNumber() {
        return drawNumber;
    }

    public void setDrawNumber(String drawNumber) {
        this.drawNumber = drawNumber == null ? null : drawNumber.trim();
    }

    public Integer getDrawStatus() {
        return drawStatus;
    }

    public void setDrawStatus(Integer drawStatus) {
        this.drawStatus = drawStatus;
    }

    public Date getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(Date drawTime) {
        this.drawTime = drawTime;
    }

    public Integer getSell() {
        return sell;
    }

    public void setSell(Integer sell) {
        this.sell = sell;
    }

    public Date getSellStartTime() {
        return sellStartTime;
    }

    public void setSellStartTime(Date sellStartTime) {
        this.sellStartTime = sellStartTime;
    }

    public Date getSellEndTime() {
        return sellEndTime;
    }

    public void setSellEndTime(Date sellEndTime) {
        this.sellEndTime = sellEndTime;
    }

    public Date getOpenEndTime() {
        return openEndTime;
    }

    public void setOpenEndTime(Date openEndTime) {
        this.openEndTime = openEndTime;
    }

    public Integer getAutoBuyStartDate() {
        return autoBuyStartDate;
    }

    public void setAutoBuyStartDate(Integer autoBuyStartDate) {
        this.autoBuyStartDate = autoBuyStartDate;
    }

    public Integer getAutoBuyEndDate() {
        return autoBuyEndDate;
    }

    public void setAutoBuyEndDate(Integer autoBuyEndDate) {
        this.autoBuyEndDate = autoBuyEndDate;
    }

    public Integer getAutoBuyStartTime() {
        return autoBuyStartTime;
    }

    public void setAutoBuyStartTime(Integer autoBuyStartTime) {
        this.autoBuyStartTime = autoBuyStartTime;
    }

    public Integer getAutoBuyEndTime() {
        return autoBuyEndTime;
    }

    public void setAutoBuyEndTime(Integer autoBuyEndTime) {
        this.autoBuyEndTime = autoBuyEndTime;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize == null ? null : prize.trim();
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }

    public Integer getLastProcess() {
        return lastProcess;
    }

    public void setLastProcess(Integer lastProcess) {
        this.lastProcess = lastProcess;
    }

    public Date getLastProcessTime() {
        return lastProcessTime;
    }

    public void setLastProcessTime(Date lastProcessTime) {
        this.lastProcessTime = lastProcessTime;
    }

    public Integer getEncashProcess() {
        return encashProcess;
    }

    public void setEncashProcess(Integer encashProcess) {
        this.encashProcess = encashProcess;
    }

    public Date getEncashProcessTime() {
        return encashProcessTime;
    }

    public void setEncashProcessTime(Date encashProcessTime) {
        this.encashProcessTime = encashProcessTime;
    }

    public Integer getIssueOrder() {
        return issueOrder;
    }

    public void setIssueOrder(Integer issueOrder) {
        this.issueOrder = issueOrder;
    }
}