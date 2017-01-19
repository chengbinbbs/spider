package com.dayspass.datacenter.task.football.match;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.ZqTechStaticsBody;
import com.dayspass.datacenter.bean.ZqTechStaticsParam;
import com.dayspass.datacenter.domain.ZqTechnicStatist;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.service.football.event.ZqTechnicStatistService;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;


/**
 * 比赛技术统计
 * @user zhangcb
 * @date 2016年1月25日
 */
@Component("technicStatistTask")
public class TechnicStatistTask {
	
	private static Logger logger = LoggerFactory.getLogger(TechnicStatistTask.class);
	
	@Autowired
	private ZqTechnicStatistService technicStatistService;
	
	@Value("${zq.matchtechnicUrl}")
	private String techUrl;	//当天完整统计数统计
	
	private static final String[] text = {"先开球", "第一个角球", "第一张黄牌", "射门次数", "射正次数", 
			"犯规次数", "角球次数", "角球次数(加时)", "任意球次数", "越位次数", "乌龙球数", "黄牌数", "黄牌数(加时)",
			"红牌数", "控球时间", "头球", "救球", "守门员出击", "丟球", "成功抢断", "阻截", "长传", "短传", "助攻", 
			"成功传中", "第一个换人", "最后换人", "第一个越位", "最后越位", "换人数", "最后角球", "最后黄牌", "换人数(加时)",
			"越位次数(加时)", "射门不中", "中柱", "头球成功次数", "射门被挡", "铲球", "过人次数", "界外球", "传球次数", "传球成功次数"};
	
	public void spiderTechStatics()
	{
		try
		{
			String reqXml = HttpClientUtil.callHttpGet(techUrl,"utf-8");
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+techUrl+"<-url source tech data is empty.");
				return; 
			}
			
			ZqTechStaticsBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqTechStaticsBody.class);
			if(reqBody == null || reqBody.getMatch()== null || reqBody.getMatch().size() == 0) {
				logger.info("httpclient-> No ongoing football game.");
				return;
			}
			for(ZqTechStaticsParam param:reqBody.getMatch())
			{
				int ID = param.getId();
				ZqTechnicStatist tech = technicStatistService.selectByScheduleId(ID);
				if(tech != null)
				{
					tech.setTechniccount(param.getTechnicCount());
					technicStatistService.updateByPrimaryKeySelective(tech);
				}
				else
				{
					tech = new ZqTechnicStatist();
					tech.setScheduleid(ID);
					tech.setTechniccount(param.getTechnicCount());
					technicStatistService.insertSelective(tech);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("定时任务->技术统计TechnicStatistTask异常，异常信息:{}",e.getMessage());
		}
	}
	
}
