package com.dayspass.datacenter.grab.util;

/**
 * 抓取内容过滤
 * @author kouyi
 */
public class GrabFilter {
	public boolean check(GrabResult result){
		if(isStatusValid(result)){//200-成功
			return true;
		}
		return false;
	}
	
	private boolean isStatusValid(GrabResult result){
		if(result.getStatusCode() != 200){
			return false;
		}
		return true;
	}
}
