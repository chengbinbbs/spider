package com.dayspass.datacenter.task.football.league;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.ZqSubLeagueBody;
import com.dayspass.datacenter.bean.ZqSubLeagueParam;
import com.dayspass.datacenter.domain.ZqSclass;
import com.dayspass.datacenter.domain.ZqSubSclass;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.service.football.league.ZqSclassService;
import com.dayspass.datacenter.service.football.league.ZqSubSclassService;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;




/**
 * 子联赛接口
 * @user zhangcb
 * @date 2016年4月13日
 */
@Component("subLeagueTask")
public class SubLeagueTask {
	
	private static Logger logger = LoggerFactory.getLogger(SubLeagueTask.class);
	
	@Value("${zq.subleagueUrl}")
	private String url;
	
	@Autowired
	private ZqSubSclassService subSclassService;
	@Autowired
	private ZqSclassService sclassService;
	
	public void spiderLeague()
	{
		try
		{
			String reqXml = HttpClientUtil.callHttpGet(url,"utf-8");
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+url+"<-url source subleague data is empty.");
				return; 
			}
			reqXml = reqXml.replace(">True<", ">true<").replace(">False<", ">false<");
			ZqSubLeagueBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqSubLeagueBody.class);
			if(reqBody == null || reqBody.getLeague()== null || reqBody.getLeague().size() == 0) {
				logger.info("httpclient-> No ongoing football game.");
				return;
			}
			for(ZqSubLeagueParam param:reqBody.getLeague())
			{
				//查询联赛是否存在
				int sclassid = param.getId();
				int subsclassid = param.getSubID();
				ZqSubSclass sc = subSclassService.selectByPrimaryKey(subsclassid);
				if(sc != null)
				{
					sc.setSubsclassname(param.getName());
					sc.setSortnumber(param.getNum());
					sc.setCountRound(param.getSum_round());
					sc.setCurrRound(param.getCurr_round());
					sc.setIshavescore(param.getIsHaveScore());
					sc.setIncludeseason(param.getIncludeSeason());
					sc.setIscurrentsclass(param.getIsCurrentSclass());
					sc.setCurrentseason(param.getCurrentSeason());
					subSclassService.updateByPrimaryKeySelective(sc);
				}
				else
				{
					sc = new ZqSubSclass();
					sc.setSubsclassid(subsclassid);
					sc.setSclassid(sclassid);
					sc.setSubsclassname(param.getName());
					sc.setSortnumber(param.getNum());
					sc.setCountRound(param.getSum_round());
					sc.setCurrRound(param.getCurr_round());
					sc.setIshavescore(param.getIsHaveScore());
					sc.setIncludeseason(param.getIncludeSeason());
					sc.setIscurrentsclass(param.getIsCurrentSclass());
					sc.setCurrentseason(param.getCurrentSeason());
					sc.setIsanalyscore(true);
					sc.setSubsclassF(param.getName());
					sc.setIszu(true);
					subSclassService.insertSelective(sc);
				}
				//更新当前子联赛
				if(sc.getIscurrentsclass())
				{
					ZqSclass sclass = new ZqSclass();
					sclass.setLeagueId(sclassid);
					sclass.setSubsclassid(subsclassid);
					sclassService.updateByPrimaryKeySelective(sclass);
				}
				logger.info("联赛sclass："+sclassid + "子联赛subsclassid:" +subsclassid +"抓取完毕");
			}
		}
		catch(Exception e)
		{
			logger.info("子联赛定时任务抓取SubLeagueTask异常，异常：{}",e.getMessage());
		}
	}
}
