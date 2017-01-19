package com.dayspass.datacenter.task.football.jsbf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.ZqJsBfMatchBody;
import com.dayspass.datacenter.bean.ZqJsBfMatchParam;
import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.domain.ZqSchedule;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.dayspass.datacenter.service.football.match.ZqScheduleService;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;



/**
 * 即时比分
 * @user zhangcb
 * @date 2016年1月29日
 */
@Component("jsbfMatchTask")
public class JsBfMatchTask {
	private static Logger logger = LoggerFactory.getLogger(JsBfMatchTask.class);
	
	@Autowired
	private ZqScheduleService scheduleService;
	@Autowired
	private LiveMatchTask liveMatchTask;
	
	@Value("${zq.jsbfmatchUrl}")
	private String matchsUrl;	//当天完整比分数据
	@Value("${ZQ_JSBF_SCHEDULE}")
	private String ZQ_JSBF_SCHEDULE;
	@Autowired
	private JedisClient jedisClient;
	
	public static BlockingQueue<ZqSchedule> queueone = new LinkedBlockingQueue<ZqSchedule>(); 	// 队列1-生成首发阵容、技术统计、比赛事件接口
	public static BlockingQueue<ZqJsBfMatchParam> queuetwo = new LinkedBlockingQueue<ZqJsBfMatchParam>(); 	// 队列2-生成新增的比赛
	public static BlockingQueue<ZqSchedule> queuethree = new LinkedBlockingQueue<ZqSchedule>(); 		// 队列3-推荐结算任务
	
	public void spiderMatchScore()
	{
		try
		{
			//1.请求球探比分接口，获取比赛的比分
			String reqXml = HttpClientUtil.callHttpGet(matchsUrl,"utf-8");
			if(StringUtils.isEmpty(reqXml)) {
				logger.info("httpclient-> "+matchsUrl+"<-url source matchs data is empty.");
				return; 
			}
			reqXml = reqXml.replace(">True<", ">true<").replace(">False<", ">false<");
			ZqJsBfMatchBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqJsBfMatchBody.class);
			if(reqBody == null || reqBody.getMatch()== null || reqBody.getMatch().size() == 0) {
				logger.info("httpclient-> No ongoing football game.");
				return;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(ZqJsBfMatchParam param : reqBody.getMatch()) 
			{
				int sid = param.getID();
				ZqSchedule sc = scheduleService.selectBySid(sid);
				//出现新增比赛
				if(sc == null)
				{
					queuetwo.put(param);
					continue;
				}
				queueone.put(sc);	//生成技术统计、比赛事件对口
				if(param.getState() > 0 || param.getState() == -1)
				{
					//赔率用
					jedisClient.setObject("schduleend_" + sid, sid, 2*24*60*60);
				}
				else
				{
					jedisClient.delkeyObject("schduleend_" + sid);
				}
				
				//1.刚结束的比赛
				if(sc.getMatchstate() != param.getState())
				{
					if(param.getState() == -1)
					{
						if(!StringUtils.isEmpty(param.getTime()))
						{
							sc.setMatchtime(sdf.parse(param.getTime().trim().replace("/", "-")));
						}
						sc.setHomescore(param.getHomeScore());
						sc.setGuestscore(param.getAwayScore());
						sc.setMatchstate(param.getState());
						//推荐结算任务
						queuethree.put(sc); 
						//刚完场的赛事，统计盘路结果
						scheduleService.getPanResult(sid, param.getHomeScore().intValue(), param.getAwayScore().intValue(), sc);
						//删除该比赛赔率缓存
						if(!StringUtils.isEmpty(sc.getSid())) {
							jedisClient.delkeyObject("allcom_" + sc.getSid().intValue());
							jedisClient.delkeyObject("allkaili_" + sc.getSid().intValue());
						}
					}
					if(param.getState() < -1) {//比赛未正常完场
						sc.setMatchstate(param.getState());
						queuethree.put(sc);
					}
				}
				
				//2.比赛发生变化时（比赛状态、时间、比分、红黄牌）
				String mtime = null;
				if(!StringUtils.isEmpty(param.getTime()))
				{
					mtime = sdf.format(sdf.parse(param.getTime().trim().replace("/", "-")));
				}
				if(param.getState() != sc.getMatchstate() || param.getHomeScore() != sc.getHomescore() || param.getAwayScore() != sc.getGuestscore()
						|| param.getRed1() != sc.getHomeRed() || param.getRed2() != sc.getGuestRed() 
						|| param.getYellow1() != sc.getHomeYellow() || param.getYellow2() != sc.getGuestYellow()
						|| !mtime.equals(sdf.format(sc.getMatchtime()))
						|| !param.getExplain2().equals(sc.getExplainlist()))
				{
					sc.setMatchstate(param.getState());
					sc.setHomescore(param.getHomeScore());
					sc.setGuestscore(param.getAwayScore());
					sc.setHomeRed(param.getRed1());
					sc.setGuestRed(param.getRed2());
					sc.setHomeYellow(param.getYellow1());
					sc.setGuestYellow(param.getYellow2());
					
					sc.setHomehalfscore(param.getBc1());
					sc.setGuesthalfscore(param.getBc2());
					if(!StringUtils.isEmpty(param.getTime()))
					{
						sc.setMatchtime(sdf.parse(param.getTime().trim().replace("/", "-")));
					}
					if(!StringUtils.isEmpty(param.getTime2()))
					{
						sc.setMatchtime2(sdf.parse(param.getTime2().trim().replace("/", "-")));
					}
					if(!StringUtils.isEmpty(param.getExplain2()))
					{
						formateExplain(param.getExplain2(),sc);
					}
					sc.setNeutrality(param.getZl());
					scheduleService.updateByPrimaryKeySelective(sc);
				}
				//3.球探状态异常时，程序自动判断比赛状态
				if(sc.getMatchstate() == 0)
				{
					//距离开赛时间10分钟未开赛的状态变为推迟
					long count = (new Date().getTime() - sc.getMatchtime().getTime())/60000;
					if(count > 10)
					{
						sc.setMatchstate((short) -14);
					}
				}
				else if(sc.getMatchstate() > 0 && sc.getMatchstate() < 4)
				{
					long hour = (new Date().getTime() - sc.getMatchtime().getTime())/3600000;
					if(hour > 2)
					{
						sc.setMatchstate((short) -1);
					}
				}
				String key = ZQ_JSBF_SCHEDULE + sid;
				jedisClient.setObject(key, sc, 24*60*60);
			}
		}
		catch(Exception e)
		{
			logger.info("即时比分定时任务JsBfMatchTask更新异常，异常信息：{}",e.getMessage());
		}
	}
	
