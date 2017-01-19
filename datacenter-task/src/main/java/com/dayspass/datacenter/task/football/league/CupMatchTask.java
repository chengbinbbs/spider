package com.dayspass.datacenter.task.football.league;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.domain.ZqCupMatch;
import com.dayspass.datacenter.domain.ZqCupMatchGrouping;
import com.dayspass.datacenter.domain.ZqSclass;
import com.dayspass.datacenter.handler.football.GrabForCupMatch;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.dayspass.datacenter.service.football.league.ZqCupMatchService;
import com.dayspass.datacenter.service.football.league.ZqCupmatchGroupingService;
import com.google.common.collect.Maps;


/**
 * 杯赛分组、积分抓取
 * @user zhangcb
 * @date 2016年4月6日
 */
@Component("cupMatchTask")
public class CupMatchTask {

	private static Logger logger = LoggerFactory.getLogger(CupMatchTask.class);
	@Autowired
	private GrabForCupMatch grabForCupMatch;
	@Autowired
	private ZqCupMatchService cupMatchService;
	@Autowired
	private ZqCupmatchGroupingService cupmatchGroupingService;
	@Autowired
	private JedisClient jedisClient;
	
	public void spiderForCupMatch(ZqSclass sclass){
		try
		{
			if(sclass == null)
			{
				return;
			}
			int sclassid = sclass.getLeagueId();
			String season = sclass.getCurrMatchseason();
			Map<String,Object> param = Maps.newHashMap();
			param.put("sclassid", sclassid);
			param.put("matchseason", season);
			param.put("groupname", "分组赛");
			List<ZqCupMatchGrouping> list = cupmatchGroupingService.queryAll(param);
			ZqCupMatchGrouping group = new ZqCupMatchGrouping();
			if(StringUtils.isEmpty(list))
			{
				group.setSclassid(sclassid);
				group.setMatchseason(season);
				group.setGroupname("分组赛");
				group.setGroupnameF("分組賽");
				group.setAdddatetime(new Date());
				group.setIszu(true);
				cupmatchGroupingService.insertSelective(group);
			}
			else
			{
				group = list.get(0);
			}
			String cupUrl = "http://zq.win007.com/jsData/matchResult/" + season + "/c" + sclassid + ".js";
			Map<String,String> map = grabForCupMatch.collect(cupUrl, null, "utf-8");
			
			if(!StringUtils.isEmpty(map))
			{
				for(Entry<String, String> entry:map.entrySet())
				{
					String key = entry.getKey();
					String value = entry.getValue();
					Map<String,Object> cupparam = Maps.newHashMap();
					cupparam.put("sclassid", sclassid);
					cupparam.put("grouping", key);
					cupparam.put("matchseason", season);
					cupparam.put("cupmatchType", group.getGroupid());
					List<ZqCupMatch> cuplist = cupMatchService.queryAll(cupparam);
					if(!StringUtils.isEmpty(cuplist))
					{
						ZqCupMatch cupmatch = cuplist.get(0);
						cupmatch.setStrcontent(value);
						cupMatchService.updateByPrimaryKeySelective(cupmatch);
					}
					else
					{
						ZqCupMatch cupmatch = new ZqCupMatch();
						cupmatch.setSclassid(sclassid);
						cupmatch.setCupmatchType(group.getGroupid());
						cupmatch.setGrouping(key);
						cupmatch.setStrcontent(value);
						cupmatch.setMatchseason(season);
						cupMatchService.insertSelective(cupmatch);
					}
					if(sclassid == 67)
					{
						jedisClient.delkeyObject("euro_score_list");
					}
				}
			}
		}
		catch(Exception e)
		{
			logger.error("杯赛积分抓取定时任务CupMatchTask异常，sclassid"+sclass.getLeagueId()+",异常信息:{}",e.getCause());
		}
	}
	
}
