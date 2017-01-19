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

import com.dayspass.datacenter.bean.ZqLineUpBody;
import com.dayspass.datacenter.bean.ZqLineUpParam;
import com.dayspass.datacenter.domain.ZqLineup;
import com.dayspass.datacenter.domain.ZqPlayerInTeam;
import com.dayspass.datacenter.domain.ZqSchedule;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.service.football.event.ZqLineupService;
import com.dayspass.datacenter.service.football.match.ZqScheduleService;
import com.dayspass.datacenter.service.football.player.ZqPlayerInTeamService;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;
import com.google.common.collect.Maps;

/**
 * 出场阵容
 * @user zhangcb
 * @date 2016年1月29日
 */
@Component("lineUpTask")
public class LineUpTask {

private static Logger logger = LoggerFactory.getLogger(LineUpTask.class);
	
	@Value("${zq.lineupUrl}")
	private String lineupUrl;	//出场阵容
	
	@Autowired
	private ZqLineupService lineupService;
	@Autowired
	private ZqScheduleService scheduleService;
	@Autowired
	private ZqPlayerInTeamService playerInTeamService;
	
	/**
	 * 抓取某场比赛的首发阵容
	 * @author zhangcb
	 */
	public void spiderLineUpBySid(ZqSchedule schedule){
		try
		{
			int scheduleid = schedule.getSid().intValue();
			String reqXml = HttpClientUtil.callHttpGet(lineupUrl + "?id=" + scheduleid,"utf-8");
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+lineupUrl+"<-url source lineup data is empty.");
				return; 
			}
			
			ZqLineUpBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqLineUpBody.class);
			if(reqBody == null || reqBody.getMatch()== null || reqBody.getMatch().size() == 0) {
				return;
			}
			for(ZqLineUpParam param:reqBody.getMatch())
			{
				String homeLineup = getLineup(param.getHomeLineup_cn(), schedule.getHometeamid());
				String guestLineup = getLineup(param.getAwayLineup_cn(), schedule.getGuestteamid());
				String homeBackup = getLineup(param.getHomeBackup_cn(), schedule.getHometeamid());
				String guestBackup = getLineup(param.getAwayBackup_cn(), schedule.getGuestteamid());
				
				ZqLineup lineup = lineupService.selectByScheduleID(scheduleid);
				if(lineup != null)
				{
					lineup.setHomeForm(param.getHomeArray());
					lineup.setGuestForm(param.getAwayArray());
					lineup.setHomeLineup(homeLineup);
					lineup.setGuestLineup(guestLineup);
					lineup.setHomeBackup(homeBackup);
					lineup.setGuestBackup(guestBackup);
					lineup.setUpdateTime(new Date());
					lineupService.updateByPrimaryKeySelective(lineup);
				}
				else
				{
					lineup = new ZqLineup();
					lineup.setScheduleid(scheduleid);
					lineup.setHomeForm(param.getHomeArray());
					lineup.setGuestForm(param.getAwayArray());
					lineup.setHomeLineup(homeLineup);
					lineup.setGuestLineup(guestLineup);
					lineup.setHomeBackup(homeBackup);
					lineup.setGuestBackup(guestBackup);
					lineup.setCreateTime(new Date());
					lineupService.insertSelective(lineup);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("抓取"+schedule.getSid()+"比赛的首发阵容接口异常,异常信息:{}",e.getMessage());
		}
	}
	
	/**
	 * 抓取24小时以内的比赛首发阵容
	 * @author zhangcb
	 */
	public void spiderLineUp(){
		try
		{
			String reqXml = HttpClientUtil.callHttpGet(lineupUrl,"utf-8");
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+lineupUrl+"<-url source lineup data is empty.");
				return; 
			}
			
			ZqLineUpBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqLineUpBody.class);
			if(reqBody == null || reqBody.getMatch()== null || reqBody.getMatch().size() == 0) {
				logger.info("httpclient-> No ongoing football game.");
				return;
			}
			for(ZqLineUpParam param:reqBody.getMatch())
			{
				int sid = param.getID();
				ZqSchedule schedule = scheduleService.selectBySid(sid);
				if(schedule == null)
				{
					continue;
				}
				String homeLineup = getLineup(param.getHomeLineup_cn(), schedule.getHometeamid());
				String guestLineup = getLineup(param.getAwayLineup_cn(), schedule.getGuestteamid());
				String homeBackup = getLineup(param.getHomeBackup_cn(), schedule.getHometeamid());
				String guestBackup = getLineup(param.getAwayBackup_cn(), schedule.getGuestteamid());
				
				ZqLineup lineup = lineupService.selectByScheduleID(sid);
				if(lineup != null)
				{
					lineup.setHomeForm(param.getHomeArray());
					lineup.setGuestForm(param.getAwayArray());
					lineup.setHomeLineup(homeLineup);
					lineup.setGuestLineup(guestLineup);
					lineup.setHomeBackup(homeBackup);
					lineup.setGuestBackup(guestBackup);
					lineup.setUpdateTime(new Date());
					lineupService.updateByPrimaryKeySelective(lineup);
				}
				else
				{
					lineup = new ZqLineup();
					lineup.setScheduleid(sid);
					lineup.setHomeForm(param.getHomeArray());
					lineup.setGuestForm(param.getAwayArray());
					lineup.setHomeLineup(homeLineup);
					lineup.setGuestLineup(guestLineup);
					lineup.setHomeBackup(homeBackup);
					lineup.setGuestBackup(guestBackup);
					lineup.setCreateTime(new Date());
					lineupService.insertSelective(lineup);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("获取24小时以内的出场阵容接口异常,异常信息:{}",e.getMessage());
		}
	}
	
	/**
	 * 获取首发阵容球员
	 * @author zhangcb
	 * @param lineup
	 * @return
	 */
	private String getLineup(String lineup, int teamid) {
		if(!StringUtils.isBlank(lineup))
		{
			String[] lineups = lineup.split("\n");
			if(lineups.length > 0)
			{
				Map<String,Object> param= Maps.newHashMap();
				param.put("teamid", teamid);
				
				StringBuffer sid = new StringBuffer();
				for(int i = 0;i<lineups.length;i++)
				{
					String[] obj = lineups[i].split(" ");
					param.put("number", obj[0].split("\\(")[0]);
					List<ZqPlayerInTeam> list = playerInTeamService.queryAll(param);
					if(list != null && list.size() > 0)
					{
						sid.append("id=" + list.get(0).getPlayerid()).append(",");
					}
				}
				return sid.length() < 1?null:sid.substring(0, sid.length()-1);
			}
			return null;
		}
		return null;
	}
}
