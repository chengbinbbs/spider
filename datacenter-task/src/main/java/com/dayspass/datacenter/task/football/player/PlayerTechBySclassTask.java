package com.dayspass.datacenter.task.football.player;

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
 * 抓取球员赛季技术统计
 * @user zhangcb
 * @date 2016年5月16日
 */
@Component("playerTechBySclassTask")
public class PlayerTechBySclassTask {
	private static Logger logger = LoggerFactory.getLogger(PlayerTechBySclassTask.class);
	@Autowired
	private ZqSclassService sclassService;
	@Autowired
	private PlayerTechTask playerTechTask;
	
	public void spiderPlayer(){
		try
		{
			Map<String,Object> params = Maps.newHashMap();
			List<ZqSclass> sclasslist = sclassService.queryAll(params);
			if(sclasslist != null)
			{
				for(ZqSclass sclass:sclasslist)
				{
					playerTechTask.spiderForPlayerTech(sclass.getCurrMatchseason(), sclass.getLeagueId());
					logger.info("联赛"+sclass.getNameJs()+",sclassid:"+sclass.getLeagueId()+"球员技术统计抓取完毕");
				}
			}
		}
		catch(Exception e)
		{
			logger.info("球员技术统计抓取PlayerInitTask异常，异常：{}",e.getMessage());
		}
	}
}
