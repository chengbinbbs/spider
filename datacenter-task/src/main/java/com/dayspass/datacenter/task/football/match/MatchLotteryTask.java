package com.dayspass.datacenter.task.football.match;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.bean.ZqMatchInfoBody;
import com.dayspass.datacenter.bean.ZqMatchInfoParam;
import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.domain.ZqMatchLottery;
import com.dayspass.datacenter.domain.ZqStage;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.dayspass.datacenter.service.football.match.MatchLotteryService;
import com.dayspass.datacenter.service.football.match.ZqStageService;
import com.dayspass.datacenter.xml.util.BeanXmlUtil;
import com.google.common.collect.Maps;

@Component("matchLotteryTask")
public class MatchLotteryTask {
	
	private static Logger logger = LoggerFactory.getLogger(MatchLotteryTask.class);
	
	@Autowired
	private ZqStageService stageService;
	@Autowired
	private MatchLotteryService matchLotteryService;
	
	@Value("${zq.matchinfoUrl}")
	private String matchsUrl;	//彩票赛事与球探网的关联表
	@Autowired
	private JedisClient jedisClient;
	//彩种对应类型，0：完整赛事，1：竞彩，2：北单，3：足彩
	private static String[] lotteryType = {"完整赛事","竞彩足球","单场让球胜平负","14场胜负彩","竞彩篮球"};
	
	//当天比赛对阵缓存的key
	@Value("${ZQ_JC_MATCH_LIST}")
	private String ZQ_JC_MATCH_LIST;
	@Value("${ZQ_BD_MATCH_LIST}")
	private String ZQ_BD_MATCH_LIST;
	@Value("${ZQ_ZC_MATCH_LIST}")
	private String ZQ_ZC_MATCH_LIST;
	@Value("${LQ_JC_MATCH_LIST}")
	private String LQ_JC_MATCH_LIST;
	
	public void handlerMatchLottry()
	{
		try
		{
			logger.info(this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+"() started!");
			
			Map<String, String> header = Maps.newHashMap();
			header.put("Host","www.310win.com");  
			header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");  
			String reqXml = HttpClientUtil.callHttpGet(matchsUrl,"UTF-8", header);
			if(StringUtils.isEmpty(reqXml)) {
				logger.info("httpclient-> "+matchsUrl+"<-url source matchs data is empty.");
				return; 
			}
			reqXml = reqXml.replace(">True<", ">true<").replace(">False<", ">false<");
			ZqMatchInfoBody reqBody = BeanXmlUtil.fromXml(reqXml, ZqMatchInfoBody.class);
			if(reqBody == null || reqBody.getMatch()== null || reqBody.getMatch().size() == 0) {
				logger.info("httpclient-> No ongoing football game.");
				return;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(ZqMatchInfoParam param:reqBody.getMatch())
			{
				//获取彩种类型
				int type = getLotteryType(param.getLotteryName());
				//过滤掉非足球赛事
				if(type == -1 || (!StringUtils.isEmpty(param.getSport()) && !"足球".equals(param.getSport())))
				{
					continue;
				}
				//获取彩种期次
				String qc = getLotteryStage(param);
				Map<String,Object> map = Maps.newHashMap();
				map.put("type", type);
				map.put("name", qc);
				List<ZqStage> list = stageService.queryAll(map);
				
				int stageId = 0;  //期次id
				if(list != null && list.size() > 0)
				{
					ZqStage stage = list.get(0);
					stageId = stage.getId();
				}
				else
				{
					ZqStage stage = new ZqStage();
					stage.setName(qc);
					stage.setType(type);
					stage.setCreatetime(new Date());
					stageService.insertSelective(stage);
					stageId = stage.getId();
				}
				Map<String,Object> matchMap = Maps.newHashMap();
				matchMap.put("sort", param.getID());
//				matchMap.put("scheduleid", param.getID_bet007());
				matchMap.put("stageid", stageId);
				//查询赛事是否存在
				List<ZqMatchLottery> matchList = matchLotteryService.queryAll(matchMap);
				if(matchList != null && matchList.size() > 0)
				{
					ZqMatchLottery match = matchList.get(0);
					match.setScheduleid(param.getID_bet007());
					match.setStageid(stageId);
					match.setLotteryname(param.getLotteryName());
					match.setSort(param.getID());
					match.setMtime(sdf.parse(param.getTime().trim().replace("/", "-")));
					match.setHometeamid(param.getHomeID());
					match.setHometeamname(param.getHome());
					match.setGuestteamid(param.getAwayID());
					match.setGuestteamname(param.getAway());
					match.setSwapteam(param.isTurn());
					match.setUpdatetime(new Date());
					matchLotteryService.updateByPrimaryKeySelective(match);
				}
				else
				{
					ZqMatchLottery match = new ZqMatchLottery();
					match.setScheduleid(param.getID_bet007());
					match.setStageid(stageId);
					match.setLotteryname(param.getLotteryName());
					match.setSort(param.getID());
					match.setMtime(sdf.parse(param.getTime().trim().replace("/", "-")));
					match.setHometeamid(param.getHomeID());
					match.setHometeamname(param.getHome());
					match.setGuestteamid(param.getAwayID());
					match.setGuestteamname(param.getAway());
					match.setSwapteam(param.isTurn());
					match.setCreatetime(new Date());
					matchLotteryService.insertSelective(match);
				}
				if(type == 1)
				{
					String jckey = ZQ_JC_MATCH_LIST + qc;
					jedisClient.delkeyObject(jckey);
				}
				else if(type == 2)
				{
					String bdkey = ZQ_BD_MATCH_LIST + qc;
					jedisClient.delkeyObject(bdkey);
				}
				else if(type == 3)
				{
					String zckey = ZQ_ZC_MATCH_LIST + qc;
					jedisClient.delkeyObject(zckey);
				}
				else if(type == 4)
				{
					String jckey = LQ_JC_MATCH_LIST + qc;
					jedisClient.delkeyObject(jckey);
				}
			}
			logger.info(this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+"() done!");
		}
		catch(Exception e)
		{
			logger.info("彩票赛事与球探网的关联表定时任务抓取MatchInfoTask异常，异常：{}",e.getMessage());
		}
	}
	
	private String getLotteryStage(ZqMatchInfoParam param) throws ParseException {
		
		String lotteryName = param.getLotteryName().trim();
		//竞彩赛事没有期次，需要自定义期次，规则：12点到次日12点之间的赛事，期次为当天日期格式化yyyyMMdd
		if("竞彩足球".equals(lotteryName) || "竞彩篮球".equals(lotteryName))
		{
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
		else
		{
			return param.getIssueNum().trim();
		}
	}
	/**
	 * 根据彩种名称获取彩种类型
	 * @param name
	 * @return
	 */
	public int getLotteryType(String name)
	{
		for(int i = 0; i< lotteryType.length; i++)
		{
			if(lotteryType[i].equals(name.trim()))
			{
				return i;
			}
		}
		return -1;
	}
}
