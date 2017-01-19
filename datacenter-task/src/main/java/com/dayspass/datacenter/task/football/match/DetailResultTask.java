package com.dayspass.datacenter.task.football.match;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.domain.ZqDetailResult;
import com.dayspass.datacenter.domain.ZqSchedule;
import com.dayspass.datacenter.handler.football.GrabForMatchEvent;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.dayspass.datacenter.service.football.event.ZqDetailResultService;
import com.dayspass.datacenter.service.football.match.ZqScheduleService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * 比赛事件（入球、红黄牌）
 * @user zhangcb
 * @date 2016年1月25日
 */
@Component("detailResultTask")
public class DetailResultTask {
private static Logger logger = LoggerFactory.getLogger(DetailResultTask.class);
	
	@Autowired
	private ZqDetailResultService detailResultService;
	@Autowired
	private ZqScheduleService scheduleService;
	@Autowired
	private GrabForMatchEvent grabForMatchEvent;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${zq.matcheventUrl}")
	private String techUrl;		//当天完整统计数统计
	
	public void spiderTechAndEvent()
	{
		try
		{
			Map<Integer, List<Map<String,Object>>> map = grabForMatchEvent.collect(techUrl, null, "utf-8");
			if(!StringUtils.isEmpty(map))
			{
				for(Map.Entry<Integer, List<Map<String,Object>>> entry:map.entrySet())
				{
					Integer sid = entry.getKey();
					List<Map<String,Object>> list = entry.getValue();
					List<Integer> ids = Lists.newArrayList();
					for (Map<String,Object> result:list)
					{
						int scheduleid = (int) result.get("scheduleid"); //比赛id
						int type = (int) result.get("type"); //主客队标志，1：主队，0：客队事件
						String key = "schedule_" + scheduleid;
						ZqSchedule schedule = null;
						if(jedisClient.existsObject(key))
						{
							schedule = (ZqSchedule) jedisClient.getObject(key);
						}
						else
						{
							schedule = scheduleService.selectBySid(scheduleid);
							if(schedule == null)
							{
								continue;
							}
							jedisClient.setObject(key, schedule, 24 * 60 * 60);
						}
						//完场的比赛不再重复统计
						if(schedule.getMatchstate() == -1)
						{
							continue;
						}
						
						int teamid = type == 1?schedule.getHometeamid():schedule.getGuestteamid(); //根据比赛id获取球队id
						Short happentime = (Short) result.get("happentime");
						//事件类型  1、入球 2、红牌  3、黄牌  4、入 5、出 6、无效 7、点球  8、乌龙  9、两黄变红
						Short kind = (Short) result.get("kind"); 
						
						Map<String,Object> param = Maps.newHashMap();
						param.put("scheduleid", scheduleid);
						param.put("happentime", happentime);
						param.put("teamid", teamid);
						param.put("kind", kind);
						List<ZqDetailResult> resultList = detailResultService.queryAll(param);
						if(resultList != null && resultList.size() > 0)
						{
							ZqDetailResult detail = resultList.get(0);
							detail.setHappentime(happentime);
							detail.setKind(kind);
							detail.setTeamid(teamid);
							detail.setScheduleid(scheduleid);
							if(result.get("playerid") != null)
							{
								detail.setPlayerid(Integer.parseInt(result.get("playerid").toString()));
							}
							if(result.get("playername") != null)
							{
								detail.setPlayername(result.get("playername").toString());
							}
							if(result.get("playernameJ") != null)
							{
								detail.setPlayernameJ(result.get("playernameJ").toString());
							}
							detail.setModifytime(new Date());
							detailResultService.updateByPrimaryKeySelective(detail);
							ids.add(detail.getId());
						}
						else
						{
							ZqDetailResult detail = new ZqDetailResult();
							detail.setHappentime(happentime);
							detail.setKind(kind);
							detail.setTeamid(teamid);
							detail.setScheduleid(scheduleid);
							if(result.get("playerid") != null)
							{
								detail.setPlayerid(Integer.parseInt(result.get("playerid").toString()));
							}
							if(result.get("playername") != null)
							{
								detail.setPlayername(result.get("playername").toString());
							}
							if(result.get("playernameJ") != null)
							{
								detail.setPlayernameJ(result.get("playernameJ").toString());
							}
							detail.setModifytime(new Date());
							detailResultService.insertSelective(detail);
							ids.add(detail.getId());
						}
					}
					if(!StringUtils.isEmpty(ids))
					{
						detailResultService.deleteDetailResult(sid,ids);
					}
				}
			}
		}
		catch(Exception e)
		{
			logger.info("抓取比赛事件定时任务DetailResultTask异常，异常信息:{}",e.getMessage());
		}
	}
	
}
