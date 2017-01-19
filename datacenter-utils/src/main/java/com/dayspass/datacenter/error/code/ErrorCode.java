package com.dayspass.datacenter.error.code;

import java.io.Serializable;

public abstract class ErrorCode implements Serializable {
	private static final long serialVersionUID = 3867285300059808138L;

	// ===================系统级提示状态码定义===================
	public static final int SUCCESS = 200;
	public static final String SUCCESS_MSG = "处理成功";
	public static final int ERROR = 201;
	public static final String ERROR_MSG = "当前网络异常,请稍后重试";
	public static final int VALID_PARAM = 202;
	public static final String VALID_PARAM_MSG = "参数异常";

	/**
	 * 根据异常码返回异常描述
	 * @param errorCode
	 * @return 
	 */
	public abstract String getCodeMsg(int code);
}
