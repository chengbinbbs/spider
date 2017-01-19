package com.dayspass.datacenter.grab.handler;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.date.util.DateUtils;
import com.dayspass.datacenter.domain.Match;
import com.dayspass.datacenter.grab.tools.Grab;
import com.dayspass.datacenter.xml.util.DOMXmlUtil;

@Component("grabForChangeMatch")
public class GrabForChangeMatch extends Grab<List<Match>, Integer> {
	private static Logger logger = LoggerFactory.getLogger(GrabForChangeMatch.class);
	//private static MemCached memcache = MemCached.getInstance();

	@Override
	public List<Match> parse(String content, Integer p) {
		List<Match> list = new ArrayList<Match>();
		if(StringUtils.isEmpty(content)){
			return list;
		}
		try {
			List<Element> elements = DOMXmlUtil.fromtoXml(content);
			for(Element el : elements) {
				Match sche = new Match();
				String[] text = el.getText().split("\\^");
				sche.setScheduleId(parseInt(text[0]));
				//if(!memcache.contains(ScheduleUtil.KEYSCHDULE + sche.getScheduleId())) {
	 			//	continue;
				//}
				sche.setMatchState(parseShort(text[1]));
				if(!StringUtils.isEmpty(text[2]))
				{
					sche.setHomeScore(parseShort(text[2]));
				}
				if(!StringUtils.isEmpty(text[3]))
				{
					sche.setGuestScore(parseShort(text[3]));
				}
				if(!StringUtils.isEmpty(text[4]))
				{
					sche.setHomeHalfScore(parseShort(text[4]));
				}
				if(!StringUtils.isEmpty(text[5]))
				{
					sche.setGuestHalfScore(parseShort(text[5]));
				}
				if(!StringUtils.isEmpty(text[6]))
				{
					sche.setHomeRed(parseShort(text[6]));
				}
				if(!StringUtils.isEmpty(text[7]))
				{
					sche.setGuestRed(parseShort(text[7]));
				}
				if(!StringUtils.isEmpty(text[9])){
					sche.setMatchBeginTime(DateUtils.getDateTime(text[9]));
				}
				if(!StringUtils.isEmpty(text[12]))
				{
					sche.setHomeYellow(parseShort(text[12]));
				}
				if(!StringUtils.isEmpty(text[13]))
				{
					sche.setGuestYellow(parseShort(text[13]));
				}
				list.add(sche);
			}
		} catch (Exception e) {
			logger.error("GrabForChangeMatch(parse)处理数据异常", e);
		}
		return list;
	}

	
}
