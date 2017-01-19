package com.dayspass.datacenter.task.football.match;

import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.ZqMatchBody;
import com.dayspass.datacenter.bean.ZqMatchParam;
import com.dayspass.datacenter.domain.ZqSchedule;
import com.dayspass.datacenter.domain.ZqSclass;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.service.football.match.ZqScheduleService;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;



/**
 * 根据联赛抓取该联赛跟赛季的赛程赛果
 * @user zhangcb
 * @date 2016年1月28日
 */
@Component("scheduleBySclassTask")
public class ScheduleBySclassTask {

	private static Logger logger = LoggerFactory.getLogger(ScheduleBySclassTask.class);
	
	@Value("${zq.scheduleOfleagueUrl}")
	private String url;
	@Autowired
	private ZqScheduleService scheduleService;
	
	/**
	 * 根据联赛id抓取该联赛当前赛季的赛程
	 * @author zhangcb
	 * @param sclassid
	 */
	public void handleScheduleBySclass(ZqSclass sclass) {
		try
		{
			int sclassid = sclass.getLeagueId();
			logger.info("联赛sclassid:"+sclassid +"整个赛季的赛程抓取开始，url:"+url + "?sclassid=" + sclassid);
			String reqXml = HttpClientUtil.callHttpGet(url + "?sclassid=" + sclassid, "utf-8");
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+url+"<-url source match data is empty.");
				return; 
			}
			reqXml = reqXml.replace(">True<", ">true<").replace(">False<", ">false<");
			ZqMatchBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqMatchBody.class);
			if(reqBody == null || reqBody.getMatch()== null || reqBody.getMatch().size() == 0) {
				logger.info("httpclient-> No ongoing football game.");
				return;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int type = sclass.getKind(); //1.联赛，2.杯赛
			for(ZqMatchParam param:reqBody.getMatch())
			{
				int sid = param.getId();
				ZqSchedule sch = scheduleService.selectBySid(sid);
				if(sch != null)
				{
					sch.setMatchtime(sdf.parse(param.getMtime().trim().replace("/", "-")));
					sch.setMatchstate(param.getState());
					sch.setSclassid(sclassid);
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
					sch.setScheduleid(param.getId());
					sch.setSclassid(sclassid);
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
			}
		}
		catch(Exception e)
		{
			logger.info("根据联赛id抓取该联赛当前赛季的赛程异常，联赛sclassid："+ sclass.getLeagueId() +",异常：{}",e.getMessage());
		}
	}
}
