package com.dayspass.datacenter.common.util;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * 字符串常规工具
 * @author kouyi
 */
public class StringUtils {
	
	public static boolean isEmpty(Object value) {
		return (value == null || "".equals(value));
	}
	
	public static boolean isEmpty(Object[] values) {
		return (values == null || values.length == 0);
	}

	public static boolean isNotEmpty(Object value) {
		return (!isEmpty(value));
	}
	
	public static boolean isEmpty(byte[] value) {
		return (value == null || value.length == 0);
	}
	
	public static boolean isEmpty(Collection<?> value) {
		return (value == null || value.size() == 0);
	}
	
	public static boolean isEmpty(Map<?,?> value) {
		return (value == null || value.size() == 0);
	}
	
	public static boolean isEmpty(String[] value) {
		return (value == null || value.length == 0);
	}
	
	/**
	 * 获取随机数
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.toUpperCase().replace("-", "");
	}
	
	/**
	 * 根据Unicode编码判断中文汉字和符号
	 * @param c
	 * @return
	 */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
 
    /**
     * 判断中文汉字和符号
     * @param strName
     * @return
     */
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
		System.out.println(isChinese(""));
	}
}
