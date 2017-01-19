package com.dayspass.datacenter.task.football.init;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.MatchBody;
import com.dayspass.datacenter.bean.MatchParam;
import com.dayspass.datacenter.domain.ZqSchedule;
import com.dayspass.datacenter.domain.ZqSclass;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.service.football.league.ZqSclassService;
import com.dayspass.datacenter.service.football.match.ZqScheduleService;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;



/**
 * 赛程赛果
 * @user zhangcb
 * @date 2016年1月28日
 */
@Component("scheduleMatchInitTask")
public class ScheduleMatchInitTask {

	private static Logger logger = LoggerFactory.getLogger(ScheduleMatchInitTask.class);
	
	@Autowired
	private ZqScheduleService scheduleService;
	@Autowired
	private ZqSclassService sclassService;
	
	
	public void spiderSchedule()
	{
		try
		{
			String begin = new DateTime("2017-01-08").toString("yyyy-MM-dd HH:mm:ss");
			String end = new DateTime("2017-01-09").toString("yyyy-MM-dd 10:00:00");

			List<ZqSchedule> list = scheduleService.querySchedulesBetween(begin, end);
			
			if (list != null && list.size() > 0)
			{
				for(ZqSchedule sche:list)
				{
//					String url = "http://test.gunqiu.com/win007/zq/BF_XMLByID.aspx?id=" + sche.getScheduleid();
					String url = "http://interface.win007.com/zq/BF_XMLByID.aspx?id=" + sche.getSid();
					String reqXml = HttpClientUtil.callHttpGet(url,"utf-8");
					if(StringUtils.isBlank(reqXml)) {
						logger.info("httpclient-> "+url+"<-url source match data is empty.");
						continue;
					}
					reqXml = reqXml.replace(">True<", ">true<").replace(">False<", ">false<");
					MatchBody reqBody = BeanXmlUtil.fromXml(reqXml, MatchBody.class);
					if(reqBody == null || reqBody.getMatch()== null || reqBody.getMatch().size() == 0) {
						logger.info("httpclient-> No ongoing football game.");
						continue;
					}
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					for(MatchParam param:reqBody.getMatch())
					{
						int sid = param.getId();
						ZqSchedule sch = scheduleService.selectBySid(sid);
						if(sch != null)
						{
							scheduleService.getPanResult(sid, param.getHomeScore().intValue(), param.getGuestScore().intValue(), sch);
							//库里面完场的赛事不做处理
							if(sch.getMatchstate() == -1)
							{
								continue;
							}
							else if(param.getState() == -1)
							{
								
							}
							sch.setMatchtime(sdf.parse(param.getMtime().trim().replace("/", "-")));
							sch.setMatchstate(param.getState());
							//联赛id
							String[] leagueArray = param.getLeague().split(",");
							int type = 1;	//1.联赛，2：杯赛
							if(leagueArray.length > 3)
							{
								int sclassid = Integer.parseInt(leagueArray[3]);
								sch.setSclassid(sclassid);
								ZqSclass sc = sclassService.selectByLeagueId(sclassid);
								if(sc != null){
									type = sc.getKind();
								}
							}
							//主队名称
							String[] homeTeam = param.getHomeName().split(",");
							if(homeTeam.length > 3)
							{
								sch.setHometeam(homeTeam[0]);
								sch.setHometeamid(Integer.parseInt(homeTeam[3]));
							}
							//客队名称
							String[] guestTeam = param.getGuestName().split(",");
							if(guestTeam.length > 3)
							{
								sch.setGuestteam(guestTeam[0]);
								sch.setGuestteamid(Integer.parseInt(guestTeam[3]));
							}
							sch.setHomescore(param.getHomeScore());
							sch.setGuestscore(param.getGuestScore());
							sch.setHomehalfscore(param.getHomeHalfScore());
							sch.setGuesthalfscore(param.getGuestHalfScore());
							sch.setHomeRed(param.getHomeRed());
							sch.setGuestRed(param.getGuestRed());
							sch.setHomeOrder(param.getHomeOrder().trim());
							sch.setGuestOrder(param.getGuestOrder().trim());
							if(!StringUtils.isBlank(param.getRound())){
								if(type == 1)	//联赛，分轮次
								{
									sch.setRound(Short.parseShort(param.getRound()));
								}
								else			//杯赛，分组
								{
									sch.setGrouping(param.getRound());
									sch.setGrouping2(param.getGroup());
								}
							}
							sch.setLocation(param.getLocation());
							sch.setWeather(param.getWeather());
							sch.setTemperature(param.getTemperature());
							sch.setMatchseason(param.getSeason());
							sch.setNeutrality(param.isNeutrality());
							sch.setExplain(param.getExplain());
							sch.setSid(sid);
							scheduleService.updateByPrimaryKeySelective(sch);
						}
						else
						{
							sch = new ZqSchedule();
							sch.setSid(sid);
							//联赛id
							String[] leagueArray = param.getLeague().split(",");
							int type = 1;
							if(leagueArray.length > 3)
							{
								int sclassid = Integer.parseInt(leagueArray[3]);
								sch.setSclassid(sclassid);
								ZqSclass sc = sclassService.selectByLeagueId(sclassid);
								if(sc != null){
									type = sc.getKind();
								}
							}
							sch.setMatchtime(sdf.parse(param.getMtime().trim().replace("/", "-")));
							sch.setMatchstate(param.getState());
							//主队名称
							String[] homeTeam = param.getHomeName().split(",");
							if(homeTeam.length > 3)
							{
								sch.setHometeam(homeTeam[0]);
								sch.setHometeamid(Integer.parseInt(homeTeam[3]));
							}
							//客队名称
							String[] guestTeam = param.getGuestName().split(",");
							if(guestTeam.length > 3)
							{
								sch.setGuestteam(guestTeam[0]);
								sch.setGuestteamid(Integer.parseInt(guestTeam[3]));
							}
							sch.setHomescore(param.getHomeScore());
							sch.setGuestscore(param.getGuestScore());
							sch.setHomehalfscore(param.getHomeHalfScore());
							sch.setGuesthalfscore(param.getGuestHalfScore());
							sch.setHomeRed(param.getHomeRed());
							sch.setGuestRed(param.getGuestRed());
							sch.setHomeOrder(param.getHomeOrder().trim());
							sch.setGuestOrder(param.getGuestOrder().trim());
							if(!StringUtils.isBlank(param.getRound())){
								if(type == 1)	//联赛，分轮次
								{
									sch.setRound(Short.parseShort(param.getRound()));
								}
								else			//杯赛，分组
								{
									sch.setGrouping(param.getRound());
									sch.setGrouping2(param.getGroup());
								}
							}
							
							sch.setLocation(param.getLocation());
							sch.setWeather(param.getWeather());
							sch.setTemperature(param.getTemperature());
							sch.setMatchseason(param.getSeason());
							sch.setNeutrality(param.isNeutrality());
							sch.setExplain(param.getExplain());
							scheduleService.insertSelective(sch);
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			logger.info("赛程赛果定时任务抓取ScheduleTask异常，异常：{}",e.getMessage());
		}
	}
	public void spiderScheduleByID(int id)
	{
		try
		{
			String url = "http://test.gunqiu.com/win007/zq/BF_XMLByID.aspx?id=" + id;
			String reqXml = HttpClientUtil.callHttpGet(url,"utf-8");
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+url+"<-url source match data is empty.");
				return;
			}
			
			MatchBody reqBody = BeanXmlUtil.fromXml(reqXml, MatchBody.class);
			if(reqBody == null || reqBody.getMatch()== null || reqBody.getMatch().size() == 0) {
				logger.info("httpclient-> No ongoing football game.");
				return;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(MatchParam param:reqBody.getMatch())
			{
				int sid = param.getId();
				ZqSchedule sch = scheduleService.selectBySid(sid);
				if(sch != null)
				{
					scheduleService.getPanResult(sid, param.getHomeScore().intValue(), param.getGuestScore().intValue(), sch);
					//库里面完场的赛事不做处理
					if(sch.getMatchstate() == -1)
					{
						continue;
					}
					else if(param.getState() == -1)
					{
						
					}
					sch.setMatchtime(sdf.parse(param.getMtime().trim().replace("/", "-")));
					sch.setMatchstate(param.getState());
					//主队名称
					String[] homeTeam = param.getHomeName().split(",");
					if(homeTeam.length > 3)
					{
						sch.setHometeam(homeTeam[0]);
						sch.setHometeamid(Integer.parseInt(homeTeam[3]));
					}
					//客队名称
					String[] guestTeam = param.getGuestName().split(",");
					if(guestTeam.length > 3)
					{
						sch.setGuestteam(guestTeam[0]);
						sch.setGuestteamid(Integer.parseInt(guestTeam[3]));
					}
					sch.setHomescore(param.getHomeScore());
					sch.setGuestscore(param.getGuestScore());
					sch.setHomehalfscore(param.getHomeHalfScore());
					sch.setGuesthalfscore(param.getGuestHalfScore());
					sch.setHomeRed(param.getHomeRed());
					sch.setGuestRed(param.getGuestRed());
					sch.setHomeOrder(param.getHomeOrder().trim());
					sch.setGuestOrder(param.getGuestOrder().trim());
					sch.setLocation(param.getLocation());
					sch.setWeather(param.getWeather());
					sch.setTemperature(param.getTemperature());
					sch.setMatchseason(param.getSeason());
					sch.setNeutrality(param.isNeutrality());
					sch.setExplain(param.getExplain());
					scheduleService.updateByPrimaryKeySelective(sch);
				}
				else
				{
					sch = new ZqSchedule();
					sch.setSid(sid);
					//联赛id
					String[] leagueArray = param.getLeague().split(",");
					int type = 1;
					if(leagueArray.length > 3)
					{
						int sclassid = Integer.parseInt(leagueArray[3]);
						sch.setSclassid(sclassid);
						ZqSclass sc = sclassService.selectByLeagueId(sclassid);
						if(sc != null){
							type = sc.getKind();
						}
					}
					sch.setMatchtime(sdf.parse(param.getMtime().trim().replace("/", "-")));
					sch.setMatchstate(param.getState());
					//主队名称
					String[] homeTeam = param.getHomeName().split(",");
					if(homeTeam.length > 3)
					{
						sch.setHometeam(homeTeam[0]);
						sch.setHometeamid(Integer.parseInt(homeTeam[3]));
					}
					//客队名称
					String[] guestTeam = param.getGuestName().split(",");
					if(guestTeam.length > 3)
					{
						sch.setGuestteam(guestTeam[0]);
						sch.setGuestteamid(Integer.parseInt(guestTeam[3]));
					}
					sch.setHomescore(param.getHomeScore());
					sch.setGuestscore(param.getGuestScore());
					sch.setHomehalfscore(param.getHomeHalfScore());
					sch.setGuesthalfscore(param.getGuestHalfScore());
					sch.setHomeRed(param.getHomeRed());
					sch.setGuestRed(param.getGuestRed());
					sch.setHomeOrder(param.getHomeOrder().trim());
					sch.setGuestOrder(param.getGuestOrder().trim());
					if(!StringUtils.isBlank(param.getRound())){
						if(type == 1)	//联赛，分轮次
						{
							sch.setRound(Short.parseShort(param.getRound()));
						}
						else			//杯赛，分组
						{
							sch.setGrouping(param.getRound());
						}
					}
					
					sch.setLocation(param.getLocation());
					sch.setWeather(param.getWeather());
					sch.setTemperature(param.getTemperature());
					sch.setMatchseason(param.getSeason());
					sch.setNeutrality(param.isNeutrality());
					sch.setExplain(param.getExplain());
					scheduleService.insertSelective(sch);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("赛程赛果定时任务抓取ScheduleTask异常，异常：{}",e.getMessage());
		}
	}
}
