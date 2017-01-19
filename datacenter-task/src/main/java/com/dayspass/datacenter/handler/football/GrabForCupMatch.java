package com.dayspass.datacenter.handler.football;


import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import sun.org.mozilla.javascript.internal.NativeArray;
import sun.org.mozilla.javascript.internal.NativeObject;

import com.dayspass.datacenter.grab.util.Grab;
import com.google.common.collect.Maps;


/**
 * 抓取杯赛积分榜
 * @user zhangcb
 * @date 2016年3月31日
 */
@Component("grabForCupMatch")
public class GrabForCupMatch extends Grab<Map<String,String>, Integer> {

	@SuppressWarnings("restriction")
	public Map<String,String> parse(String content, Integer k) throws Exception {
		ScriptEngine engine = null;
		try {
			if(StringUtils.isBlank(content)){
				return null;
			}
			engine = new ScriptEngineManager().getEngineByName("javascript");
			engine.eval("var jh = {};" + content);
		} catch (ScriptException e) {
			return null;
		}
		//比赛阶段
		NativeArray cupKind = (NativeArray) engine.get("arrCupKind");
		int cid = -1;
		for(int i = 0; i<cupKind.size();i++)
		{
			NativeArray array= (NativeArray) cupKind.get(i,cupKind);
			String group = (String) array.get(2,array);
			if("分组赛".equals(group))
			{
				cid = parseDouble(array.get(0,array)).intValue();
				break;
			}
		}
		//积分数据
		if(cid < 0)
		{
			return null;
		}
		
		//球队数据
		NativeArray arrTeam = (NativeArray) engine.get("arrTeam");
		Map<Integer,String> param = Maps.newHashMap();
		if(arrTeam != null && arrTeam.size() > 0)
		{
			for(int i = 0; i<arrTeam.size(); i++)
			{
				NativeArray teamArray= (NativeArray) arrTeam.get(i,arrTeam);
				for(int j = 0; j<teamArray.size(); j++)
				{
					Integer teamid = parseDouble(teamArray.get(0,teamArray)).intValue();
					String teamname = teamArray.get(1,teamArray).toString();
					param.put(teamid, teamname);
				}
			}
		}
		
		NativeObject jh = (NativeObject) engine.get("jh");
		Map<String,String> map = Maps.newHashMap();
		for(char i = 'A'; i < 'I' ; i++)
		{
			String key = "S" + cid + i;
			NativeArray scoreArray = (NativeArray) jh.get(key);
			StringBuilder sb = new StringBuilder();
			if(scoreArray != null && scoreArray.size() > 0)
			{
				for(int j = 0; j<scoreArray.size(); j++)
				{
					NativeArray score= (NativeArray) scoreArray.get(j,scoreArray);
					StringBuilder ss = new StringBuilder();
					for(int s = 0; s < score.size(); s++)
					{
						ss.append(parseDouble(score.get(s,score)).intValue()).append("^");
						if(s == 1)
						{
							Integer teamid = parseDouble(score.get(1,score)).intValue();
							ss.append(param.get(teamid) == null?"":param.get(teamid)).append("^");
						}
					}
					ss.substring(0, ss.length() - 1);
					sb.append(ss.substring(0, ss.length() - 1)).append("|");
				}
				String value = sb.substring(0, sb.length() - 1);
				map.put(String.valueOf(i), value);
			}
		}
		return map;
	}
}
