package com.dayspass.datacenter.grab.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.ZqMatchInfoBody;
import com.dayspass.datacenter.bean.ZqMatchInfoParam;
import com.dayspass.datacenter.domain.BasketBallMatch;
import com.dayspass.datacenter.domain.FootBallMatch;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.service.match.BasketBallMatchService;
import com.dayspass.datacenter.service.match.FootBallMatchService;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;



/**
 * 彩票赛事与球探网的关联表
 * @user zhangcb
 * @date 2016年1月25日
 */
@Component("matchInfoTask")
public class MatchInfoTask {
	private static Logger logger = LoggerFactory.getLogger(MatchInfoTask.class);
	
	@Autowired
	private FootBallMatchService footBallMatchService;
	@Autowired
	private BasketBallMatchService basketBallMatchService;
	
	@Value("${qiutan.matchinfo.url}")
	private String matchsUrl;	//彩票赛事与球探网的关联表
		
	public void handlerMatchLottry()
	{
		try
		{
			String reqXml = HttpClientUtil.callHttpGet(matchsUrl,null);
			if(StringUtils.isBlank(reqXml)) {
				logger.info("httpclient-> "+matchsUrl+"<-url source matchs data is empty.");
				return; 
			}
			reqXml = reqXml.replace(">True<", ">true<").replace(">False<", ">false<");
			ZqMatchInfoBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqMatchInfoBody.class);
			if(reqBody == null || reqBody.getMatch()== null || reqBody.getMatch().size() == 0) {
				logger.info("httpclient-> No ongoing football game.");
				return;
			}
			for(ZqMatchInfoParam param:reqBody.getMatch())
			{
				//获取彩种类型
				String lotteryName = param.getLotteryName();
				//过滤掉非竞彩赛事
				if(lotteryName.indexOf("竞彩") == -1)
				{
					continue;
				}
				//获取彩种期次
				String issue = getLotteryStage(param);
				String teamid = param.getID().substring(2, 5);
				String matchCode = issue + teamid;
				//查询赛事是否存在
				if("竞彩足球".equals(lotteryName)){
					FootBallMatch match = footBallMatchService.findMatchByMatchCode(matchCode);
					if(null == match || (match.getGuestTeamId() != null && match.getHostTeamId() != null)){
						continue;
					}
					match.setHostTeamId(param.getHomeID());
					match.setGuestTeamId(param.getAwayID());
					footBallMatchService.updateByPrimaryKeySelective(match);
				}
				else if("竞彩篮球".equals(lotteryName))
				{
					BasketBallMatch match = basketBallMatchService.findMatchByMatchCode(matchCode);
					if(null == match || (match.getGuestTeamId() != null && match.getHostTeamId() != null)){
						continue;
					}
					match.setHostTeamId(param.getHomeID());
					match.setGuestTeamId(param.getAwayID());
					basketBallMatchService.updateByPrimaryKeySelective(match);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("彩票赛事与球探网的关联表定时任务抓取MatchInfoTask异常，异常：{}",e.getMessage());
		}
	}
	
	private String getLotteryStage(ZqMatchInfoParam param) throws ParseException {
		
		String lotteryName = param.getLotteryName().trim();
		//竞彩赛事没有期次，需要自定义期次，规则：12点到次日12点之间的赛事，期次为当天日期格式化yyyyMMdd
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date mtime = sdf.parse(param.getTime().trim().replace("/", "-"));   //开赛时间
		Date separateTime = sdf.parse(new DateTime(mtime).toString("yyyy-MM-dd 11:59:59")); //竞彩时间分隔点中午12点
		if(mtime.getTime() > separateTime.getTime())
		{
			return new DateTime(mtime).toString("yyyyMMdd");
		}
		else
		{
			return new DateTime(mtime).minusDays(1).toString("yyyyMMdd");
		}
	}
}
