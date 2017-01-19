package com.dayspass.datacenter.bean;

import java.io.Serializable;

/**
 * 联赛索引/联赛筛选
 *
 * @user zhangcb
 * @date 2016年8月25日
 */
public class LeagueIndex implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;			//联赛id;
	
	private String name;		//联赛名称
	
	private Integer count = 0;		//比赛场数
	
	private String index;		//联赛索引
	
	private Integer order;		//排序
	
	private String tag;			//竞彩分类
	
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
}
