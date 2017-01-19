package com.dayspass.datacenter.task.football.init;

import java.text.SimpleDateFormat;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * @date 2017年1月9日
 */
@Component("scheduleInitTask")
public class ScheduleInitTask {

	private static Logger logger = LoggerFactory.getLogger(ScheduleInitTask.class);
	
	@Value("${zq.scheduleinfoUrl}")
	private String url;
	@Autowired
	private ZqScheduleService scheduleService;
	@Autowired
	private ZqSclassService sclassService;
	
	
	public static BlockingQueue<MatchParam> queueone = new LinkedBlockingQueue<MatchParam>(); 	// 队列1-统计盘路结果
	public static BlockingQueue<Integer> queuetwo = new LinkedBlockingQueue<Integer>(); 		// 队列2-数据面统计
	
	public void spiderSchedule()
	{
		try
		{
			DateTime dt = new DateTime("2016-12-01");
			for(int i =0;i<40;i++){
				String date = dt.plusDays(i).toString("yyyy-MM-dd");
				String reqXml = HttpClientUtil.callHttpGet(url +"?date="+date,"utf-8");
				if(StringUtils.isBlank(reqXml)) {
					logger.info("httpclient-> "+url+"<-url source match data is empty.");
					return; 
				}
				reqXml = reqXml.replace(">True<", ">true<").replace(">False<", ">false<");
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
						//库里面完场的赛事不做处理
						queueone.put(param);
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
								sch.setGrouping2(param.getGroup());
							}
						}
						sch.setLocation(param.getLocation());
						sch.setWeather(param.getWeather());
						sch.setMatchseason(param.getSeason());
						sch.setNeutrality(param.isNeutrality());
						sch.setExplain(param.getExplain());
						scheduleService.insertSelective(sch);
					}
					queuetwo.put(sid);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("赛程赛果定时任务抓取ScheduleTask异常，异常：{}",e.getMessage());
		}
	}

}
