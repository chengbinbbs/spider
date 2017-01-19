package com.dayspass.datacenter.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * PlayerParam.java描述了解析球员信息xml对应参数
 * 
 * @author zcb
 * @version 1.0.0, 2016-01-22
 */
public class ZqPlayerParam implements Serializable {
	private static final long serialVersionUID = 1L;
	@XmlElement
	private int id; //记录id
	@XmlElement
	private int PlayerID; //球员id
	@XmlElement
	private String Name_J; //球员简体名
	@XmlElement
	private String Name_F; //球员繁体名
	@XmlElement
	private String Name_E; //球员英文名
	@XmlElement
	private String Birthday; //出生日期
	@XmlElement
	private Short Tallness; //身高
	@XmlElement
	private Short Weight; //体重
	@XmlElement
	private String Country; //国籍
	@XmlElement
	private String Photo; //照片
	@XmlElement
	private String Health; //身体状况
	@XmlElement
	private String Value; //身价
	@XmlElement
	private String Feet; //惯用脚
	@XmlElement
	private String Introduce; //球员简介
	@XmlElement
	private int TeamID; //所在球队
	@XmlElement
	private String Place; //位置
	@XmlElement
	private String Number; //球衣号码
	
	public ZqPlayerParam() {
		super();
	}
	@XmlTransient
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@XmlTransient
	public int getPlayerID() {
		return PlayerID;
	}
	public void setPlayerID(int playerID) {
		PlayerID = playerID;
	}
	@XmlTransient
	public String getName_J() {
		return Name_J;
	}
	public void setName_J(String name_J) {
		Name_J = name_J;
	}
	@XmlTransient
	public String getName_F() {
		return Name_F;
	}
	public void setName_F(String name_F) {
		Name_F = name_F;
	}
	@XmlTransient
	public String getName_E() {
		return Name_E;
	}
	public void setName_E(String name_E) {
		Name_E = name_E;
	}
	@XmlTransient
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthday) {
		Birthday = birthday;
	}
	@XmlTransient
	public Short getTallness() {
		return Tallness;
	}
	public void setTallness(Short tallness) {
		Tallness = tallness;
	}
	@XmlTransient
	public Short getWeight() {
		return Weight;
	}
	public void setWeight(Short weight) {
		Weight = weight;
	}
	@XmlTransient
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	@XmlTransient
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	@XmlTransient
	public String getHealth() {
		return Health;
	}
	public void setHealth(String health) {
		Health = health;
	}
	@XmlTransient
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	@XmlTransient
	public String getFeet() {
		return Feet;
	}
	public void setFeet(String feet) {
		Feet = feet;
	}
	@XmlTransient
	public String getIntroduce() {
		return Introduce;
	}
	public void setIntroduce(String introduce) {
		Introduce = introduce;
	}
	@XmlTransient
	public int getTeamID() {
		return TeamID;
	}
	public void setTeamID(int teamID) {
		TeamID = teamID;
	}
	@XmlTransient
	public String getPlace() {
		return Place;
	}
	public void setPlace(String place) {
		Place = place;
	}
	@XmlTransient
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	
	
}
