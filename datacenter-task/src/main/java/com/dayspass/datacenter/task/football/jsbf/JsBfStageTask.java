package com.dayspass.datacenter.task.football.jsbf;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.date.util.DateUtils;
import com.dayspass.datacenter.domain.ZqStage;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.dayspass.datacenter.service.football.match.ZqStageService;
import com.google.common.collect.Maps;




/**
 * 生成即时比分期次文件
 * @user zhangcb
 * @date 2016年4月20日
 */
@Component("jsBfStageTask")
public class JsBfStageTask {

private static Logger logger = LoggerFactory.getLogger(JsBfStageTask.class);
	
	@Autowired
	private ZqStageService stageService;
	
	@Value("${zq.jsbf.stagepath}")
	private String position;	
	@Value("${ZQ_BD_STAGE_LIST}")
	private String ZQ_BD_STAGE_LIST;
	@Value("${ZQ_ZC_STAGE_LIST}")
	private String ZQ_ZC_STAGE_LIST;
	@Autowired
	private JedisClient jedisClient;
	
	/**
	 * 完整赛事期次列表
	 */
	public void spiderAllStage(){
		try
		{
			DateTime today = new DateTime();
			String current = today.toString("yyyyMMdd");
			if(Integer.parseInt(today.toString("HHmm")) < 1130) {
				current = today.minusDays(1).toString("yyyyMMdd");
			}
			JSONArray stagearray = new JSONArray();
			for(int i = 3; i > 0; i--)
			{
				JSONObject json = new JSONObject();
				DateTime datetime = today.minusDays(i);
				String name = datetime.toString("yyyyMMdd");
				json.put("name", name);
				
				String week = DateUtils.getWeekOfDate(datetime.toDate());
				String time = datetime.toString("MM-dd");
				String date = "(" + week + ") " + time;
				json.put("week", week);
				json.put("time", time);
				json.put("date", date);
				if(current.equals(name))
				{
					json.put("iscurrent", 1);
				}
				else
				{
					json.put("iscurrent", 0);
				}
				stagearray.add(json);
			}
			for(int i = 0; i < 4; i++)
			{
				JSONObject json = new JSONObject();
				DateTime datetime = today.plusDays(i);
				String name = datetime.toString("yyyyMMdd");
				json.put("name", name);
				String week = DateUtils.getWeekOfDate(datetime.toDate());
				String time = datetime.toString("MM-dd");
				String date = "(" + week + ") " + time;
				json.put("week", week);
				json.put("time", time);
				json.put("date", date);
				if(current.equals(name))
				{
					json.put("iscurrent", 1);
				}
				else
				{
					json.put("iscurrent", 0);
				}
				stagearray.add(json);
			}
			FileUtils.write(new File(position + "all.json"), stagearray.toString(),"UTF-8"); 
		}
		catch(Exception e)
		{
			logger.info("生成完整赛事期次文件异常，异常信息:{}", e.getMessage());
		}
	}
	
