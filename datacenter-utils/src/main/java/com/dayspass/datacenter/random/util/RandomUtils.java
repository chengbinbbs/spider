package com.dayspass.datacenter.random.util;

public class RandomUtils {

	/**
	 * 生成6位随机数
	 * 
	 * @return
	 */
	public static String createCode() {
		return (int)((Math.random()*9+1)*100000) + "";
	}
	
	public static void main(String[] args) {
		System.out.println(createCode());
	}
}
