package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * PlayerDetailParam.java描述了解析球员技术统计信息xml对应参数
 * 
 * @author zcb
 * @version 1.0.0, 2016-01-22
 */
public class ZqPlayerDetailParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int ID; //球员id
	@XmlElement
	private int TeamID; //球队id
	@XmlElement
	private int number; //球衣号
	@XmlElement
	private String playerName; //球员名，简繁
	@XmlElement
	private String positionName; //位置
	@XmlElement
	private int shots; //射门
	@XmlElement
	private int shotsTarget; //射正
	@XmlElement
	private int keyPass; //关键传球
	@XmlElement
	private float PassRate; //传球成功率
	@XmlElement
	private int aerialWon; //争项成功
	@XmlElement
	private int touches; //身体接触
	@XmlElement
	private int dribblesWon; //带球摆脱
	@XmlElement
	private int wasFouled; //被犯规
	@XmlElement
	private int dispossessed; //失去球权
	@XmlElement
	private int turnOver; //失误
	@XmlElement
	private int Offsides; //越位
	@XmlElement
	private int tackles; //铲断
	@XmlElement
	private int interception; //拦截
	@XmlElement
	private int clearances; //解围
	@XmlElement
	private int clearanceWon; //有效解围
	@XmlElement
	private int shotsBlocked; //封堵
	@XmlElement
	private int OffsideProvoked; //造越位
	@XmlElement
	private int fouls; //犯规
	@XmlElement
	private int totalPass; //传球
	@XmlElement
	private int accuratePass; //传球成功
	@XmlElement
	private int CrossNum; //横传
	@XmlElement
	private int CrossWon; //精确横传
	@XmlElement
	private int longBall; //长传
	@XmlElement
	private int longBallWon; //精确长传
	@XmlElement
	private int throughBall; //直塞
	@XmlElement
	private int throughBallWon; //精确直塞
	@XmlElement
	private float rating; //评分
	
	public ZqPlayerDetailParam() {
		super();
	}
	@XmlTransient
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	@XmlTransient
	public int getTeamID() {
		return TeamID;
	}

	public void setTeamID(int teamID) {
		TeamID = teamID;
	}
	@XmlTransient
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	@XmlTransient
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	@XmlTransient
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	@XmlTransient
	public int getShots() {
		return shots;
	}

	public void setShots(int shots) {
		this.shots = shots;
	}
	@XmlTransient
	public int getShotsTarget() {
		return shotsTarget;
	}

	public void setShotsTarget(int shotsTarget) {
		this.shotsTarget = shotsTarget;
	}
	@XmlTransient
	public int getKeyPass() {
		return keyPass;
	}

	public void setKeyPass(int keyPass) {
		this.keyPass = keyPass;
	}
	@XmlTransient
	public float getPassRate() {
		return PassRate;
	}

	public void setPassRate(float passRate) {
		PassRate = passRate;
	}
	@XmlTransient
	public int getAerialWon() {
		return aerialWon;
	}

	public void setAerialWon(int aerialWon) {
		this.aerialWon = aerialWon;
	}
	@XmlTransient
	public int getTouches() {
		return touches;
	}

	public void setTouches(int touches) {
		this.touches = touches;
	}
	@XmlTransient
	public int getDribblesWon() {
		return dribblesWon;
	}

	public void setDribblesWon(int dribblesWon) {
		this.dribblesWon = dribblesWon;
	}
	@XmlTransient
	public int getWasFouled() {
		return wasFouled;
	}

	public void setWasFouled(int wasFouled) {
		this.wasFouled = wasFouled;
	}
	@XmlTransient
	public int getDispossessed() {
		return dispossessed;
	}

	public void setDispossessed(int dispossessed) {
		this.dispossessed = dispossessed;
	}
	@XmlTransient
	public int getTurnOver() {
		return turnOver;
	}

	public void setTurnOver(int turnOver) {
		this.turnOver = turnOver;
	}
	@XmlTransient
	public int getOffsides() {
		return Offsides;
	}

	public void setOffsides(int offsides) {
		Offsides = offsides;
	}
	@XmlTransient
	public int getTackles() {
		return tackles;
	}

	public void setTackles(int tackles) {
		this.tackles = tackles;
	}
	@XmlTransient
	public int getInterception() {
		return interception;
	}

	public void setInterception(int interception) {
		this.interception = interception;
	}
	@XmlTransient
	public int getClearances() {
		return clearances;
	}

	public void setClearances(int clearances) {
		this.clearances = clearances;
	}
	@XmlTransient
	public int getClearanceWon() {
		return clearanceWon;
	}

	public void setClearanceWon(int clearanceWon) {
		this.clearanceWon = clearanceWon;
	}
	@XmlTransient
	public int getShotsBlocked() {
		return shotsBlocked;
	}

	public void setShotsBlocked(int shotsBlocked) {
		this.shotsBlocked = shotsBlocked;
	}
	@XmlTransient
	public int getOffsideProvoked() {
		return OffsideProvoked;
	}

	public void setOffsideProvoked(int offsideProvoked) {
		OffsideProvoked = offsideProvoked;
	}
	@XmlTransient
	public int getFouls() {
		return fouls;
	}

	public void setFouls(int fouls) {
		this.fouls = fouls;
	}
	@XmlTransient
	public int getTotalPass() {
		return totalPass;
	}

	public void setTotalPass(int totalPass) {
		this.totalPass = totalPass;
	}
	@XmlTransient
	public int getAccuratePass() {
		return accuratePass;
	}

	public void setAccuratePass(int accuratePass) {
		this.accuratePass = accuratePass;
	}
	@XmlTransient
	public int getCrossNum() {
		return CrossNum;
	}

	public void setCrossNum(int crossNum) {
		CrossNum = crossNum;
	}
	@XmlTransient
	public int getCrossWon() {
		return CrossWon;
	}

	public void setCrossWon(int crossWon) {
		CrossWon = crossWon;
	}
	@XmlTransient
	public int getLongBall() {
		return longBall;
	}

	public void setLongBall(int longBall) {
		this.longBall = longBall;
	}
	@XmlTransient
	public int getLongBallWon() {
		return longBallWon;
	}

	public void setLongBallWon(int longBallWon) {
		this.longBallWon = longBallWon;
	}
	@XmlTransient
	public int getThroughBall() {
		return throughBall;
	}

	public void setThroughBall(int throughBall) {
		this.throughBall = throughBall;
	}
	@XmlTransient
	public int getThroughBallWon() {
		return throughBallWon;
	}

	public void setThroughBallWon(int throughBallWon) {
		this.throughBallWon = throughBallWon;
	}
	@XmlTransient
	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	
	
	
}
