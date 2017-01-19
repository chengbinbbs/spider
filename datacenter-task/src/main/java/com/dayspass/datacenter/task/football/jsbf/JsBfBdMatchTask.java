package com.dayspass.datacenter.task.football.jsbf;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.command.ZqJsBfCommand;
import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.domain.ZqStage;
import com.dayspass.datacenter.json.util.JsonDateValueProcessor;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.dayspass.datacenter.service.football.match.ZqScheduleService;
import com.dayspass.datacenter.service.football.match.ZqStageService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**
 * 生成即时比分北单赛事文件
 * @user zhangcb
 * @date 2016年4月20日
 */
@Component("jsBfBdMatchTask")
public class JsBfBdMatchTask {

private static Logger logger = LoggerFactory.getLogger(JsBfBdMatchTask.class);
	
	@Autowired
	private ZqScheduleService scheduleService;
	@Autowired
	private ZqStageService stageService;
	
	@Value("${zq.jsbf.bdmatchpath}")
	private String position;
	
	//当天比赛对阵缓存的key
	@Value("${ZQ_BD_MATCH_LIST}")
	private String ZQ_BD_MATCH_LIST;

	@Autowired
	private JedisClient jedisClient;
	private final static JsonConfig jsonConfig = new JsonConfig();
	static{
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
	}
	/**
	 * 生成当前期对阵文件
	 */
	@SuppressWarnings("unchecked")
	public void spiderBdCurrentMatch(){
		try
		{
			List<ZqJsBfCommand> matchList = Lists.newArrayList();
			
			//获取期次列表
			List<ZqStage> stagelist = null;
			if(jedisClient.existsObject("bd_stage_list"))
			{
				stagelist = (List<ZqStage>) jedisClient.getObject("bd_stage_list");
			}
			else
			{
				Map<String, Object> param = Maps.newHashMap();
				param.put("type", 2);
				param.put("limit", 3);
				stagelist = stageService.queryBdStageList(param);
				if(stagelist != null)
				{
					jedisClient.setObject("bd_stage_list", stagelist, 30*60);
				}
			}
			for(ZqStage stage:stagelist)
			{
				if(stage.getIsCurrent())
				{
					String name = stage.getName();
					String key = ZQ_BD_MATCH_LIST + name;
					if(jedisClient.existsObject(key))
					{
						matchList = (List<ZqJsBfCommand>) jedisClient.getObject(key);
					}
					else
					{
						Map<String, String> param = Maps.newHashMap();
						param.put("type", "2");	//彩种类型，1：竞彩，2：北单，3：足彩
						param.put("name", name);
						matchList = scheduleService.queryMatchListByStage(param);
						if (!StringUtils.isEmpty(matchList)) 
						{
							for(ZqJsBfCommand match:matchList)
							{
								//比分反转
								scheduleService.swapTeam(match);
							}
							jedisClient.setObject(key, matchList, 60*60);
						}
					}
					if (!StringUtils.isEmpty(matchList)) 
					{
						List<ZqJsBfCommand> live = Lists.newArrayList();//进行中的
						List<ZqJsBfCommand> nostart = Lists.newArrayList();  //未开赛的
						List<ZqJsBfCommand> over = Lists.newArrayList();//已结束的
						Iterator<ZqJsBfCommand> iterator = matchList.iterator();
						while(iterator.hasNext())
						{
							ZqJsBfCommand match = iterator.next();
							if(match.getSid() == null)
							{
								iterator.remove();
								continue;
							}
							//更新比赛状态
							scheduleService.changeMatch(match);
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
						SortMatch.sortByNumber(live);
						SortMatch.sortByNumber(nostart);
						SortMatch.sortByNumber(over);
						matchList.clear();
						matchList.addAll(live);
						matchList.addAll(nostart);
						matchList.addAll(over);
					}
					JSONObject json = new JSONObject();
					json.put("matchs", JSONArray.fromObject(matchList, jsonConfig));
					json.put("index", new JSONArray());
					json.put("hot",  new JSONArray());
					FileUtils.write(new File(position+ name + ".json"), json.toString(),"UTF-8"); 
				}
			}
		}
		catch(Exception e)
		{
			logger.info("生成北单赛事当前期对阵文件异常，异常信息:{}", e.getMessage());
		}
	}
	
	/**
	 * 生成历史期对阵文件
	 */
	@SuppressWarnings("unchecked")
	public void spiderBdHistoryMatch(){
		try
		{
			//获取期次列表
			List<ZqStage> stagelist = null;
			if(jedisClient.existsObject("bd_stage_list"))
			{
				stagelist = (List<ZqStage>) jedisClient.getObject("bd_stage_list");
			}
			else
			{
				Map<String, Object> param = Maps.newHashMap();
				param.put("type", 2);
				param.put("limit", 3);
				stagelist = stageService.queryBdStageList(param);
				if(stagelist != null)
				{
					jedisClient.setObject("bd_stage_list", stagelist, 30*60);
				}
			}
			for(ZqStage stage:stagelist)
			{
				if(stage.getIsCurrent())
				{
					continue;
				}
				String name = stage.getName();
				Map<String, String> param = Maps.newHashMap();
				param.put("type", "2");	//彩种类型，1：竞彩，2：北单，3：足彩
				param.put("name", name);
				
				List<ZqJsBfCommand> matchList = scheduleService.queryMatchListByStage(param);
				if (!StringUtils.isEmpty(matchList)) 
				{
					for(ZqJsBfCommand match:matchList)
					{
						//比分反转
						scheduleService.swapTeam(match);
					}
					//对阵按照进行中的、未开赛、已结束的顺序排序
//					SortMatch.doMatchSort(matchList);
				}
				JSONObject json = new JSONObject();
				json.put("matchs", JSONArray.fromObject(matchList, jsonConfig));
				json.put("index", new JSONArray());
				json.put("hot",  new JSONArray());
				FileUtils.write(new File(position+ name + ".json"), json.toString(),"UTF-8"); 
			}
		}
		catch(Exception e)
		{
			logger.info("生成北单赛事历史期对阵文件异常，异常信息:{}", e.getMessage());
		}
	}
}
