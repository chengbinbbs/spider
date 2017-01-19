package com.dayspass.datacenter.task.football.league;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.domain.ZqLeagueBody;
import com.dayspass.datacenter.domain.ZqLeagueParam;
import com.dayspass.datacenter.domain.ZqSclass;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.service.football.league.ZqSclassService;
import com.dayspass.datacenter.task.football.match.ScheduleBySclassTask;
import com.dayspass.datacenter.task.football.player.PlayerTechTask;
import com.dayspass.datacenter.task.football.score.ScoreTask;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;


/**
 * 联赛杯赛入库
 * @author zcb
 * @date 2016-1-19
 *
 */
@Component("leagueTask")
public class LeagueTask {
	
	private static Logger logger = LoggerFactory.getLogger(LeagueTask.class);
	
	@Value("${zq.leagueUrl}")
	private String url;
	
	@Autowired
	private ZqSclassService sclassService;
	@Autowired
	private PlayerTechTask playerTechTask;
	@Autowired
	private ScoreTask scoreTask;
	@Autowired
	private ScheduleBySclassTask scheduleBySclassTask;
	
	public static BlockingQueue<ZqSclass> queueone = new LinkedBlockingQueue<ZqSclass>(); 	// 队列1-球员数据统计
	public static BlockingQueue<ZqSclass> queuetwo = new LinkedBlockingQueue<ZqSclass>(); 	// 队列2-联赛积分数据统计
	public static BlockingQueue<ZqSclass> queuethree = new LinkedBlockingQueue<ZqSclass>(); // 队列3-联赛赛程数据统计
	
	public void spiderLeague()
	{
		try
		{
			String reqXml = HttpClientUtil.callHttpGet(url,"utf-8");
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+url+"<-url source league data is empty.");
				return; 
			}
			
			ZqLeagueBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqLeagueBody.class);
			if(reqBody == null || reqBody.getLeague()== null || reqBody.getLeague().size() == 0) {
				logger.info("httpclient-> No ongoing football game.");
				return;
			}
			for(ZqLeagueParam param:reqBody.getLeague())
			{
				//查询联赛是否存在
				int sclassid = param.getId();
				ZqSclass sc = sclassService.selectByLeagueId(sclassid);
				if(sc != null)
				{
					sc.setColor(param.getColor());
					sc.setNameJs(param.getGb_short());
					sc.setNameFs(param.getBig_short());
					sc.setNameEs(param.getEn_short());
					sc.setNameJ(param.getGb());
					sc.setNameF(param.getBig());
					sc.setNameE(param.getEn());
					sc.setKind(param.getType());
					sc.setMode(param.getType());  //比赛方式，分轮（1）还是分组（2）
					sc.setCountRound(param.getSum_round());
					sc.setCurrRound(param.getCurr_round());
					sc.setCurrMatchseason(param.getCurr_matchSeason());
					sc.setModifytime(new Date());
					sc.setLeagueId(sclassid);
					sclassService.updateByPrimaryKeySelective(sc);
				}
				else
				{
					sc = new ZqSclass();
					sc.setLeagueId(sclassid);
					sc.setColor(param.getColor());
					sc.setNameJs(param.getGb_short());
					sc.setNameFs(param.getBig_short());
					sc.setNameEs(param.getEn_short());
					sc.setNameJ(param.getGb());
					sc.setNameF(param.getBig());
					sc.setNameE(param.getEn());
					sc.setKind(param.getType());
					sc.setMode(param.getType());  //比赛方式，分轮（1）还是分组（2）
					sc.setCountRound(param.getSum_round());
					sc.setCurrRound(param.getCurr_round());
					sc.setCurrMatchseason(param.getCurr_matchSeason());
					sc.setModifytime(new Date());
					sclassService.insertSelective(sc);
				}
				queueone.put(sc);
				queuetwo.put(sc);
				queuethree.put(sc);
				//更新联赛当前赛季
//				Map<String,Object> map = Maps.newHashMap();
//				map.put("sclassid", sclassid);
//				map.put("season", sc.getCurrMatchseason());
//				List<ZqSeason> seasonList = seasonService.queryAll(map);
//				if(seasonList == null || seasonList.size() == 0)
//				{
//					//出现新赛季，当前赛季设置为false
//					seasonService.updateCurrentSeason(map);
//					ZqSeason season = new ZqSeason();
//					season.setSclassid(sclassid);
//					season.setSeason(sc.getCurrMatchseason());
//					season.setIsCurrent(true);
//					season.setIsUpdate(false);
//					season.setCreateTime(new Date());
//					seasonService.insertSelective(season);
//				}
			}
		}
		catch(Exception e)
		{
			logger.info("联赛杯赛定时任务抓取LeagueTask异常，异常：{}",e.getMessage());
		}
	}
	
	/**
	 * 球员技术统计抓取
	 * @author zhangcb
	 */
	public void scheduleConsumQueueOne() {
		boolean flag = true; // 线程停止
		long begintime = System.currentTimeMillis();
		while(flag){
			try {
				ZqSclass sc = queueone.take(); // 消费队列段数据
				if(sc != null) {
					playerTechTask.spiderForPlayerTech(sc.getCurrMatchseason(), sc.getLeagueId());
				}
			} catch (Exception e) {
				logger.info("球员技术统计抓取异常：", e);
				continue;
			} 
			if(queueone.isEmpty()){
				flag = false;
			}
		}
		long endtime = System.currentTimeMillis();
		logger.info("球员技术统计抓取处理时间：" + (endtime-begintime)/1000);	
	}
	
	/**
	 * 联赛积分抓取消费队列
	 * @author zhangcb
	 */
	public void scheduleConsumQueueTwo() {
		boolean flag = true; // 线程停止
		long begintime = System.currentTimeMillis();
		while(flag){
			try {
				ZqSclass sc = queuetwo.take(); // 消费队列段数据
				if(sc != null) {
					scoreTask.spiderForSclassScore(sc);
				}
			} catch (Exception e) {
				logger.info("联赛积分抓取异常：", e);
				continue;
			} 
			if(queuetwo.isEmpty()){
				flag = false;
			}
		}
		long endtime = System.currentTimeMillis();
		logger.info("联赛积分抓取处理时间：" + (endtime-begintime)/1000);	
	}
	
	/**
	 * 抓取联赛整个赛季的赛程
	 * @author zhangcb
	 */
	public void scheduleConsumQueueThree() {
		boolean flag = true; // 线程停止
		long begintime = System.currentTimeMillis();
		while(flag){
			try {
				ZqSclass sc = queuethree.take(); // 消费队列段数据
				if(sc != null) {
					scheduleBySclassTask.handleScheduleBySclass(sc);
				}
			} catch (Exception e) {
				logger.info("根据联赛抓取整个赛季的赛程抓取异常：", e);
				continue;
			} 
			if(queuethree.isEmpty()){
				flag = false;
			}
		}
		long endtime = System.currentTimeMillis();
		logger.info("根据联赛抓取整个赛季的赛程处理时间：" + (endtime-begintime)/1000);	
	}
}
