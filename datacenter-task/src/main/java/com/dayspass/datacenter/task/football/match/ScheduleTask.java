package com.dayspass.datacenter.task.football.match;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.ZqMatchBody;
import com.dayspass.datacenter.bean.ZqMatchParam;
import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.domain.ZqSchedule;
import com.dayspass.datacenter.domain.ZqSclass;
import com.dayspass.datacenter.domain.ZqSubSclass;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.dayspass.datacenter.service.football.league.ZqSclassService;
import com.dayspass.datacenter.service.football.league.ZqSubSclassService;
import com.dayspass.datacenter.service.football.match.ZqScheduleService;
import com.dayspass.datacenter.task.football.league.CupMatchTask;
import com.dayspass.datacenter.task.football.score.ScoreTask;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;
import com.google.common.collect.Maps;


/**
 * 赛程赛果
 * @user zhangcb
 * @date 2016年1月28日
 */
@Component("scheduleTask")
public class ScheduleTask {

	private static Logger logger = LoggerFactory.getLogger(ScheduleTask.class);
	
	@Value("${zq.scheduleinfoUrl}")
	private String url;
	@Autowired
	private ZqScheduleService scheduleService;
	@Autowired
	private ZqSclassService sclassService;
	@Autowired
	private CupMatchTask cupMatchTask;
	@Autowired
	private ScoreTask scoreTask;
	@Autowired
	private ZqSubSclassService subSclassService;
	@Autowired
	private JedisClient jedisClient;
	
	public static BlockingQueue<ZqMatchParam> queueone = new LinkedBlockingQueue<ZqMatchParam>(); 	// 队列1-统计盘路结果
	public static BlockingQueue<Integer> queuetwo = new LinkedBlockingQueue<Integer>(); 		// 队列2-杯赛统计
	public static BlockingQueue<Integer> queuethree = new LinkedBlockingQueue<Integer>(); 		// 队列3-联赛积分统计
	
	public void spiderSchedule()
	{
		try
		{
			String reqXml = HttpClientUtil.callHttpGet(url,"utf-8");
			if(StringUtils.isEmpty(reqXml)) {
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
			for(ZqMatchParam param:reqBody.getMatch())
			{
				int sid = param.getId();
				if(param.getState() > 0 || param.getState() == -1)
				{
					//赔率用
					jedisClient.setObject("schduleend_" + sid, sid, 2*24*60*60);
				}
				else
				{
					jedisClient.delkeyObject("schduleend_" + sid);
				}
				ZqSchedule sch = scheduleService.selectBySid(sid);
				if(sch != null)
				{
					//完场的赛事统计赛果
					if(param.getState() == -1)
					{
//						queueone.put(param);
						if(!queuethree.contains(sch.getSclassid()))
						{
							queuethree.put(sch.getSclassid());
						}
						continue;
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
					
					if(!StringUtils.isEmpty(param.getRound())){
						if(type == 1)	//联赛，分轮次
						{
							sch.setRound(Short.parseShort(param.getRound()));
							//匹配子联赛
							Map<String,Object> maps = Maps.newHashMap();
							maps.put("sclassid", sch.getSclassid());
							maps.put("subsclassname", param.getType());
							maps.put("currentseason", param.getSeason());
							List<ZqSubSclass> sublist = subSclassService.queryAll(maps);
							if(!StringUtils.isEmpty(sublist))
							{
								sch.setSubsclassid(sublist.get(0).getSubsclassid());
							}
						}
						else			//杯赛，分组
						{
							if("分组赛".equals(param.getRound().trim()) && !queuetwo.contains(sch.getSclassid()))
							{
								queuetwo.put(sch.getSclassid());
							}
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
					if(!StringUtils.isEmpty(param.getRound())){
						if(type == 1)	//联赛，分轮次
						{
							sch.setRound(Short.parseShort(param.getRound()));
							//匹配子联赛
							Map<String,Object> maps = Maps.newHashMap();
							maps.put("sclassid", sch.getSclassid());
							maps.put("subsclassname", param.getType());
							maps.put("currentseason", param.getSeason());
							List<ZqSubSclass> sublist = subSclassService.queryAll(maps);
							if(!StringUtils.isEmpty(sublist))
							{
								sch.setSubsclassid(sublist.get(0).getSubsclassid());
							}
						}
						else			//杯赛，分组
						{
							if("分组赛".equals(param.getRound().trim()) && !queuetwo.contains(sch.getSclassid()))
							{
								queuetwo.put(sch.getSclassid());
							}
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
			logger.info("赛程赛果定时任务抓取ScheduleTask异常，异常：{}",e.getMessage());
		}
	}
	/**
	 * 更新盘路结果消费队列
	 * @author zhangcb
	 */
	public void scheduleConsumQueueOne() {
		boolean flag = true; // 线程停止
		long begintime = System.currentTimeMillis();
		while(flag){
			try {
				ZqMatchParam param = queueone.take(); // 消费队列段数据
				if(param != null && param.getState() == -1) {
					ZqSchedule sch = scheduleService.selectBySid(param.getId());
					if(sch != null)
					{
						scheduleService.getPanResult(param.getId(), param.getHomeScore().intValue(), param.getGuestScore(), sch);
						scheduleService.updateByPrimaryKeySelective(sch);
					}
				}
			} catch (Exception e) {
				logger.info("比赛结束更新盘路结果异常：", e);
				continue;
			} 
			if(queueone.isEmpty()){
				flag = false;
			}
		}
		long endtime = System.currentTimeMillis();
		logger.info("比赛结束更新盘路结果队列处理时间：" + (endtime-begintime)/1000);	
	}

	
	/**
	 * 杯赛积分抓取消费队列
	 * @author zhangcb
	 */
	public void scheduleConsumQueueTwo() {
		boolean flag = true; // 线程停止
		long begintime = System.currentTimeMillis();
		while(flag){
			try {
				Integer sclassid = queuetwo.take(); // 消费队列段数据
				ZqSclass sc = sclassService.selectByLeagueId(sclassid);
				if(sc != null){
					cupMatchTask.spiderForCupMatch(sc);
				}
			} catch (Exception e) {
				logger.info("杯赛积分抓取异常：", e);
				continue;
			} 
			if(queuetwo.isEmpty()){
				flag = false;
			}
		}
		long endtime = System.currentTimeMillis();
		logger.info("杯赛积分抓取队列处理时间：" + (endtime-begintime)/1000);	
	}
	
	/**
	 * 联赛积分抓取消费队列
	 * @author zhangcb
	 */
	public void scheduleConsumQueueThree() {
		boolean flag = true; // 线程停止
		long begintime = System.currentTimeMillis();
		while(flag){
			try {
				Integer sclassid = queuethree.take(); // 消费队列段数据
				ZqSclass sc = sclassService.selectByLeagueId(sclassid);
				if(sc != null){
					scoreTask.spiderForSclassScore(sc);
				}
			} catch (Exception e) {
				logger.info("联赛积分抓取异常：", e);
				continue;
			} 
			if(queuethree.isEmpty()){
				flag = false;
			}
		}
		long endtime = System.currentTimeMillis();
		logger.info("联赛积分抓取队列处理时间：" + (endtime-begintime)/1000);	
	}
}
