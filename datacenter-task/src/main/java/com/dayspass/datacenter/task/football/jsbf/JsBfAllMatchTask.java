package com.dayspass.datacenter.task.football.jsbf;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.command.ZqJsBfCommand;
import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.json.util.JsonDateValueProcessor;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.dayspass.datacenter.service.football.match.ZqScheduleService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**
 * 即时比分完整赛事
 * @user zhangcb
 * @date 2016年4月20日
 */
@Component("jsBfAllMatchTask")
public class JsBfAllMatchTask {

private static Logger logger = LoggerFactory.getLogger(JsBfAllMatchTask.class);
	
	@Autowired
	private ZqScheduleService scheduleService;
	
	@Value("${zq.jsbf.allmatchpath}")
	private String position;	
	//当天比赛对阵缓存的key
	@Value("${ZQ_ALL_MATCH_LIST}")
	private String ZQ_ALL_MATCH_LIST;
	//当天联赛缓存的key
	@Value("${ZQ_ALL_LEAGUE_LIST}")
	private String ZQ_ALL_LEAGUE_LIST;

	@Autowired
	private JedisClient jedisClient;

	private final static JsonConfig jsonConfig = new JsonConfig();
	static{
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 完整赛事当前期
	 */
	@SuppressWarnings("unchecked")
	public void spiderAllCurrentMatch(){
		try
		{
			List<ZqJsBfCommand> matchList = Lists.newArrayList();
			
			DateTime today = new DateTime();
			if(Integer.parseInt(today.toString("HHmm")) < 1130) {
				today = today.minusDays(1);
			}
			String qc = today.toString("yyyyMMdd");
			String key = ZQ_ALL_MATCH_LIST + qc;
			if(jedisClient.existsObject(key))
			{
				matchList = (List<ZqJsBfCommand>) jedisClient.getObject(key);
			}
			else
			{
				Map<String, String> param = Maps.newHashMap();
				String begin = today.toString("yyyy-MM-dd 11:30:00");
				String end = today.plusDays(1).toString("yyyy-MM-dd 11:30:00");
				param.put("begin", begin);
				param.put("end", end);
				matchList = scheduleService.queryMatchListBetween(param);
				if (!StringUtils.isEmpty(matchList)) 
				{
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
					//更新比赛状态
					scheduleService.changeMatch(match);
					//对阵按照进行中的、未开赛、已结束的顺序排序
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
				SortMatch.sortByTime(live);
				SortMatch.sortByTime(nostart);
				SortMatch.sortByTime(over);
				matchList.clear();
				matchList.addAll(live);
				matchList.addAll(nostart);
				matchList.addAll(over);
			}
			//赛事筛选
			String leaguekey = ZQ_ALL_LEAGUE_LIST + qc;
			JSONObject leagueindex = scheduleService.getLeagueIndex(leaguekey,matchList);
			JSONObject json = new JSONObject();
			json.put("matchs", JSONArray.fromObject(matchList, jsonConfig));
			json.put("index", leagueindex.get("index"));
			json.put("hot", leagueindex.get("hot"));
			FileUtils.write(new File(position+ qc + ".json"), json.toString(),"UTF-8"); 
		}
		catch(Exception e)
		{
			logger.info("生成完整赛事当前期对阵文件异常，异常信息:{}", e.getMessage());
		}
	}
	
	/**
	 * 完整赛事未来期
	 */
	@SuppressWarnings("unchecked")
	public void spiderAllFutureMatch(){
		try
		{
			List<ZqJsBfCommand> matchList = Lists.newArrayList();
			
			DateTime today = new DateTime();
			if(Integer.parseInt(today.toString("HHmm")) < 1130) {
				today = today.minusDays(1);
			}
			for(int i = 1; i < 5; i++)
			{
				String qc = today.plusDays(i).toString("yyyyMMdd");
				String key = ZQ_ALL_MATCH_LIST + qc;
				
				if(jedisClient.existsObject(key))
				{
					matchList = (List<ZqJsBfCommand>) jedisClient.getObject(key);
				}
				else
				{
					Map<String, String> param = Maps.newHashMap();
					String begin = today.plusDays(i).toString("yyyy-MM-dd 11:30:00");
					String end = today.plusDays(i + 1).toString("yyyy-MM-dd 11:30:00");
					param.put("begin", begin);
					param.put("end", end);
					matchList = scheduleService.queryMatchListBetween(param);
					if (!StringUtils.isEmpty(matchList)) 
					{
						jedisClient.setObject(key, matchList, 60*60);
					}

				}
				//赛事筛选
				String leaguekey = ZQ_ALL_LEAGUE_LIST + qc;
				JSONObject leagueindex = scheduleService.getLeagueIndex(leaguekey,matchList);
				JSONObject json = new JSONObject();
				json.put("matchs", JSONArray.fromObject(matchList, jsonConfig));
				json.put("index", leagueindex.get("index"));
				json.put("hot", leagueindex.get("hot"));

				FileUtils.write(new File(position+ qc + ".json"), json.toString(),"UTF-8"); 
			}
		}
		catch(Exception e)
		{
			logger.info("生成完整赛事当前期对阵文件异常，异常信息:{}", e.getMessage());
		}
	}
	/**
	 * 完整赛事历史期
	 */
	public void spiderAllHistoryMatch(){
		try
		{
			DateTime today = new DateTime();
			if(Integer.parseInt(today.toString("HHmm")) < 1130) {
				today = today.minusDays(1);
			}
			
			for(int i = 0; i < 3; i++)
			{
				String qc = today.minusDays(i + 1).toString("yyyyMMdd");
				Map<String, String> param = Maps.newHashMap();
				String begin = today.minusDays(i + 1).toString("yyyy-MM-dd 11:30:00");
				String end = today.minusDays(i).toString("yyyy-MM-dd 11:30:00");
				param.put("begin", begin);
				param.put("end", end);
				List<ZqJsBfCommand> matchList = scheduleService.queryMatchListBetween(param);
				if (!StringUtils.isEmpty(matchList)) 
				{
					
					if(i == 0)
					{
						for(ZqJsBfCommand match:matchList)
						{
							//更新比赛状态
							scheduleService.changeMatch(match);
						}
					}
				}
				//赛事筛选
				String leaguekey = ZQ_ALL_LEAGUE_LIST + qc;
				JSONObject leagueindex = scheduleService.getLeagueIndex(leaguekey,matchList);
				JSONObject json = new JSONObject();
				json.put("matchs", JSONArray.fromObject(matchList, jsonConfig));
				json.put("index", leagueindex.get("index"));
				json.put("hot", leagueindex.get("hot"));

				FileUtils.write(new File(position+ qc + ".json"), json.toString(),"UTF-8"); 
			}
		}
		catch(Exception e)
		{
			logger.info("生成完整赛事历史期对阵文件异常，异常信息:{}", e.getMessage());
		}
	}
}
