package com.dayspass.datacenter.handler.football;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.org.mozilla.javascript.internal.NativeArray;

import com.dayspass.datacenter.domain.ZqScore;
import com.dayspass.datacenter.grab.util.Grab;
import com.google.common.collect.Lists;

/**
 * 抓取联赛积分
 * @user zhangcb
 * @date 2016年1月22日
 */
@Component("grabForMatchScore")
public class GrabForMatchScore extends Grab<List<ZqScore>, Integer> {

	private static Logger logger = LoggerFactory.getLogger(GrabForMatchScore.class);
	
	@SuppressWarnings("restriction")
	public List<ZqScore> parse(String content, Integer leagueId) throws Exception {
		ScriptEngine engine = null;
		try {
			if(StringUtils.isBlank(content) || "错误的ID".equals(content)){
				return null;
			}
//			logger.info("联赛sclassid："+leagueId +"积分接口为:" +content);
			engine = new ScriptEngineManager().getEngineByName("javascript");
			engine.eval(content);
		} catch (ScriptException e) {
			logger.info("联赛sclassid："+leagueId +"积分抓取异常");
			e.printStackTrace();
			return null;
		}
		//1.当前赛季
		String seson = (String) engine.get("Season");
		//2.主场积分
		NativeArray home = (NativeArray)  engine.get("homeScore");
		List<ZqScore> list = Lists.newArrayList();
		if(home != null && home.size() > 0)
		{
			for(int i = 0; i<home.size();i++)
			{
				NativeArray array= (NativeArray) home.get(i,home);
				ZqScore score = new ZqScore();
				score.setSclassid(leagueId);
				score.setRank(parseDouble(array.get(0,array)).intValue());
				score.setTeamid(parseDouble(array.get(1,array)).intValue());
				score.setRedcard(parseDouble(array.get(2,array)).intValue());
				score.setWinScore(parseDouble(array.get(3,array)).intValue());
				score.setFlatScore(parseDouble(array.get(4,array)).intValue());
				score.setFailScore(parseDouble(array.get(5,array)).intValue());
				score.setTotalHomescore(parseDouble(array.get(6,array)).intValue());
				score.setTotalGuestscore(parseDouble(array.get(7,array)).intValue());
				score.setHomeorguest((byte) 1);
				score.setMatchseason(seson);
				score.setTotalScore(parseDouble(array.get(14,array)).intValue());
				list.add(score);
			}
		}
		
		//3.客场积分
		NativeArray guest = (NativeArray)  engine.get("guestScore");
		if(guest != null && guest.size() > 0)
		{
			for(int i = 0; i<guest.size();i++)
			{
				NativeArray array= (NativeArray) guest.get(i,guest);
				ZqScore score = new ZqScore();
				score.setSclassid(leagueId);
				score.setRank(parseDouble(array.get(0,array)).intValue());
				score.setTeamid(parseDouble(array.get(1,array)).intValue());
				score.setRedcard(parseDouble(array.get(2,array)).intValue());
				score.setWinScore(parseDouble(array.get(3,array)).intValue());
				score.setFlatScore(parseDouble(array.get(4,array)).intValue());
				score.setFailScore(parseDouble(array.get(5,array)).intValue());
				score.setTotalHomescore(parseDouble(array.get(6,array)).intValue());
				score.setTotalGuestscore(parseDouble(array.get(7,array)).intValue());
				score.setHomeorguest((byte) 0);
				score.setMatchseason(seson);
				score.setTotalScore(parseDouble(array.get(14,array)).intValue());
				list.add(score);
			}
		}
		//3.总积分
		NativeArray total = (NativeArray)  engine.get("totalScore");
		if(total != null && total.size() > 0)
		{
			for(int i = 0; i<total.size();i++)
			{
				NativeArray array= (NativeArray) total.get(i,total);
				ZqScore score = new ZqScore();
				score.setSclassid(leagueId);
				score.setRank(parseDouble(array.get(1,array)).intValue());
				score.setTeamid(parseDouble(array.get(2,array)).intValue());
				score.setRedcard(parseDouble(array.get(3,array)).intValue());
				score.setWinScore(parseDouble(array.get(5,array)).intValue());
				score.setFlatScore(parseDouble(array.get(6,array)).intValue());
				score.setFailScore(parseDouble(array.get(7,array)).intValue());
				score.setTotalHomescore(parseDouble(array.get(8,array)).intValue());
				score.setTotalGuestscore(parseDouble(array.get(9,array)).intValue());
				score.setHomeorguest((byte) 2);
				score.setMatchseason(seson);
				score.setTotalScore(parseDouble(array.get(16,array)).intValue());
				list.add(score);
			}
		}
		return list;
	}
}