	/**
	 * 生成竞彩、完整赛事期次文件
	 */
	public void spiderJcStage(){
		try
		{
			DateTime today = new DateTime();
			String current = today.toString("yyyyMMdd");
			if(Integer.parseInt(today.toString("HHmm")) < 1130) {
				current = today.minusDays(1).toString("yyyyMMdd");
			}
			JSONArray stagearray = new JSONArray();
			for(int i = 3; i > 0; i--)
			{
				JSONObject json = new JSONObject();
				DateTime datetime = today.minusDays(i);
				String name = datetime.toString("yyyyMMdd");
				json.put("name", name);
				
				String week = DateUtils.getWeekOfDate(datetime.toDate());
				String time = datetime.toString("MM-dd");
				String date = "(" + week + ") " + time;
				json.put("week", week);
				json.put("time", time);
				json.put("date", date);
				if(current.equals(name))
				{
					json.put("iscurrent", 1);
				}
				else
				{
					json.put("iscurrent", 0);
				}
				stagearray.add(json);
			}
			for(int i = 0; i < 4; i++)
			{
				JSONObject json = new JSONObject();
				DateTime datetime = today.plusDays(i);
				String name = datetime.toString("yyyyMMdd");
				json.put("name", name);
				String week = DateUtils.getWeekOfDate(datetime.toDate());
				String time = datetime.toString("MM-dd");
				String date = "(" + week + ") " + time;
				json.put("week", week);
				json.put("time", time);
				json.put("date", date);
				if(current.equals(name))
				{
					json.put("iscurrent", 1);
				}
				else
				{
					json.put("iscurrent", 0);
				}
				stagearray.add(json);
			}
			FileUtils.write(new File(position + "jc.json"), stagearray.toString(),"UTF-8"); 
		}
		catch(Exception e)
		{
			logger.info("生成竞彩赛事期次文件异常，异常信息:{}", e.getMessage());
		}
	}
	
	/**
	 * 生成北单赛事期次文件
	 */
	@SuppressWarnings("unchecked")
	public void spiderBdStage(){
		try
		{
			//获取期次列表
			List<ZqStage> stagelist = null;
			if(jedisClient.existsObject(ZQ_BD_STAGE_LIST))
			{
				stagelist = (List<ZqStage>) jedisClient.getObject(ZQ_BD_STAGE_LIST);
			}
			else
			{
				Map<String, Object> param = Maps.newHashMap();
				param.put("type", 2);
				param.put("limit", 3);
				stagelist = stageService.queryBdStageList(param);
				if(stagelist != null)
				{
					jedisClient.setObject(ZQ_BD_STAGE_LIST, stagelist, 30*60);
				}
			}
			JSONArray stagearray = new JSONArray();
			if(!StringUtils.isEmpty(stagelist))
			{
				for(ZqStage stage:stagelist)
				{
					JSONObject json = new JSONObject();
					json.put("name", stage.getName());
					if(stage.getIsCurrent())
					{
						json.put("iscurrent", 1);
					}
					else
					{
						json.put("iscurrent", 0);
					}
					stagearray.add(json);
				}
			}
			FileUtils.write(new File(position + "bd.json"), stagearray.toString(),"UTF-8"); 
		}
		catch(Exception e)
		{
			logger.info("生成北单赛事期次文件异常，异常信息:{}", e.getMessage());
		}
	}
	
	/**
	 * 生成足彩赛事期次文件
	 */
	@SuppressWarnings("unchecked")
	public void spideZcStage(){
		try
		{
			//获取期次列表
			List<ZqStage> stagelist = null;
			if(jedisClient.existsObject(ZQ_ZC_STAGE_LIST))
			{
				stagelist = (List<ZqStage>) jedisClient.getObject(ZQ_ZC_STAGE_LIST);
			}
			else
			{
				Map<String, Object> param = Maps.newHashMap();
				param.put("type", 3);
				param.put("limit", 5);
				stagelist = stageService.queryZcStageList(param);
				if(!StringUtils.isEmpty(stagelist))
				{
					jedisClient.setObject(ZQ_ZC_STAGE_LIST, stagelist, 30*60);
				}
			}
			JSONArray stagearray = new JSONArray();
			if(!StringUtils.isEmpty(stagelist))
			{
				for(ZqStage stage:stagelist)
				{
					JSONObject json = new JSONObject();
					json.put("name", stage.getName());
					if(stage.getIsCurrent())
					{
						json.put("iscurrent", 1);
					}
					else
					{
						json.put("iscurrent", 0);
					}
					stagearray.add(json);
				}
			}
			FileUtils.write(new File(position + "zc.json"), stagearray.toString(),"UTF-8"); 
		}
		catch(Exception e)
		{
			logger.info("生成足彩赛事期次文件异常，异常信息:{}", e.getMessage());
		}
	}
}
