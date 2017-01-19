package com.dayspass.datacenter.interf.bean;

import java.io.Serializable;

import com.dayspass.datacenter.commons.QueryBean;
import com.dayspass.datacenter.domain.Page;
import com.dayspass.datacenter.error.code.ErrorCode;
import com.dayspass.datacenter.interf.ecode.ErrorCode_Interface;


/**
 * 接口返回标准bean
 * @user kouyi
 * @date 2016-10-25
 */
public class ResultBean extends ErrorCode_Interface implements Serializable {
	private static final long serialVersionUID = 1L;
	private int code = ErrorCode.SUCCESS; //返回状态吗
	private String msg;
	private Object data; //结果数据
	private Page page; //分页参数
	
	public ResultBean() {
		
	}
	
	public ResultBean(int code) {
		this.code = code;
		this.msg = msg(code);
	}
	
	public ResultBean(int code, Object data) {
		super();
		this.code = code;
		this.data = data;
		this.msg = msg(code);
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		this.msg = msg(code);
	}

	public String msg(int code) {
		return getCodeMsg(code);
	}
	
	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public void page(QueryBean bean) {
		Page pg = new Page();
		pg.setCount(bean.getCount());
		pg.setLimnow(bean.getLimnow());
		pg.setLimnum(bean.getLimnum());
		pg.setLimstart(bean.getLimstart());
		this.page = pg;
	}
}
