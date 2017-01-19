package com.dayspass.datacenter.interf.ecode;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.dayspass.datacenter.error.code.ErrorCode;

public class ErrorCode_Interface extends ErrorCode implements Serializable {
	private static final long serialVersionUID = 3867285300059808138L;
	// ===================接口返回成功状态码定义===================
	
	// ===================多模块共用状态码定义===================
	public static final int ERR_1900 = 1900;
	public static final String ERR_1900_MSG = "请确认你已登录并具备操作权限";
	public static final int ERR_1901 = 1901;
	public static final String ERR_1901_MSG = "请先登录";

	// ===================用户模块接口返回失败状态码定义===================
	public static final int ERR_2000 = 2000;
	public static final String ERR_2000_MSG = "昵称不正确";
	
	// ===================xx模块接口返回失败状态码定义===================
	
	// ===================xx模块接口返回失败状态码定义===================
	
	/**
	 * 获取异常码对应描述
	 */
	@Override
	public String getCodeMsg(int code) {
		try {
			Field[] fs = this.getClass().getFields();
			for (Field f : fs) {
				if (f.get(f.getName()).toString().equals(String.valueOf(code))) {
					return this.getClass().getField(f.getName() + "_MSG").get(f.getName() + "_MSG").toString();
				}
			}
		} catch (Exception e) {
			System.out.println("获取异常码描述异常");
		}
		return "找不到异常码 " + code;
	}
	
}
