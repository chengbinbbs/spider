package com.dayspass.datacenter.task.football.team;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.ZqTeamBody;
import com.dayspass.datacenter.bean.ZqTeamParam;
import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.domain.ZqTeam;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.service.football.team.ZqTeamService;
import com.dayspass.datacenter.task.football.player.PlayerTask;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;



/**
 * 球队信息
 * @user zhangcb
 * @date 2016年1月28日
 */
@Component("teamTask")
public class TeamTask {
	private static Logger logger = LoggerFactory.getLogger(TeamTask.class);
	
	@Autowired
	private ZqTeamService teamService;
	@Autowired
	private PlayerTask playerTask;
	
	@Value("${zq.teaminfoUrl}")
	private String teamUrl;	//球队数据
	
	public static BlockingQueue<Integer> queueone = new LinkedBlockingQueue<Integer>(); 		// 队列-球员统计
	
	public void spiderTeam(){
		try
		{
			String reqXml = HttpClientUtil.callHttpGet(teamUrl,"utf-8");
			if(StringUtils.isEmpty(reqXml)) {
				logger.info("httpclient-> "+teamUrl+"<-url source team data is empty.");
				return; 
			}
			
			ZqTeamBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqTeamBody.class);
			if(reqBody == null || reqBody.getTeam()== null || reqBody.getTeam().size() == 0) {
				logger.info("httpclient-> No ongoing football game.");
				return;
			}
			for(ZqTeamParam param : reqBody.getTeam()) 
			{
				int id = param.getId();
				queueone.put(id);
				ZqTeam team = teamService.selectByPrimaryKey(id);
				if(team != null)
				{
					team.setSclassid(param.getLsID());
					team.setNameJ(param.getG());
					team.setNameF(param.getB());
					team.setNameE(param.getE());
					team.setFoundDate(param.getFound());
					team.setArea(param.getArea());
					team.setGymnasium(param.getGym());
					team.setCapacity(param.getCapacity());
					team.setFlag(param.getFlag());
					team.setAddress(param.getAddr());
					team.setUrl(param.getURL());
					team.setDrillmaster(param.getMaster());
					team.setModifytime(new Date());
					teamService.updateByPrimaryKeySelective(team);
				}
				else
				{
					team = new ZqTeam();
					team.setTeamid(param.getId());
					team.setSclassid(param.getLsID());
					team.setKind((short) 1);
					team.setNameJ(param.getG());
					team.setNameF(param.getB());
					team.setNameE(param.getE());
					team.setFoundDate(param.getFound());
					team.setArea(param.getArea());
					team.setGymnasium(param.getGym());
					team.setCapacity(param.getCapacity());
					team.setFlag(param.getFlag());
					team.setAddress(param.getAddr());
					team.setUrl(param.getURL());
					team.setDrillmaster(param.getMaster());
					team.setModifytime(new Date());
					teamService.insertSelective(team);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("球队信息抓起定时任务TeamTask更新异常，异常信息：{}",e.getMessage());
		}
	}
	
	/**
	 * 抓取球队的球员数据
	 * @author zhangcb
	 */
	public void scheduleConsumQueueOne() {
		boolean flag = true; // 线程停止
		long begintime = System.currentTimeMillis();
		while(flag){
			try {
				Integer teamid = queueone.take(); // 消费队列段数据
				if(!StringUtils.isEmpty(teamid)) {
					playerTask.spiderTeamPlayer(teamid);
				}
			} catch (Exception e) {
				logger.info("更新首发阵容统计异常：", e);
				continue;
			} 
			if(queueone.isEmpty()){
				flag = false;
			}
		}
		long endtime = System.currentTimeMillis();
		logger.info("抓取球队的球员数据队列处理时间：" + (endtime-begintime)/1000);	
	}
}
