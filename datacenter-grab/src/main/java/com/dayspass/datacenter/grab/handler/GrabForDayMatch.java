package com.dayspass.datacenter.grab.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.date.util.DateUtils;
import com.dayspass.datacenter.domain.Match;
import com.dayspass.datacenter.grab.tools.Grab;

import sun.org.mozilla.javascript.internal.NativeArray;

@Component("grabForDayMatch")
public class GrabForDayMatch extends Grab<List<Match>, Map<String, String>> {
	private static Logger logger = LoggerFactory.getLogger(GrabForDayMatch.class);
	
	@Override
	public List<Match> parse(String content, Map<String, String> map) {
		List<Match> list = new ArrayList<Match>();
		if(StringUtils.isEmpty(content)){
			return list;
		}
		try {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
			if(content.indexOf("ShowBf") <= 0) {
				return list;
			}
			engine.eval(content.substring(0, content.indexOf("ShowBf")));
			NativeArray arrA = (NativeArray) engine.get("A");
			for(Object id : arrA.getIds()) {
				NativeArray array = (NativeArray) arrA.get((int) id);
				if(map.size() > 0 && !map.containsKey(parseString(array.get(45)))) {//赛事过滤
	 				continue;
				}
				Match sche = new Match();
				sche.setScheduleId(parseInt(array.get(0)));
				sche.setSclassId(parseInt(array.get(45)));
				sche.setLeague(parseString(array.get(2)));
				sche.setMatchSeason(parseString(array.get(43)));
				sche.setHomeTeamId(parseInt(array.get(37)));
				sche.setGuestTeamId(parseInt(array.get(38)));
				String homeTeam = parseString(array.get(5));
				if(homeTeam.indexOf(">(中)") > -1){
					sche.setNeutrality(true);
					sche.setHomeTeam(homeTeam.substring(0, homeTeam.indexOf("<")));
				} else {
					sche.setHomeTeam(homeTeam);
				}
				String guestTeam = parseString(array.get(8));
				if(guestTeam.indexOf(">(中)") > -1){
					sche.setNeutrality(true);
					sche.setGuestTeam(guestTeam.substring(0, guestTeam.indexOf("<")));
				} else {
					sche.setGuestTeam(guestTeam);
				}
				sche.setMatchState(parseShort(array.get(13)));
				sche.setHomeScore(parseShort(array.get(14)));
				sche.setGuestScore(parseShort(array.get(15)));
				sche.setHomeHalfScore(parseShort(array.get(16)));
				sche.setGuestHalfScore(parseShort(array.get(17)));
				sche.setHomeRed(parseShort(array.get(18)));
				sche.setGuestRed(parseShort(array.get(19)));
				sche.setHomeYellow(parseShort(array.get(20)));
				sche.setGuestYellow(parseShort(array.get(21)));
				sche.setHomeOrder(parseString(array.get(22)));
				sche.setGuestOrder(parseString(array.get(23)));
				sche.setMatchTime(DateUtils.parseDate(parseString(array.get(43)) + "-" + parseString(array.get(36)) + " " + parseString(array.get(11)) + ":00", DateUtils.DATETIME_FORMAT));
				sche.setMatchBeginTime(parseStringDate(array.get(12)));
				String remark = parseString(array.get(42));
				if(!StringUtils.isEmpty(remark) && remark.indexOf("90") > 0) {
					sche.setRemark(remark.substring(remark.indexOf("90"), remark.length()));
				}
				sche.setFlag(0);
				list.add(sche);
			}
		} catch (Exception e) {
			logger.error("GrabForDayMatch(parse)处理数据异常", e);
		}
		return list;
	}

	
}
