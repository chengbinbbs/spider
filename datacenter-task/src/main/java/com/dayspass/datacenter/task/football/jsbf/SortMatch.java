package com.dayspass.datacenter.task.football.jsbf;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.dayspass.datacenter.command.ZqJsBfCommand;
import com.google.common.collect.Lists;

/**
 * 即时比分对阵排序
 * @user zhangcb
 * @date 2016年5月18日
 */
public class SortMatch {

	public static void doMatchSort(List<ZqJsBfCommand> list)
	{
		List<ZqJsBfCommand> live = Lists.newArrayList();//进行中的
		List<ZqJsBfCommand> nostart = Lists.newArrayList();  //未开赛的
		List<ZqJsBfCommand> over = Lists.newArrayList();//已结束的
		
		if(list != null && list.size() > 0)
		{
			for(ZqJsBfCommand match:list)
			{
				//比赛状态 0:未开,1:上半场,2:中场,3:下半场,4,加时，-11:待定,-12:腰斩,-13:中断,-14:推迟,-1:完场，-10取消
				int code = match.getMatchstate();
				if(code == 1 || code == 2 || code == 3 || code == 4)	//开赛中
				{
					live.add(match);
				}
				else if(code == 0)	//未开赛或延期
				{
					nostart.add(match);
				}
				else if(code < 0)	//已完场
				{
					over.add(match);
				}
			}
			sortByNumber(live);
			sortByNumber(nostart);
			sortByNumber(over);
			list.clear();
			list.addAll(live);
			list.addAll(nostart);
			list.addAll(over);
		}
	}
	/**
	 * 按照排序编号排序
	 * @param list
	 */
	public static void sortByNumber(List<ZqJsBfCommand> list)
	{
		Collections.sort(list, new Comparator<ZqJsBfCommand>() {

			public int compare(ZqJsBfCommand o1, ZqJsBfCommand o2) {
				int num1 = o1.getNumber();
				int num2 = o2.getNumber();
				if(num1 > num2)
				{
					return 1;
				}
				else if(num1 == num2)
				{
					return 0;
				}
				else
				{
					return -1;
				}
			}
		});
	}
	
	/**
	 * 按照开赛时间排序
	 * @param list
	 */
	public static void sortByTime(List<ZqJsBfCommand> list)
	{
		Collections.sort(list, new Comparator<ZqJsBfCommand>() {

			public int compare(ZqJsBfCommand o1, ZqJsBfCommand o2) {
				long date1 = o1.getMatchtime().getTime();
				long date2 = o2.getMatchtime().getTime();
				if(date1 > date2)
				{
					return 1;
				}
				else if(date1 == date2)
				{
					return 0;
				}
				else
				{
					return -1;
				}
			}
		});
	}
}
