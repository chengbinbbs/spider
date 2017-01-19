package com.dayspass.datacenter.properties.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dayspass.datacenter.common.util.StringUtils;


/**
 * 联赛分类
 * @author zhangcb
 * @version 1.0 2016-08-24
 *
 */
public class LeagueUtil {
	private static String fileName = "league.properties";
	private static PropertiesUtil proper = PropertiesUtil.getInstanceLeague(fileName);
	
	private static String[] allEnName = new String[]{"europeantop","europeanlow","asian","southamerica","northamerica","country","other"};
	private static String[] allCnName = new String[]{"欧洲顶级","欧洲低级","亚洲","南美洲","中北美","国家队","其它"};
	private static List<Map<String, Object>> allLeague = new LinkedList<Map<String, Object>>();

	static{
		for(int i=0;i<allEnName.length;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("enName", allEnName[i]);
			map.put("cnName", allCnName[i]);
			
			List<String> data = getLeagues(allEnName[i]);
			if(data==null)
				data = new ArrayList<String>();
			map.put("data", data);
			
			allLeague.add(map);
		}
	}
	
	/**
	 * 根据类型获取联赛
	 * @param id
	 * @return
	 */
	public static List<String> getLeagues(String name) {
		String league = proper.getStringValue(name);
		if (!StringUtils.isEmpty(league)) {
			return Arrays.asList(league.split(","));
		}
		return null;
	}
	
	/**
	 * 获取联赛的排序
	 * @param name
	 * @return
	 */
	public static Integer getLeagueOrder(String name) {
		String order = proper.getStringValue(name);
		if (!StringUtils.isEmpty(order)) {
			return Integer.parseInt(order);
		}
		return 9999;
	}
	
	/**
	 * 根据亚盘盘口排序
	 * @param name
	 * @return
	 */
	public static Integer getOddsOrder(String name) {
		String order = proper.getStringValue(name);
		if (!StringUtils.isEmpty(order)) {
			return Integer.parseInt(order);
		}
		return null;
	}
	
	public static List<Map<String, Object>> getAllLeagues(){
		return allLeague;
	}
}
