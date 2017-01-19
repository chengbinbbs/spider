package com.dayspass.datacenter.bean;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sf.json.JSONObject;


/**
 * 联赛排序
 * @user zhangcb
 * @date 2016年8月26日
 */
public class LeagueSort {
	
	/**
	 * 按照order排序
	 * @author zhangcb
	 * @param list
	 */
	public static void sortByOrder(List<LeagueIndex> list)
	{
		Collections.sort(list, new Comparator<LeagueIndex>() {

			@Override
			public int compare(LeagueIndex o1, LeagueIndex o2) {
				int num = o1.getOrder().compareTo(o2.getOrder());
				if(num == 0)
				{
					return o1.getCount() > o2.getCount() ? -1:1;
				}
				return num;
			}
		});
	}
	
	/**
	 * 根据索引排序
	 * @author zhangcb
	 * @param list
	 */
	public static void sortByIndex(List<LeagueIndex> list)
	{
		Collections.sort(list, new Comparator<LeagueIndex>() {

			@Override
			public int compare(LeagueIndex o1, LeagueIndex o2) {
				int num = o1.getIndex().compareTo(o2.getIndex());
				if(num == 0)
				{
					num = o1.getOrder().compareTo(o2.getOrder());
					if(num == 0)
					{
						return o1.getCount().compareTo(o2.getCount());
//						return o1.getCount() > o2.getCount() ? -1:1;
					}
					return num;
				}
				else
				{
					return num;
				}
			}
		});
	}
	
	public static void sortLeague(List<JSONObject> list)
	{
		Collections.sort(list, new Comparator<JSONObject>() {

			@Override
			public int compare(JSONObject json1, JSONObject json2) {
				Integer order1 = json1.getInt("order");
				Integer order2 = json2.getInt("order");
				int num = order1.compareTo(order2);
				if(num == 0)
				{
					Integer count1 = json1.getInt("count");
					Integer count2 = json2.getInt("count");
					return count1.compareTo(count2);
//					return count1 > count2 ? -1:1;
				}
				return num;
			}
		});
	}
}
