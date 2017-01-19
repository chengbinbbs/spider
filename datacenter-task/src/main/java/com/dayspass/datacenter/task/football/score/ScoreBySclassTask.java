package com.dayspass.datacenter.task.football.score;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.domain.ZqSclass;
import com.dayspass.datacenter.service.football.league.ZqSclassService;
import com.google.common.collect.Maps;

/**
 * 抓取整个联赛的积分榜
 * @user zhangcb
 * @date 2016年5月17日
 */
@Component("scoreBySclassTask")
public class ScoreBySclassTask {
	private static Logger logger = LoggerFactory.getLogger(ScoreBySclassTask.class);
	@Autowired
	private ZqSclassService sclassService;
	@Autowired
	private ScoreTask scoreTask;
	
	public void spiderScore(){
		try
		{
			Map<String,Object> params = Maps.newHashMap();
			List<ZqSclass> sclasslist = sclassService.queryAll(params);
			if(sclasslist != null)
			{
				for(ZqSclass sclass:sclasslist)
				{
					//联赛积分抓取
					scoreTask.spiderForSclassScore(sclass);
					logger.info("联赛"+sclass.getNameJs()+",sclassid:"+sclass.getLeagueId()+"积分抓取完毕");
				}
			}
		}
		catch(Exception e)
		{
			logger.info("联赛积分定时任务抓取ScoreInitTask异常，异常：{}",e.getMessage());
		}
	}
}
