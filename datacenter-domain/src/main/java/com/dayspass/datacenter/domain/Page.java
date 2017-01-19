package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class Page implements Serializable {
	private static final long serialVersionUID = 1L;
	private int limstart = 0; //开始位置
	private int limnum = 15; //页大小
	private int count = 0; //总页数
	private int limnow = 1; //当前页
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getLimnow() {
		return limnow;
	}

	public void setLimnow(int limnow) {
		this.limnow = limnow;
		this.limstart = (limnow-1)*limnum;
	}

	public int getLimstart() {
		return limstart;
	}
	
	public void setLimstart(int limstart) {
		this.limstart = limstart;
	}
	public int getLimnum() {
		return limnum;
	}
	public void setLimnum(int limnum) {
		this.limnum = limnum;
		this.limstart = (limnow-1)*limnum;
	}
	
}
