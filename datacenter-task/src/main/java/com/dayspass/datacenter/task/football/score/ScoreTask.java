package com.dayspass.datacenter.task.football.score;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.domain.ZqSclass;
import com.dayspass.datacenter.domain.ZqScore;
import com.dayspass.datacenter.handler.football.GrabForMatchScore;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.service.football.score.ZqScoreService;
import com.dayspass.datacenter.task.football.league.CupMatchTask;
import com.google.common.collect.Maps;


/**
 * 抓取进两天的联赛积分
 * @user zhangcb
 * @date 2016年1月28日
 */
@Component("scoreTask")
public class ScoreTask {
	
	private static Logger logger = LoggerFactory.getLogger(ScoreTask.class);
	@Autowired
	private ZqScoreService scoreService;
	@Autowired
	private GrabForMatchScore grabForMatchScore;
	@Autowired
	private CupMatchTask cupMatchTask;
	
	@Value("${zq.leaguescoreUrl}")
	private String scoreUrl;	//联赛积分接口
	
	/**
	 * 抓取某个联赛的积分
	 * @author zhangcb
	 * @param sclassid
	 */
	public void spiderForSclassScore(ZqSclass sclass){
		try
		{
			if(sclass == null)
			{
				return;
			}
			int sclassid = sclass.getLeagueId();
			if(sclass.getKind() == 1)
			{
				//1.联赛积分抓取
				String url = scoreUrl + sclassid;
				String reqXml = HttpClientUtil.callHttpGet(url,"utf-8");
				if(StringUtils.isBlank(reqXml) || "错误的ID".equals(reqXml)) {
					return;
				}
				else if(reqXml.indexOf("sub:") != -1 || sclass.getSubsclassid() != null)
				{
					//有子联赛
					String[] subs = reqXml.replace("sub:", "").split("\\,");
					Integer subID = sclass.getSubsclassid();
					if(subs.length > 0)
					{
						if(subs.length == 1)
						{
							subID = Integer.parseInt(subs[0].replace("(current)", ""));
						}
						else
						{
							for(int i = 0; i < subs.length; i++)
							{
								if(subs[i].indexOf("(current)") != -1)
								{
									subID = Integer.parseInt(subs[i].replace("(current)", ""));
									break;
								}
							}
						}
						doScoreTask(url + "&subID=" + subID, sclassid, subID);
					}
				}
				else
				{
					//没有子联赛
					doScoreTask(url, sclassid, null);
				}
			}
			else
			{
				//2.杯赛积分抓取
				cupMatchTask.spiderForCupMatch(sclass);
			}
		}
		catch(Exception e)
		{
			logger.info("联赛积分定时任务抓取异常，sclassid：" +sclass.getLeagueId(),e.getMessage());
		}
	}
	
	/**
	 * 联赛积分抓取
	 * @author zhangcb
	 * @param url
	 * @param sclassid
	 * @param subsclassid
	 */
	public void doScoreTask(String url, int sclassid,Integer subsclassid){
		try
		{	
			List<ZqScore> list = grabForMatchScore.collect(url, sclassid, "utf-8");
			if(list != null && list.size() > 0)
			{
				for(ZqScore score:list)
				{
					Map<String,Object> param = Maps.newHashMap();
					if(subsclassid != null)
					{
						score.setSubsclassid(subsclassid);
						param.put("subsclassid", subsclassid);
					}
					param.put("teamid", score.getTeamid());
					param.put("sclassid", score.getSclassid());
					param.put("homeorguest", score.getHomeorguest());
					param.put("matchseason", score.getMatchseason());
					//3.查询是否存在记录
					List<ZqScore> scores = scoreService.queryAll(param);
					if(scores !=null && scores.size() > 0)
					{
						ZqScore newScore = scores.get(0);
						newScore.setRank(score.getRank());
						newScore.setWinScore(score.getWinScore());
						newScore.setFlatScore(score.getFlatScore());
						newScore.setFailScore(score.getFailScore());
						newScore.setTotalHomescore(score.getTotalHomescore());
						newScore.setTotalGuestscore(score.getTotalGuestscore());
						newScore.setTotalScore(score.getTotalScore());
						newScore.setRedcard(score.getRedcard());
						newScore.setMatchseason(score.getMatchseason());
						newScore.setSubsclassid(score.getSubsclassid());
						scoreService.updateByPrimaryKeySelective(newScore);
					}
					else
					{
						scoreService.insertSelective(score);
					}
				}
			}
		}
		catch(Exception e)
		{
			logger.info("联赛积分抓取异常，sclassid：" +sclassid + ",subsclassid" + subsclassid,e.getMessage());
		}
	}
}
