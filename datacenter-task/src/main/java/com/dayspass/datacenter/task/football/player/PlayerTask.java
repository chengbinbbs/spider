package com.dayspass.datacenter.task.football.player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.ZqPlayerBody;
import com.dayspass.datacenter.bean.ZqPlayerParam;
import com.dayspass.datacenter.domain.ZqPlayer;
import com.dayspass.datacenter.domain.ZqPlayerInTeam;
import com.dayspass.datacenter.domain.ZqTeam;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.service.football.player.ZqPlayerInTeamService;
import com.dayspass.datacenter.service.football.player.ZqPlayerService;
import com.dayspass.datacenter.service.football.team.ZqTeamService;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;
import com.google.common.collect.Maps;


/**
 * 球员信息抓取
 * @user zhangcb
 * @date 2016年1月29日
 */
@Component("playerTask")
public class PlayerTask {
	
	private static Logger logger = LoggerFactory.getLogger(PlayerTask.class);
	@Autowired
	private ZqPlayerService playerService;
	@Autowired
	private ZqTeamService teamService;
	@Autowired
	private ZqPlayerInTeamService playerInTeamService;
	
	
	@Value("${zq.playerinfoUrl}")
	private String playerUrl;	//球员数据
	
	/**
	 * 抓取一天的球员信息
	 * @author zhangcb
	 */
	public void spiderPlayer(){
		try
		{
			Map<String,String> params = Maps.newHashMap();
			params.put("day", String.valueOf(1));
			String reqXml = HttpClientUtil.callHttpPost_Map(playerUrl,"utf-8", params);
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient->"+playerUrl+"<-url source player data is empty.");
				return; 
			}
			
			ZqPlayerBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqPlayerBody.class);
			if(reqBody == null || reqBody.getPlayer()== null || reqBody.getPlayer().size() == 0) {
				logger.info("httpclient-> No ongoing player data.");
				return;
			}
			
			updatePlayerInfo(reqBody);
		}
		catch(Exception e)
		{
			logger.info("获取一天内球员信息接口异常PlayerTask，异常:{}",e.getMessage());
		}
	}
	/**
	 * 抓取某个球队的球员数据
	 * @author zhangcb
	 * @param teamid
	 */
	public void spiderTeamPlayer(int teamid){
		try
		{
			Map<String,String> params = Maps.newHashMap();
			params.put("teamID", String.valueOf(teamid));
			String reqXml = HttpClientUtil.callHttpPost_Map(playerUrl,"utf-8", params);
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient->"+playerUrl+"<-url source player data is empty.");
				return; 
			}
			
			ZqPlayerBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqPlayerBody.class);
			if(reqBody == null || reqBody.getPlayer()== null || reqBody.getPlayer().size() == 0) {
				logger.info("httpclient-> No ongoing player data.");
				return;
			}
			
			updatePlayerInfo(reqBody);
		}
		catch(Exception e)
		{
			logger.info("获取球队球员信息接口异常PlayerTask，teamid:" + teamid +"",e.getMessage());
		}
	}

	/**
	 * 更新球员信息
	 * @author zhangcb
	 * @param reqBody
	 */
	private void updatePlayerInfo(ZqPlayerBody reqBody) {
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(ZqPlayerParam param:reqBody.getPlayer())
			{
				//1.更新球员信息
				int playerid = param.getPlayerID();
				ZqPlayer player = playerService.selectByPrimaryKey(playerid);
				if(player != null)
				{
					player.setNameJ(param.getName_J());
					player.setNameF(param.getName_F());
					player.setNameE(param.getName_E());
					if(!StringUtils.isBlank(param.getBirthday()))
					{
						player.setBirthday(sdf.parse(param.getBirthday().trim().replace("/", "-")));
					}
					
					player.setTallness(param.getTallness());
					player.setWeight(param.getWeight());
					if(!StringUtils.isBlank(param.getCountry()))
					{
						player.setCountry(param.getCountry().trim());
					}
					player.setHealth(param.getHealth());
					player.setExpectedvalue(param.getValue());
					player.setIntroduce(param.getIntroduce());
					player.setModifytime(new Date());
					playerService.updateByPrimaryKeySelective(player);
				}
				else
				{
					player = new ZqPlayer();
					player.setPlayerid(playerid);
					player.setKind((short) 1);
					player.setNameJ(param.getName_J());
					player.setNameF(param.getName_F());
					player.setNameE(param.getName_E());
					if(!StringUtils.isBlank(param.getBirthday()))
					{
						player.setBirthday(sdf.parse(param.getBirthday().trim().replace("/", "-")));
					}
					player.setTallness(param.getTallness());
					player.setWeight(param.getWeight());
					if(!StringUtils.isBlank(param.getCountry()))
					{
						player.setCountry(param.getCountry().trim());
					}
					player.setPhoto(param.getPhoto());
					player.setIntroduce(param.getIntroduce());
					player.setHealth(param.getHealth());
					player.setExpectedvalue(param.getValue());
					player.setModifytime(new Date());
					playerService.insertSelective(player);
				}
				//2.更新球员球队信息
				int teamid = param.getTeamID();
				ZqTeam team = teamService.selectByPrimaryKey(teamid);
				
				Map<String,Object> ptMap = Maps.newHashMap();
				ptMap.put("playerid", playerid);
				ptMap.put("teamid", teamid);
				List<ZqPlayerInTeam> list = playerInTeamService.queryAll(ptMap);
				if(list != null && list.size() > 0)
				{
					ZqPlayerInTeam pt = list.get(0);
					pt.setPlayername(param.getName_J());
					pt.setTeamname(team.getNameJ());
					pt.setPlace(param.getPlace());
					pt.setNumber(param.getNumber());
					pt.setModifytime(new Date());
					playerInTeamService.updateByPrimaryKeySelective(pt);
				}
				else
				{
					ZqPlayerInTeam pt = new ZqPlayerInTeam();
					pt.setPlayerid(playerid);
					pt.setPlayername(param.getName_J());
					pt.setTeamid(teamid);
					pt.setTeamname(team.getNameJ());
					pt.setPlace(param.getPlace());
					pt.setNumber(param.getNumber());
					pt.setModifytime(new Date());
					playerInTeamService.insertSelective(pt);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("获取球员信息定时任务PlayerTask异常，异常:{}",e.getMessage());
		}
	}
}
