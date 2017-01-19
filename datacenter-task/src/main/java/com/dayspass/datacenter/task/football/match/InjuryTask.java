package com.dayspass.datacenter.task.football.match;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.ZqInjuryBody;
import com.dayspass.datacenter.bean.ZqInjuryParam;
import com.dayspass.datacenter.domain.ZqInjure;
import com.dayspass.datacenter.domain.ZqPlayerInTeam;
import com.dayspass.datacenter.domain.ZqSchedule;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.service.football.event.ZqInjureService;
import com.dayspass.datacenter.service.football.match.ZqScheduleService;
import com.dayspass.datacenter.service.football.player.ZqPlayerInTeamService;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;
import com.google.common.collect.Maps;

/**
 * 伤停、预测、赛前简报
 * @author zcb
 * @date 2016-01-29
 */
@Component("injuryTask")
public class InjuryTask {

	private static Logger logger = LoggerFactory.getLogger(InjuryTask.class);
	@Autowired
	private ZqInjureService injureService;
	@Autowired
	private ZqScheduleService scheduleService;
	@Autowired
	private ZqPlayerInTeamService playerInTeamService;
	
	@Value("${zq.injureUrl}")
	private String injuryUrl;
	
	/**
	 * 根据比赛id抓取该场比赛的伤停
	 * @param sche
	 */
	public void spiderInjuryBySid(ZqSchedule sche){
		try
		{
			int scheduleid = sche.getSid().intValue();
			String reqXml = HttpClientUtil.callHttpGet(injuryUrl + "?id=" + scheduleid,"utf-8");
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+injuryUrl+"<-url source injure data is empty.");
				return; 
			}
			
			ZqInjuryBody injuryBody = BeanXmlUtil.fromXml(reqXml, ZqInjuryBody.class);
			if(injuryBody == null || injuryBody.getInjury()== null || injuryBody.getInjury().size() == 0) {
				return;
			}
			for(ZqInjuryParam param:injuryBody.getInjury())
			{
				//查询比赛的伤停情况
				String homeStop = getInjurePlayer(param.getHomeStop(), sche.getHometeamid());
				String guestStop = getInjurePlayer(param.getAwayStop(), sche.getGuestteamid());
				String homeInjure = getInjurePlayer(param.getHomeInjure(), sche.getHometeamid());
				String guestInjure = getInjurePlayer(param.getAwayInjure(), sche.getGuestteamid());
				
				ZqInjure injure = injureService.selectByScheduleID(scheduleid);
				if(injure != null)
				{
					if(injure.getIsStatis())
					{
						continue;		//后台更新过，不需要再抓取更新
					}
					injure.setBrief(param.getBriefing());
					injure.setRecommend(param.getRecommend());
					injure.setHomeStop(homeStop);
					injure.setGuestStop(guestStop);
					injure.setHomeInjure(homeInjure);
					injure.setGuestInjure(guestInjure);
					injure.setUpdateTime(new Date());
					injureService.updateByPrimaryKeySelective(injure);
				}
				else
				{
					injure = new ZqInjure();
					injure.setScheduleid(scheduleid);
					injure.setBrief(param.getBriefing());
					injure.setRecommend(param.getRecommend());
					injure.setHomeStop(homeStop);
					injure.setGuestStop(guestStop);
					injure.setHomeInjure(homeInjure);
					injure.setGuestInjure(guestInjure);
					injure.setCreateTime(new Date());
					injure.setIsStatis(false);
					injureService.insertSelective(injure);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("获取某场比赛伤停情况定时任务InjuryTask异常，sid:"+sche.getSid()+",异常:{}",e.getMessage());
		}
	}
	/**
	 * 抓取24小时以内的伤停数据
	 * @author zhangcb
	 */
	public void spiderInjury(){
		try
		{
			String reqXml = HttpClientUtil.callHttpGet(injuryUrl,"utf-8");
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+injuryUrl+"<-url source injure data is empty.");
				return; 
			}
			
			ZqInjuryBody injuryBody = BeanXmlUtil.fromXml(reqXml, ZqInjuryBody.class);
			if(injuryBody == null || injuryBody.getInjury()== null || injuryBody.getInjury().size() == 0) {
				logger.info("httpclient-> No ongoing football game.");
				return;
			}
			for(ZqInjuryParam param:injuryBody.getInjury())
			{
				//查询比赛的伤停情况
				int sid = param.getMatchId();
				ZqSchedule schedule = scheduleService.selectBySid(sid);
				if(schedule == null)
				{
					continue;
				}
				String homeStop = getInjurePlayer(param.getHomeStop(), schedule.getHometeamid());
				String guestStop = getInjurePlayer(param.getAwayStop(), schedule.getGuestteamid());
				String homeInjure = getInjurePlayer(param.getHomeInjure(), schedule.getHometeamid());
				String guestInjure = getInjurePlayer(param.getAwayInjure(), schedule.getGuestteamid());
				
				ZqInjure injure = injureService.selectByScheduleID(sid);
				if(injure != null)
				{
					if(injure.getIsStatis())
					{
						continue;		//后台更新过，不需要再抓取更新
					}
					injure.setBrief(param.getBriefing());
					injure.setRecommend(param.getRecommend());
					injure.setHomeStop(homeStop);
					injure.setGuestStop(guestStop);
					injure.setHomeInjure(homeInjure);
					injure.setGuestInjure(guestInjure);
					injure.setUpdateTime(new Date());
					injureService.updateByPrimaryKeySelective(injure);
				}
				else
				{
					injure = new ZqInjure();
					injure.setScheduleid(sid);
					injure.setBrief(param.getBriefing());
					injure.setRecommend(param.getRecommend());
					injure.setHomeStop(homeStop);
					injure.setGuestStop(guestStop);
					injure.setHomeInjure(homeInjure);
					injure.setGuestInjure(guestInjure);
					injure.setCreateTime(new Date());
					injure.setIsStatis(false);
					injureService.insertSelective(injure);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("获取24小时比赛伤停情况定时任务InjuryTask异常，异常:{}",e.getMessage());
		}
	}
	
	/**
	 * 获取伤停球员
	 * @author zhangcb
	 * @param homeStop
	 * @return
	 */
	private String getInjurePlayer(String injure, int teamid) {
		if(!StringUtils.isBlank(injure))
		{
			String[] injures = injure.split("\n");
			if(injures.length > 0)
			{
				Map<String,Object> param= Maps.newHashMap();
				param.put("teamid", teamid);
				StringBuffer sid = new StringBuffer();
				for(int i = 0;i<injures.length;i++)
				{
					String[] obj = injures[i].split(" ");
					param.put("number", obj[0].split("\\(")[0]);
					List<ZqPlayerInTeam> list = playerInTeamService.queryAll(param);
					if(list != null && list.size() > 0)
					{
						sid.append("id=" + list.get(0).getPlayerid()).append(",");
					}
				}
				return sid.length() > 0?sid.substring(0, sid.length()-1):null;
			}
			return null;
		}
		return null;
	}


}