	/**
	 * 比赛说明(加时 点球等）
	 * @param explain2
	 * CCTV5 上海体育 北京体育;|2;|4;3|90,2-1;3-3;1,2-1;1-3;2
	 * @return
	 */
	private void formateExplain(String explain, ZqSchedule sc) {
		try
		{
			String[] array = explain.split("\\|");
			if(array.length >= 4 && !StringUtils.isEmpty(array[3]))
			{
				String[] explainlist = array[3].split(";");
				if(explainlist.length > 0)
				{
					StringBuilder sb = new StringBuilder();
					//1.90分钟
					if(!StringUtils.isEmpty(explainlist[0]))
					{
						String[] score = explainlist[0].split(",");	
						sb.append(score[0]).append("分钟[").append(score[1].replace("-", ":")).append("],");
					}
					//2.两回合
					if(explainlist.length > 1 && !StringUtils.isEmpty(explainlist[1]))
					{
						sb.append("两回合[").append(explainlist[1].replace("-", ":")).append("],");
					}
					//3.120分钟
					if(explainlist.length > 2 && !StringUtils.isEmpty(explainlist[2]))
					{
						//1,2-1：,号前面的数据（1: 120分钟;2: 加时;3: 加时中）2-1：主得分-客得分
						String[] jiashi = explainlist[2].split(",");	//120分钟
						if(jiashi.length > 0)
						{
							int type = Integer.parseInt(jiashi[0]);
							if(type == 1)
							{
								sb.append("120分钟[");
							}
							else if(type == 2)
							{
								sb.append("加时[");
							}
							else if(type == 3)
							{
								sb.append("加时中[");
							}
							sb.append(jiashi[1].replace("-", ":")).append("],");
						}
					}
					//4.点球
					if(explainlist.length > 3 && !StringUtils.isEmpty(explainlist[3]))
					{
						sb.append("点球[").append(explainlist[3].replace("-", ":")).append("],");
					}
					//5.胜出,（1：主，2：客）
					if(explainlist.length > 4 && !StringUtils.isEmpty(explainlist[4]))
					{
						int type = Integer.parseInt(explainlist[4]);
						if(type == 1)
						{
							sb.append(sc.getHometeam()).append("胜出,");
						}
						else
						{
							sb.append(sc.getGuestteam()).append("胜出,");
						}
					}
					String remark= sb.toString().length() > 0?sb.substring(0, sb.toString().length() -1):sb.toString();
					sc.setExplainlist(remark);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("比赛sid:" +sc.getSid() +",加时，比赛说明：" +explain+ ",解析异常");
		}
		
	}


	/**
	 * 生成比赛直播接口
	 * @author zhangcb
	 */
	public void scheduleConsumQueueOne() {
		boolean flag = true; // 线程停止
		long begintime = System.currentTimeMillis();
		while(flag){
			try {
				ZqSchedule sche = queueone.take(); // 消费队列段数据
				if(sche != null) {
					liveMatchTask.spiderLiveMatch(sche);
				}
			} catch (Exception e) {
				logger.info("生成比赛直播接口异常：", e);
				continue;
			} 
			if(queueone.isEmpty()){
				flag = false;
			}
		}
		long endtime = System.currentTimeMillis();
		logger.info("生成比赛直播接口队列处理时间：" + (endtime-begintime)/1000);	
	}
	/**
	 * 即时比分新增赛事消费队列
	 * @author zhangcb
	 */
	public void scheduleConsumQueueTwo() {
		boolean flag = true; // 线程停止
		long begintime = System.currentTimeMillis();
		while(flag){
			try {
				ZqJsBfMatchParam param = queuetwo.take(); // 消费队列段数据
				if(param != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					ZqSchedule sc = new ZqSchedule();
					sc.setScheduleid(param.getID());
					//联赛id
					sc.setSclassid(param.getLeagueID());
					if(!StringUtils.isEmpty(param.getTime()))
					{
						sc.setMatchtime(sdf.parse(param.getTime().trim().replace("/", "-")));
					}
					if(!StringUtils.isEmpty(param.getTime2()))
					{
						sc.setMatchtime2(sdf.parse(param.getTime2().trim().replace("/", "-")));
					}
					sc.setMatchstate(param.getState());
					//主队名称
					String[] homeTeam = param.getHome().split(",");
					if(homeTeam.length > 3)
					{
						sc.setHometeam(homeTeam[0]);
						sc.setHometeamid(Integer.parseInt(homeTeam[3]));
					}
					//客队名称
					String[] guestTeam = param.getAway().split(",");
					if(guestTeam.length > 3)
					{
						sc.setGuestteam(guestTeam[0]);
						sc.setGuestteamid(Integer.parseInt(guestTeam[3]));
					}
					sc.setHomescore(param.getHomeScore());
					sc.setGuestscore(param.getAwayScore());
					sc.setHomehalfscore(param.getBc1());
					sc.setGuesthalfscore(param.getBc2());
					sc.setHomeRed(param.getRed1());
					sc.setGuestRed(param.getRed2());
					sc.setHomeOrder(param.getOrder1().trim());
					sc.setGuestOrder(param.getOrder2().trim());
					sc.setNeutrality(param.getZl());
					sc.setExplain(param.getExplain());
					scheduleService.insertSelective(sc);
				}
			} catch (Exception e) {
				logger.info("即时比分新增赛事异常：", e);
				continue;
			} 
			if(queuetwo.isEmpty()){
				flag = false;
			}
		}
		long endtime = System.currentTimeMillis();
		logger.info("即时比分新增赛事队列处理时间：" + (endtime-begintime)/1000);	
	}
}
