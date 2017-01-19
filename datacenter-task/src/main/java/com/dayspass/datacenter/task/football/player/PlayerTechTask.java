package com.dayspass.datacenter.task.football.player;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.domain.ZqPlayerTech;
import com.dayspass.datacenter.handler.football.GrabForPlayerTech;
import com.dayspass.datacenter.service.football.player.ZqPlayerTechService;


/**
 * 抓取球员技术统计
 * @user zhangcb
 * @date 2016年3月31日
 */
@Component("playerTechTask")
public class PlayerTechTask {

	private static Logger logger = LoggerFactory.getLogger(PlayerTechTask.class);
	@Autowired
	private GrabForPlayerTech grabForPlayerTech;
	@Autowired
	private ZqPlayerTechService playerTechService;
	
	public void spiderForPlayerTech(String season, Integer sclassid){
		try
		{
			if(StringUtils.isEmpty(season) || sclassid == null)
			{
				return;
			}
			String playerUrl = "http://zq.win007.com/jsData/Count/" + season + "/playerTech_" + sclassid + ".js";
			List<Map<String,Object>> list = grabForPlayerTech.collect(playerUrl, null, "utf-8");
			
			if(!StringUtils.isEmpty(list))
			{
				for(Map<String,Object> param:list)
				{
					param.put("sclassid", sclassid);
					param.put("season", season);
					List<ZqPlayerTech> playertech = playerTechService.queryAll(param);
					if(!StringUtils.isEmpty(playertech))
					{
						ZqPlayerTech player = playertech.get(0);
						player.setSeason(season);
						player.setRounds(Integer.parseInt(param.get("rounds").toString()));
						player.setBackrounds(Integer.parseInt(param.get("backrounds").toString()));
						player.setPlaytime(Integer.parseInt(param.get("playtime").toString()));
						player.setGoals(Integer.parseInt(param.get("goals").toString()));
						player.setShotsnum(Integer.parseInt(param.get("shotsnum").toString()));
						player.setShotson(Integer.parseInt(param.get("shotson").toString()));
						player.setBestsum(Integer.parseInt(param.get("bestsum").toString()));
						player.setRating(new BigDecimal(param.get("rating").toString()));
						
						player.setInterception(Integer.parseInt(param.get("interception").toString()));
						player.setAssist(Integer.parseInt(param.get("assist").toString()));
						player.setPass(Integer.parseInt(param.get("pass").toString()));
						player.setPasssuc(Integer.parseInt(param.get("passsuc").toString()));
						player.setClearance(Integer.parseInt(param.get("clearance").toString()));
						player.setClearancesuc(Integer.parseInt(param.get("clearancesuc").toString()));
						player.setModifytime(new Date());
						playerTechService.updateByPrimaryKeySelective(player);
					}
					else
					{
						ZqPlayerTech player = new ZqPlayerTech();
						player.setSeason(season);
						player.setPlayerid(Integer.parseInt(param.get("playerid").toString()));
						player.setSclassid(sclassid);
						player.setTeamid(Integer.parseInt(param.get("teamid").toString()));
						player.setRounds(Integer.parseInt(param.get("rounds").toString()));
						player.setBackrounds(Integer.parseInt(param.get("backrounds").toString()));
						player.setPlaytime(Integer.parseInt(param.get("playtime").toString()));
						player.setGoals(Integer.parseInt(param.get("goals").toString()));
						player.setShotsnum(Integer.parseInt(param.get("shotsnum").toString()));
						player.setShotson(Integer.parseInt(param.get("shotson").toString()));
						player.setBestsum(Integer.parseInt(param.get("bestsum").toString()));
						player.setRating(new BigDecimal(param.get("rating").toString()));
						
						player.setInterception(Integer.parseInt(param.get("interception").toString()));
						player.setAssist(Integer.parseInt(param.get("assist").toString()));
						player.setPass(Integer.parseInt(param.get("pass").toString()));
						player.setPasssuc(Integer.parseInt(param.get("passsuc").toString()));
						player.setClearance(Integer.parseInt(param.get("clearance").toString()));
						player.setClearancesuc(Integer.parseInt(param.get("clearancesuc").toString()));
						player.setModifytime(new Date());
						playerTechService.insertSelective(player);
					}
				}
			}
		}
		catch(Exception e)
		{
			logger.error("抓取球员赛季技术统计定时任务PlayerTechTask异常，season:"+season+",sclassid："+sclassid+",异常信息:{}",e.getCause());
		}
	}
}
