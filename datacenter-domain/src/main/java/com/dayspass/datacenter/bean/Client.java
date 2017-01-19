package com.dayspass.datacenter.bean;

import java.io.Serializable;

public class Client implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5564289735954779613L;

	private String name;
	
	private String address;
	
	private String mobile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
