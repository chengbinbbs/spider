package com.dayspass.datacenter.task.football.jsbf;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.domain.ZqDetailResult;
import com.dayspass.datacenter.domain.ZqSchedule;
import com.dayspass.datacenter.domain.ZqTechnicStatist;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.dayspass.datacenter.service.football.event.ZqDetailResultService;
import com.dayspass.datacenter.service.football.event.ZqTechnicStatistService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;



/**
 * 即时比分直播(比赛事件、技术统计)
 * @user zhangcb
 * @date 2016年5月3日
 */
@Component("liveMatchTask")
public class LiveMatchTask {

	private static Logger logger = LoggerFactory.getLogger(LiveMatchTask.class);
	
	@Autowired
	private ZqDetailResultService detailResultService;
	@Autowired
	private ZqTechnicStatistService technicStatistService;
	
	@Value("${zq.jsbf.livepath}")
	private String position;	
	@Autowired
	private JedisClient jedisClient;
	
	public void spiderLiveMatch(ZqSchedule sche)
	{
		try
		{
			JSONObject result = new JSONObject();
			result.put("sid", sche.getSid());
			showMatchEvent(sche, result);
			showMatchStatis(sche, result);
//			showMatchLineup(sche, result);
			FileUtils.write(new File(position + "live_" +sche.getSid() + ".json"), result.toString(),"UTF-8"); 
		}
		catch(Exception e)
		{
			logger.info("生成即时比分直播文件异常，sid:"+sche.getSid()+",异常信息:{}", e.getMessage());
		}
	}
	
	/**
	 * 比赛事件
	 * @author zhangcb
	 * @param params
	 * @param request
	 * @param response
	 */
	public void showMatchEvent(ZqSchedule sche, JSONObject result) {
		try
		{
			//1.比赛事件
			Map<String,Object> param = Maps.newHashMap();
			param.put("scheduleid", sche.getSid());
			List<ZqDetailResult> list = detailResultService.queryAll(param);
			if(list != null && list.size() > 0)
			{
				List<JSONObject> event = Lists.newArrayList();
				int hometeamid = sche.getHometeamid().intValue();
				Map<String,ZqDetailResult> up = Maps.newHashMap();
				Map<String,ZqDetailResult> down = Maps.newHashMap();
				for(ZqDetailResult detailresult:list)
				{
					JSONObject json = new JSONObject();
					int teamid = detailresult.getTeamid();
					int kind = detailresult.getKind();
					int time = detailresult.getHappentime();
					String key = teamid + "-" + time;
					//换人事件
					if(kind == 4)
					{
						up.put(key, detailresult);
						continue;
					}
					if(kind == 5)
					{
						down.put(key, detailresult);
						continue;
					}
					json.put("type", kind);
					json.put("time", time);
					json.put("teamid", teamid);
					if(hometeamid == teamid)
					{
						json.put("ishome", 1);
					}
					else
					{
						json.put("ishome", 0);
					}
					String playername = detailresult.getPlayernameJ();
					if(StringUtils.isEmpty(playername))
					{
						json.put("name", playername);
					}
					else
					{
						int length = playername.length() > 8 ? 8 : playername.length();
						if(kind == 1 && playername.indexOf("助攻") != -1)
						{
							String[] names = playername.split("\\(");
							if(names.length > 1)
							{
								json.put("type", 10);
								String name0 = names[0];
								String name1 = names[1].replace("助攻:", "").replace(")", "");
								String name = name0.substring(0, name0.length() > 8?8:name0.length()) + "," + name1.substring(0, name1.length() > 8?8:name1.length());
								json.put("name", name);
							}
							else
							{
								json.put("name", playername.substring(0, length));
							}
						}
						else
						{
							json.put("name", playername.substring(0, length));
						}
					}
					event.add(json);
				}
				for(Map.Entry<String, ZqDetailResult> entry:up.entrySet())
				{
					String key = entry.getKey();
					ZqDetailResult detailresult = entry.getValue();
					if(!down.containsKey(key))
					{
						continue;
					}
					String name0 = detailresult.getPlayernameJ();
					String name1 = down.get(key).getPlayernameJ();
					String name = name0.substring(0, name0.length() > 8?8:name0.length()) + "," +name1.substring(0, name1.length() > 8?8:name1.length());
					JSONObject json = new JSONObject();
					int teamid = detailresult.getTeamid().intValue();
					json.put("type", 11);
					json.put("name", name);
					json.put("time", detailresult.getHappentime());
					json.put("teamid", teamid);
					if(hometeamid == teamid)
					{
						json.put("ishome", 1);
					}
					else
					{
						json.put("ishome", 0);
					}
					event.add(json);
				}
				Collections.sort(event, new Comparator<JSONObject>() {

					@Override
					public int compare(JSONObject o1, JSONObject o2) {
						int time1 = o1.getIntValue("time");
						int time2 = o2.getIntValue("time");
						if(time1 > time2)
						{
							return 1;
						}
						else if(time1 == time2)
						{
							return 0;
						}
						else
						{
							return -1;
						}
					}
					
				});
				result.put("event", event);
			}
		}
		catch(Exception e)
		{
			logger.info("获取比赛事件异常，sid:"+sche.getSid()+",异常信息:{}", e.getMessage());
		}
	}
	
