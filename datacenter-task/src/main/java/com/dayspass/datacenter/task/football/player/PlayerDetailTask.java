package com.dayspass.datacenter.task.football.player;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.ZqPlayerDetailBody;
import com.dayspass.datacenter.bean.ZqPlayerDetailParam;
import com.dayspass.datacenter.bean.ZqScheduleBody;
import com.dayspass.datacenter.bean.ZqScheduleParam;
import com.dayspass.datacenter.domain.ZqPlayerDetail;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.service.football.player.ZqPlayerDetailService;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;
import com.google.common.collect.Maps;



/**
 * 球员技术统计
 * @user zhangcb
 * @date 2016年1月29日
 */
@Component("playerDetailTask")
public class PlayerDetailTask {

	private static Logger logger = LoggerFactory.getLogger(PlayerDetailTask.class);
	@Autowired
	private ZqPlayerDetailService playerDetailService;
	
	@Value("${zq.playerdetailUrl}")
	private String playerDetailUrl;			//球员技术统计数据
	
	public void spiderPlayerDetail(){
		try
		{
			//1.请求接口，不带参数：返回一天内有球员统计的比赛列表
			String reqXml = HttpClientUtil.callHttpGet(playerDetailUrl,"utf-8");
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+playerDetailUrl+"<-url source playerDetail schedule is empty.");
				return; 
			}
			
			ZqScheduleBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqScheduleBody.class);
			if(reqBody == null || reqBody.getMatch()== null || reqBody.getMatch().size() == 0) {
				logger.info("httpclient-> No ongoing schedule game.");
				return;
			}
			for(ZqScheduleParam param:reqBody.getMatch())
			{
				//2.获取该场比赛的球员技术统计
				httpPostPlayerDetail(param.getScheduleID());
			}
		}
		catch(Exception e)
		{
			logger.info("获取球员统计的比赛列表接口异常,异常信息:{}",e.getMessage());
		}
	}
	/**
	 * 根据比赛id获取该场比赛的球员统计
	 * @author zhangcb
	 * @param scheduleID
	 */
	private void httpPostPlayerDetail(int scheduleID) {
		try
		{
			Map<String,String> params = Maps.newHashMap();
			params.put("ID", String.valueOf(scheduleID));
			String reqXml = HttpClientUtil.callHttpPost_Map(playerDetailUrl, "utf-8",params);
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> scheduleID:"+scheduleID+","+playerDetailUrl+"<-url source playerDetail data is empty.");
				return; 
			}
			
			ZqPlayerDetailBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqPlayerDetailBody.class);
			if(reqBody == null || reqBody.getPlay()== null || reqBody.getPlay().size() == 0) {
				logger.info("httpclient-> No ongoing football game.");
				return;
			}
			for(ZqPlayerDetailParam playerParam:reqBody.getPlay())
			{
				Map<String,Object> param = Maps.newHashMap();
				param.put("playerId", playerParam.getID());
				param.put("teamId", playerParam.getTeamID());
				param.put("scheduleid", scheduleID);
				
				//球员本场详细技术统计
				List<ZqPlayerDetail> list = playerDetailService.queryAll(param);
				if(list != null && list.size() > 0)
				{
					ZqPlayerDetail playerDetail = list.get(0);
					playerDetail.setShotsNum(playerParam.getShots());
					playerDetail.setShotsOn(playerParam.getShotsTarget());
					playerDetail.setKeyPass(playerParam.getKeyPass());
					playerDetail.setPassRate(playerParam.getPassRate());
					playerDetail.setAerialWin(playerParam.getAerialWon());
					playerDetail.setTouches(playerParam.getTouches());
					playerDetail.setDribblesWin(playerParam.getDribblesWon());
					playerDetail.setWasFouled(playerParam.getWasFouled());
					playerDetail.setDispossessed(playerParam.getDispossessed());
					playerDetail.setTurnOver(playerParam.getTurnOver());
					playerDetail.setOffSides(playerParam.getOffsides());
					playerDetail.setTackles(playerParam.getTackles());
					playerDetail.setInterception(playerParam.getInterception());
					playerDetail.setClearances(playerParam.getClearances());
					playerDetail.setClearanceWin(playerParam.getClearanceWon());
					playerDetail.setShotsBlocked(playerParam.getShotsBlocked());
					playerDetail.setOffsideProvoked(playerParam.getOffsideProvoked());
					playerDetail.setFouls(playerParam.getFouls());
					playerDetail.setTotalPass(playerParam.getTotalPass());
					playerDetail.setAccuratePass(playerParam.getAccuratePass());
					playerDetail.setCrossNum(playerParam.getCrossNum());
					playerDetail.setCrossWin(playerParam.getCrossWon());
					playerDetail.setLongBall(playerParam.getLongBall());
					playerDetail.setLongballWin(playerParam.getLongBallWon());
					playerDetail.setThroughBall(playerParam.getThroughBall());
					playerDetail.setThroughballWin(playerParam.getThroughBallWon());
					playerDetail.setRating(playerParam.getRating());
					playerDetail.setUpdateTime(new Date());
					playerDetailService.updateByPrimaryKeySelective(playerDetail);
				}
				else
				{
					ZqPlayerDetail playerDetail = new ZqPlayerDetail();
					playerDetail.setPlayerId(playerParam.getID());
					playerDetail.setTeamId(playerParam.getTeamID());
					playerDetail.setScheduleid(scheduleID);
					playerDetail.setNumber(playerParam.getNumber());
					if(!StringUtils.isBlank(playerParam.getPlayerName()))
					{
						String[] playername = playerParam.getPlayerName().split(",");
						if(playername.length > 0)
						{
							playerDetail.setPlayerName(playername[0]);
						}
					}
					if(!StringUtils.isBlank(playerParam.getPositionName()))
					{
						String[] position = playerParam.getPositionName().split(",");
						if(position.length > 0)
						{
							playerDetail.setPosition(position[0]);
						}
					}
					playerDetail.setShotsNum(playerParam.getShots());
					playerDetail.setShotsOn(playerParam.getShotsTarget());
					playerDetail.setKeyPass(playerParam.getKeyPass());
					playerDetail.setPassRate(playerParam.getPassRate());
					playerDetail.setAerialWin(playerParam.getAerialWon());
					playerDetail.setTouches(playerParam.getTouches());
					playerDetail.setDribblesWin(playerParam.getDribblesWon());
					playerDetail.setWasFouled(playerParam.getWasFouled());
					playerDetail.setDispossessed(playerParam.getDispossessed());
					playerDetail.setTurnOver(playerParam.getTurnOver());
					playerDetail.setOffSides(playerParam.getOffsides());
					playerDetail.setTackles(playerParam.getTackles());
					playerDetail.setInterception(playerParam.getInterception());
					playerDetail.setClearances(playerParam.getClearances());
					playerDetail.setClearanceWin(playerParam.getClearanceWon());
					playerDetail.setShotsBlocked(playerParam.getShotsBlocked());
					playerDetail.setOffsideProvoked(playerParam.getOffsideProvoked());
					playerDetail.setFouls(playerParam.getFouls());
					playerDetail.setTotalPass(playerParam.getTotalPass());
					playerDetail.setAccuratePass(playerParam.getAccuratePass());
					playerDetail.setCrossNum(playerParam.getCrossNum());
					playerDetail.setCrossWin(playerParam.getCrossWon());
					playerDetail.setLongBall(playerParam.getLongBall());
					playerDetail.setLongballWin(playerParam.getLongBallWon());
					playerDetail.setThroughBall(playerParam.getThroughBall());
					playerDetail.setThroughballWin(playerParam.getThroughBallWon());
					playerDetail.setRating(playerParam.getRating());
					playerDetail.setCreateTime(new Date());
					playerDetailService.insertSelective(playerDetail);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("获取球员技术统计接口异常，scheduleID:"+scheduleID+",异常信息:{}",e.getMessage());
		}
	}
}
