package com.dayspass.datacenter.task.football.jsbf;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.command.ChangeRoundCommand;
import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.domain.ZqSchedule;
import com.dayspass.datacenter.handler.football.GrabForChangeMatch;
import com.dayspass.datacenter.json.util.JsonUtil;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.dayspass.datacenter.service.football.match.ZqScheduleService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.json.JSONObject;


/**
 * 即时比分
 * @user zhangcb
 * @date 2016年1月25日
 */
@Component("jsBfChangeTask")
public class JsBfChangeTask {
	private static Logger logger = LoggerFactory.getLogger(JsBfChangeTask.class);
	
	@Value("${zq.jsbfchangeUrl}")
	private String changeUrl;
	
	@Value("${zq.jsbf.changepath}")
	private String position;
	
	@Value("${ZQ_JSBF_SCHEDULE}")
	private String ZQ_JSBF_SCHEDULE;
	
	@Autowired
	private GrabForChangeMatch grabChangeMatch;
	@Autowired
	private ZqScheduleService scheduleService;
	@Autowired
	private JedisClient jedisClient;
	
	public static BlockingQueue<ChangeRoundCommand> queueone = new LinkedBlockingQueue<ChangeRoundCommand>(); 	// 队列1-比分状态发生变化
	
	@SuppressWarnings("unchecked")
	public void spiderCreateChange()
	{
		try
		{
			List<ChangeRoundCommand> list = grabChangeMatch.collect(changeUrl, null, "GBK");
			Map<Integer, ChangeRoundCommand> changeMatch = Maps.newHashMap();
			if(jedisClient.existsObject("jsbf_change_bf"))
			{
				changeMatch = (Map<Integer, ChangeRoundCommand>)jedisClient.getObject("jsbf_change_bf");
			}
			if(!StringUtils.isEmpty(list))
			{
				for (ChangeRoundCommand crc : list) {
					logger.info("球探比分变化数据：" + JSONObject.fromObject(crc));
					queueone.put(crc);
					int sid = crc.getSid();
					if(changeMatch.containsKey(sid))
					{
						ChangeRoundCommand change = changeMatch.get(sid);
						crc.setDate(change.getDate());
						changeMatch.put(sid,crc);
					}
					else
					{
						//未开赛的、完场的不保留
						if(crc.getMatchstate() > 0)
						{
							changeMatch.put(sid,crc);
						}
					}
				}
			}
			if(changeMatch != null && changeMatch.size() > 0)
			{
				List<ChangeRoundCommand> changeList = Lists.newArrayList();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Map<Integer, ChangeRoundCommand> newchange = Maps.newHashMap();
				
				for (Map.Entry<Integer, ChangeRoundCommand> entry : changeMatch.entrySet())
				{
					ChangeRoundCommand command = entry.getValue();
					//完场的比赛超过两小时,移除掉
					Date nowtime = new Date();
					Date overtime = command.getDate();
					if(overtime == null)
					{
						if(command.getMatchstate() < 0)
						{
							command.setDate(new Date());
						}
						if(!StringUtils.isEmpty(command.getMatchtime()))
						{
							overtime = sdf.parse(command.getMatchtime());
						}
					}
					if(overtime != null)
					{
						long hour = (nowtime.getTime() - overtime.getTime())/3600000;
						if(hour > 2)
						{
							continue;
						}
					}
					newchange.put(command.getSid(), command);
					changeList.add(command);
				}
				jedisClient.setObject("jsbf_change_bf", newchange);
				JSONObject json = new JSONObject();
				json.put("data", JsonUtil.JsonArray(changeList));
				FileUtils.write(new File(position +  "change.json"), json.toString(),"UTF-8"); 
			}
		}
		catch (Exception e) 
		{
			logger.error("即时比分变化数据解析发生异常,异常消息" + e.getMessage());
		}
	}
	/**
	 * 比赛状态更新
	 * @author zhangcb
	 */
	public void scheduleConsumQueueOne() {
		boolean flag = true; // 线程停止
		while(flag){
			try {
				ChangeRoundCommand crc = queueone.take(); // 消费队列段数据
				if(crc != null) {
					String key = ZQ_JSBF_SCHEDULE + crc.getSid();
					if(jedisClient.existsObject(key))
					{
						ZqSchedule sc = (ZqSchedule) jedisClient.getObject(key);
						int state = sc.getMatchstate().intValue();
						if(state != crc.getMatchstate() || sc.getHomescore().intValue() != crc.getHomescore()
								|| sc.getGuestscore().intValue() != crc.getGuestscore() || sc.getHomeRed().intValue() != crc.getHomeRed()
								|| sc.getGuestRed().intValue() != crc.getGuestRed() || sc.getHomeYellow().intValue() != crc.getHomeYellow()
								|| sc.getGuestYellow().intValue() != crc.getGuestYellow())
						{
							if(state < 0)
							{
								//-11:待定,-12:腰斩,-13:中断,-14:推迟,-1:完场，-10取消 ,不处理
								continue;
							}
							else if(crc.getMatchstate() == -1)
							{
								//刚完场的赛事，统计盘路结果
								scheduleService.getPanResult(sc.getSid(), sc.getHomescore().intValue(), sc.getGuestscore().intValue(), sc);
								//删除该比赛赔率缓存
								if(!StringUtils.isEmpty(sc.getSid())) {
									jedisClient.delkeyObject("allcom_" + sc.getSid().intValue());
									jedisClient.delkeyObject("allkaili_" + sc.getSid().intValue());
								}
							}
							sc.setMatchstate(crc.getMatchstate());
							sc.setHomescore(crc.getHomescore());
							sc.setGuestscore(crc.getGuestscore());
							sc.setHomeRed(crc.getHomeRed());
							sc.setGuestRed(crc.getGuestRed());
							sc.setHomeYellow(crc.getHomeYellow());
							sc.setGuestYellow(crc.getGuestYellow());
							jedisClient.setObject(key, sc, 24*60*60);
							scheduleService.updateByPrimaryKeySelective(sc);
						}
					}
				}
			} catch (Exception e) {
				logger.info("JsBfChangeTask即时比分比赛状态更新异常：", e);
				continue;
			} 
			if(queueone.isEmpty()){
				flag = false;
			}
		}
	}
}