	/**
	 * 获取比赛的技术统计
	 * @author zhangcb
	 * @param sid
	 * @param result
	 */
	public void showMatchStatis(ZqSchedule sche, JSONObject result) {
		try
		{
			//1.技术统计
			ZqTechnicStatist technicStatist = technicStatistService.selectByScheduleId(sche.getSid());
			if(technicStatist != null && !StringUtils.isEmpty(technicStatist.getTechniccount()))
			{
				String[] techStr = technicStatist.getTechniccount().split(";");
				JSONObject shotsNum = null;
				JSONObject shotsOn = null;
				JSONObject foul = null;
				JSONObject cornerkick = null;
				JSONObject offside = null;
				JSONObject yellowCard = null;
				JSONObject redCard = null;
				JSONObject possessionTime = null;
				JSONObject saves = null;
				JSONObject shotBlock = null;
				for(int i = 0; i<techStr.length; i++)
				{
					String[] obj = techStr[i].split(",");
					int type = Integer.parseInt(obj[0]);
					switch (type) {
					case 3:   //射门次数
						shotsNum = new JSONObject();
						shotsNum.put("teamA", obj[1]);
						shotsNum.put("teamB", obj[2]);
						shotsNum.put("name", "射门次数");
						break;
					case 4:  //射正次数
						shotsOn = new JSONObject();
						shotsOn.put("teamA", obj[1]);
						shotsOn.put("teamB", obj[2]);
						shotsOn.put("name", "射正次数");
						break;
					case 5:  //犯规次数
						foul = new JSONObject();
						foul.put("teamA", obj[1]);
						foul.put("teamB", obj[2]);
						foul.put("name", "犯规次数");
						break;
					case 6:  //角球次数
						cornerkick = new JSONObject();
						cornerkick.put("teamA", obj[1]);
						cornerkick.put("teamB", obj[2]);
						cornerkick.put("name", "角球次数");
						break;
					case 9:  //越位次数
						offside = new JSONObject();
						offside.put("teamA", obj[1]);
						offside.put("teamB", obj[2]);
						offside.put("name", "越位次数");
						break;
					case 11:  //黄牌数
						yellowCard = new JSONObject();
						yellowCard.put("teamA", obj[1]);
						yellowCard.put("teamB", obj[2]);
						yellowCard.put("name", "黄牌数");
						break;
					case 13:  //红牌数
						redCard = new JSONObject();
						redCard.put("teamA", obj[1]);
						redCard.put("teamB", obj[2]);
						redCard.put("name", "红牌数");
						break;
					case 14:  //控球时间
						possessionTime = new JSONObject();
						possessionTime.put("teamA", obj[1]);
						possessionTime.put("teamB", obj[2]);
						possessionTime.put("name", "控球时间");
						break;
					case 16:  //救球数
						saves = new JSONObject();
						saves.put("teamA", obj[1]);
						saves.put("teamB", obj[2]);
						saves.put("name", "救球数");
						break;
					case 37:  //射门被挡
						shotBlock = new JSONObject();
						shotBlock.put("teamA", obj[1]);
						shotBlock.put("teamB", obj[2]);
						shotBlock.put("name", "射门被挡");
						break;
					default:
						break;
					}
				}
				JSONArray array = new JSONArray();
				if(shotsNum != null)
				{
					array.add(shotsNum);
				}
				if(shotsOn != null)
				{
					array.add(shotsOn);
				}
				if(cornerkick != null)
				{
					array.add(cornerkick);
				}
				if(foul != null)
				{
					array.add(foul);
				}
				if(yellowCard != null)
				{
					array.add(yellowCard);
				}
				if(redCard != null)
				{
					array.add(redCard);
				}
				if(offside != null)
				{
					array.add(offside);
				}
				if(saves != null)
				{
					array.add(saves);
				}
				if(shotBlock != null)
				{
					array.add(shotBlock);
				}
				if(possessionTime != null)
				{
					array.add(possessionTime);
				}
				result.put("tech", array);
			}
		}
		catch(Exception e)
		{
			logger.info("获取技术统计异常，sid:"+sche.getSid()+",异常信息:{}", e.getMessage());
		}
	}
}
