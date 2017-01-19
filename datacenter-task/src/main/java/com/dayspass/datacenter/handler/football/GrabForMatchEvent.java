package com.dayspass.datacenter.handler.football;

import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang.StringUtils;
import sun.org.mozilla.javascript.internal.NativeArray;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.grab.util.Grab;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 抓取比赛事件
 * @user zhangcb
 * @date 2016年1月22日
 */
@Component("grabForMatchEvent")
public class GrabForMatchEvent extends Grab<Map<Integer, List<Map<String,Object>>>, String> {

	public Map<Integer, List<Map<String,Object>>> parse(String content, String k) throws Exception {
		ScriptEngine engine = null;
		try {
			if(StringUtils.isBlank(content)){
				return null;
			}
			engine = new ScriptEngineManager().getEngineByName("javascript");
			engine.eval(content);
		} catch (ScriptException e) {
			e.printStackTrace();
			return null;
		}
		
		//进球、红黄牌事件
		NativeArray rq = (NativeArray)  engine.get("rq");
		Map<Integer, List<Map<String,Object>>> map = Maps.newHashMap();
		for(Object id:rq.getIds()){
			String obj=(String) rq.get((Integer)id, rq);
			String[] array = obj.split("\\^");
			//一共7项，依次为：赛程id、主客队标志 1：主队，0：客队事件，事件类型  1、入球 2、红牌  3、黄牌  4、入 5、出 6、无效 7、点球  8、乌龙  9、两黄变红 11、换人
			//球员名字，球员id，球员简称(三者可能为空)
			Map<String,Object> result = Maps.newHashMap();
			int sid = Integer.parseInt(array[0]);
			List<Map<String,Object>> list = null;
			if(map.containsKey(sid))
			{
				list = map.get(sid);
			}
			else
			{
				list = Lists.newArrayList();
			}
			result.put("scheduleid",sid);
			result.put("type",Integer.parseInt(array[1]));  //主客队标志，1：主队，0：客队事件
			Short kind = Short.parseShort(array[2]);
			result.put("kind", kind);
			result.put("happentime",Short.parseShort(array[3]));
			
			if(array.length == 7)
			{
				result.put("playername", array[4]);
				result.put("playerid",StringUtils.isBlank(array[5])?null:array[5]);
				result.put("playernameJ", array[6]);
			}
			else if(array.length == 6)
			{
				result.put("playername", array[4]);
				result.put("playerid",StringUtils.isBlank(array[5])?null:Integer.parseInt(array[5]));
			}
			else if(array.length == 5)
			{
				result.put("playername", array[4]);
			}
			//换人事件, 1、入球 2、红牌  3、黄牌  4、入 5、出 6、无效 7、点球  8、乌龙  9、两黄变红 11、换人
			if(kind == 11)
			{
				String[] name = result.get("playername").toString().replace("↓", "").split("↑");
				String[] ids = array[5].split(",");
				String[] nameJ = result.get("playernameJ").toString().replace("↓", "").split("↑");
				for(short j = 0;j < name.length;j++)
				{
					Map<String,Object> newresult = Maps.newHashMap();
					newresult.put("scheduleid",sid);
					newresult.put("type", result.get("type"));  //主客队标志，1：主队，0：客队事件
					newresult.put("happentime", result.get("happentime"));
					
					newresult.put("playername", name[j]);
					newresult.put("playerid",ids[j]);
					newresult.put("playernameJ", nameJ[j]);
					newresult.put("kind", (short) (j+(short)4));
					list.add(newresult);
				}
			}
			else
			{
				list.add(result);
			}
			map.put(sid, list);
		}
		return map;
	}
}
