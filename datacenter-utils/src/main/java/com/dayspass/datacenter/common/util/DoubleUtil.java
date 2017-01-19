package com.dayspass.datacenter.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DoubleUtil {
	
	/**
	 * double类型不四舍五入取prec位小数
	 * @param value	
	 * 		double值
	 * @param prec
	 * 		小数位数
	 * @return
	 */
	public static double roundNoDouble(double value, int prec) {
		double ret = 0.0;
		try {
			double factor = Math.pow(10, prec);
			ret = Math.floor(value * factor) / factor;
		} catch (Exception e) {
			System.out.println("double不四舍五入" + prec + "位小数异常");
		}
		return ret;
	}
	
	/**
	 * double类型四舍五入取prec位小数
	 * @param value	
	 * 		double值
	 * @param prec
	 * 		小数位数
	 * @return
	 */
	public static double roundDouble(double value, int prec) {
		BigDecimal bg = new BigDecimal(value);  
        double ret = bg.setScale(prec, BigDecimal.ROUND_HALF_UP).doubleValue();  
		return ret;
	}
	
	/**
	 * double类型四舍五入取prec位小数
	 * @param value	
	 * 		double值
	 * @param prec
	 * 		小数位数
	 * @return
	 */
	public static double roundDouble(String value, int prec) {
		BigDecimal bg = new BigDecimal(value);  
        double ret = bg.setScale(prec, BigDecimal.ROUND_HALF_UP).doubleValue();  
		return ret;
	}
	
	public static String formatOdds(double d){
		return String.format("%.2f", d);
	}
	
	/**
	 * double类型四舍五入取prec位小数, 格式化为百分比
	 * @param value	
	 * 		double值
	 * @param prec
	 * 		格式化后的小数位数
	 * @return
	 */
	public static String roundDoublePercent(double value, int prec) {
		BigDecimal bg = new BigDecimal(value * 100);  
        double ret = bg.setScale(prec, BigDecimal.ROUND_HALF_UP).doubleValue();  
		return ret+"%";
	}
	
	/**
	 * 保留两位小数-不足的补0
	 * @param value
	 * @return
	 */
	public static String roundDouble(double value) {
		DecimalFormat format = new DecimalFormat("##.00");
	    return format.format(value);
	}
}
