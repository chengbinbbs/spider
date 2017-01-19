package com.dayspass.datacenter.handler.football;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import sun.org.mozilla.javascript.internal.NativeArray;
import sun.org.mozilla.javascript.internal.NativeObject;

import com.dayspass.datacenter.grab.util.Grab;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * 抓取球员技术统计
 * @user zhangcb
 * @date 2016年3月31日
 */
@Component("grabForPlayerTech")
public class GrabForPlayerTech extends Grab<List<Map<String,Object>>, Integer> {

	@SuppressWarnings("restriction")
	public List<Map<String,Object>> parse(String content, Integer k) throws Exception {
		ScriptEngine engine = null;
		try {
			if(StringUtils.isBlank(content)){
				return null;
			}
			engine = new ScriptEngineManager().getEngineByName("javascript");
			engine.eval(content);
		} catch (ScriptException e) {
			return null;
		}
		List<Map<String,Object>> list = Lists.newArrayList();
		
		NativeObject techCount = (NativeObject) engine.get("techCout_Player");
		//球员球队对应关系
		NativeObject pid = (NativeObject) techCount.get("Pid");
		Map<Integer,Integer> param = Maps.newHashMap();
		if(pid != null && pid.size() > 0)
		{
			for(Entry<Object, Object> entry:pid.entrySet())
			{
				Integer playerid = (Integer) entry.getKey();
				NativeArray play = (NativeArray) entry.getValue();
				Integer teamid = null;
				if(play != null && play.size() > 0)
				{
					teamid = parseDouble(play.get(1,play)).intValue();
				}
				param.put(playerid, teamid);
			}
		}
		
		//球员统计
		NativeObject total = (NativeObject) techCount.get("Total");
		NativeArray value = (NativeArray) total.get("value");
		
		if(value != null && value.size() > 0)
		{
			for(int i = 0; i<value.size();i++)
			{
				NativeArray array= (NativeArray) value.get(i,value);
				Map<String,Object> map = Maps.newHashMap();
				Integer playerid = parseDouble(array.get(0,array)).intValue();
				map.put("playerid", playerid);
				map.put("rounds", parseDouble(array.get(1,array)).intValue());
				map.put("backrounds", parseDouble(array.get(2,array)).intValue());
				map.put("playtime", parseDouble(array.get(3,array)).intValue());
				map.put("shotsnum", parseDouble(array.get(6,array)).intValue());
				map.put("shotson", parseDouble(array.get(7,array)).intValue());
				map.put("bestsum", parseDouble(array.get(9,array)).intValue());
				map.put("rating", parseDouble(array.get(10,array)).intValue());
				
				map.put("pass", parseDouble(array.get(12,array)).intValue());
				map.put("passsuc", parseDouble(array.get(13,array)).intValue());
				map.put("assist", parseDouble(array.get(15,array)).intValue());
				map.put("interception", parseDouble(array.get(25,array)).intValue());
				map.put("clearance", parseDouble(array.get(26,array)).intValue());
				map.put("clearancesuc", parseDouble(array.get(27,array)).intValue());
				map.put("goals", parseDouble(array.get(40,array)).intValue());
				if(param.containsKey(playerid))
				{
					map.put("teamid", param.get(playerid));
				}
				list.add(map);
			}
		}
		return list;
	}
}
