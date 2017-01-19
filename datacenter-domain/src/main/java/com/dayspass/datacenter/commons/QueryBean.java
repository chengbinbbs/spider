package com.dayspass.datacenter.commons;

import java.io.Serializable;

import com.dayspass.datacenter.domain.Page;

/**
 * 查询对象
 * @author kouyi
 */
public class QueryBean extends Page implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;//唯一键id
	private Integer code=-1; //存储过程返回状态
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}

}
