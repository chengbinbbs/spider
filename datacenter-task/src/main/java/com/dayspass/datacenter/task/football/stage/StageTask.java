package com.dayspass.datacenter.task.football.stage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.BdStageBody;
import com.dayspass.datacenter.bean.BdStageParam;
import com.dayspass.datacenter.bean.ZcStageBody;
import com.dayspass.datacenter.bean.ZcStageParam;
import com.dayspass.datacenter.domain.ZqStage;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.service.football.match.ZqStageService;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;
import com.google.common.collect.Maps;


/**
 * 北单、足彩期次管理
 * @user zhangcb
 * @date 2016年2月3日
 */
@Component("stageTask")
public class StageTask {
	private static Logger logger = LoggerFactory.getLogger(StageTask.class);
	@Autowired
	private ZqStageService stageService;
	
	@Value("${9188.bdstageUrl}")
	private String bdUrl;	//9188北单期次列表
	@Value("${9188.zcstageUrl}")
	private String zcUrl;	//9188足彩期次列表
	
	/**
	 * 竞彩期次管理
	 * @author zhangcb
	 */
	public void spiderJcStage(){
		try
		{
			Map<String,Object> params = Maps.newHashMap();
			params.put("type", 1);
			params.put("isCurrent", 0);
			stageService.updateCurrentStage(params);
			
			DateTime today = new DateTime();
			if(Integer.parseInt(today.toString("HHmm")) < 1130) {
				today = today.minusDays(1);
			}
			
			Map<String,Object> map = Maps.newHashMap();
			map.put("type", 1);
			map.put("name", today.toString("yyyyMMdd"));
			map.put("isCurrent", 1);
			stageService.updateCurrentStage(map);
		}
		catch(Exception e)
		{
			logger.info("更新竞彩当前期次定时任务spiderJcStage异常",e.getMessage());
		}
	}
	/***
	 * 北单期次管理
	 * @author zhangcb
	 */
	public void spiderBdStage(){
		try
		{
			String reqXml = HttpClientUtil.callHttpGet(bdUrl, "UTF-8");
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+bdUrl+"<-url source bd stage data is empty.");
				return; 
			}
			
			BdStageBody reqBody = BeanXmlUtil.fromXml(reqXml, BdStageBody.class);
			if(reqBody == null || reqBody.getRow()== null || reqBody.getRow().size() == 0) {
				logger.info("httpclient-> No ongoing stage game.");
				return;
			}
			Map<String,Object> params = Maps.newHashMap();
			params.put("type", 2);
			params.put("isCurrent", 0);
			stageService.updateCurrentStage(params);
			
			List<BdStageParam> bdList = reqBody.getRow();
			for(int i = 0; i<bdList.size(); i++)
			{
				BdStageParam param = bdList.get(i);
				String name = param.getPid();
				int flag = param.getFlag();
				if(i == 0)
				{
					flag = 1;
				}
				Map<String,Object> map = Maps.newHashMap();
				map.put("type", 2);
				map.put("name", name);
				List<ZqStage> list = stageService.queryAll(map);
				if(list == null || list.isEmpty())
				{
					ZqStage stage = new ZqStage();
					stage.setName(name);
					stage.setType(2);
					stage.setIsCurrent(flag == 1?true:false);
					stage.setCreatetime(new Date());
					stageService.insertSelective(stage);
				}
				else
				{
					ZqStage stage = list.get(0);
					stage.setIsCurrent(flag == 1?true:false);
					stage.setUpdatetime(new Date());
					stageService.updateByPrimaryKeySelective(stage);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("更新北单当前期次定时任务spiderBdStage异常",e.getMessage());
		}
	}
	/**
	 * 足彩期次管理
	 * @author zhangcb
	 */
	public void spiderZcStage(){
		try
		{
			String reqXml = HttpClientUtil.callHttpGet(zcUrl,"utf-8");
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+zcUrl+"<-url source zc stage data is empty.");
				return; 
			}
			
			ZcStageBody reqBody = BeanXmlUtil.fromXml(reqXml, ZcStageBody.class);
			if(reqBody == null || reqBody.getRow()== null || reqBody.getRow().size() == 0) {
				logger.info("httpclient-> No ongoing stage game.");
				return;
			}
			//更新当前期次，将当前期次置为0
			Map<String,Object> params = Maps.newHashMap();
			params.put("type", 3);
			params.put("isCurrent", 0);
			stageService.updateCurrentStage(params);
			
			String curname = reqBody.getRow().get(0).getPid();	//默认第一条为当前期
			for(ZcStageParam param:reqBody.getRow())
			{
				String name = param.getPid();
				Map<String,Object> map = Maps.newHashMap();
				map.put("type", 3);
				map.put("name", name);
				List<ZqStage> list = stageService.queryAll(map);
				
				boolean flag = false;
				if(curname.equals(name))
				{
					flag = true;
				}
				if(list == null || list.isEmpty())
				{
					ZqStage stage = new ZqStage();
					stage.setName(name);
					stage.setType(3);
					stage.setIsCurrent(flag);
					stage.setCreatetime(new Date());
					stageService.insertSelective(stage);
				}
				else
				{
					ZqStage stage = list.get(0);
					stage.setIsCurrent(flag);
					stage.setUpdatetime(new Date());
					stageService.updateByPrimaryKeySelective(stage);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("更新足彩当前期次定时任务spiderZcStage异常",e.getMessage());
		}
	}
}
