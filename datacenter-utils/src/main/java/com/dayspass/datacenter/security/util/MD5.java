package com.dayspass.datacenter.security.util;

import java.security.MessageDigest;

/**
 * 对象与xml文件转换工具类
 * @author kouyi
 * @version 1.0 2015-11-04
 */
public class MD5 {
	
	/**
	 * 统一使用UTF-8编码
	 * @param source	
	 * 			加密前的字符串
	 * @return 经过MD5加密后的16进制值
	 * 
	 */
	public static String md5(String source) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			byte[] byteArray = messageDigest.digest(source.getBytes("UTF-8"));
			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
					md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
				} else {
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
				}
			}
			return md5StrBuff.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static void main(String[] args) {
		System.out.println(md5("123456"));
	}
}
