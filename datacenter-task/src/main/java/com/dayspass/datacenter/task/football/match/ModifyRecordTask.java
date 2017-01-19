package com.dayspass.datacenter.task.football.match;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.ZqModifyBody;
import com.dayspass.datacenter.bean.ZqModifyParam;
import com.dayspass.datacenter.date.util.DateUtils;
import com.dayspass.datacenter.domain.ZqSchedule;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.dayspass.datacenter.service.football.match.ZqScheduleService;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;



/**
 * 8小时内的赛程删除、比赛时间修改记录
 * @user zhangcb
 * @date 2016年2月2日
 */
@Component("modifyRecordTask")
public class ModifyRecordTask {

	private static Logger logger = LoggerFactory.getLogger(ModifyRecordTask.class);
	
	@Value("${zq.modifyrecordUrl}")
	private String url;
	@Autowired
	private ZqScheduleService scheduleService;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ZQ_JSBF_SCHEDULE}")
	private String ZQ_JSBF_SCHEDULE;
	
	public void spiderModify(){
		try
		{
			String reqXml = HttpClientUtil.callHttpGet(url,"utf-8");
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+url+"<-url source modify data is empty.");
				return; 
			}
			
			ZqModifyBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqModifyBody.class);
			if(reqBody == null || reqBody.getMatch()== null || reqBody.getMatch().size() == 0) {
				logger.info("httpclient-> No ongoing football game.");
				return;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(ZqModifyParam param:reqBody.getMatch())
			{
				int id = param.getID();
				ZqSchedule schedule = scheduleService.selectBySid(id);
				if(schedule != null)
				{
					//比赛时间更改
					if("modify".equals(param.getType()))
					{
						schedule.setMatchtime(sdf.parse(param.getMatchtime().trim().replace("/", "-")));
						Date nowtime = new Date();
						if(DateUtils.getInterval(nowtime, schedule.getMatchtime()) > 90)
						{
							schedule.setMatchstate((short) 0);
						}
						scheduleService.updateByPrimaryKeySelective(schedule);
					}
					else if("delete".equals(param.getType()))
					{
						//取消
						String key = ZQ_JSBF_SCHEDULE + id;
						if(jedisClient.existsObject(key))
						{
							schedule = (ZqSchedule) jedisClient.getObject(key);
						}
						schedule.setMatchstate((short) -10);
						scheduleService.updateByPrimaryKeySelective(schedule);
						jedisClient.setObject(key, schedule, 24*60*60);
					}
				}
			}
		}
		catch(Exception e)
		{
			logger.info("8小时内的赛程删除、比赛时间修改记录抓取ModifyRecordTask异常，异常：{}",e.getMessage());
		}
	}
}
