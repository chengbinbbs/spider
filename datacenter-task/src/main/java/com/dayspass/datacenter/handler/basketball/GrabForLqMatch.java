package com.dayspass.datacenter.handler.basketball;

import java.util.List;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.date.util.DateUtils;
import com.dayspass.datacenter.domain.LqSchedule;
import com.dayspass.datacenter.grab.util.Grab;
import com.dayspass.datacenter.xml.util.DOMXmlUtil;
import com.google.common.collect.Lists;

/**
 * 抓取篮球比分赛程
 * @author zhangcb
 *
 */
@Component("grabForLqMatch")
public class GrabForLqMatch extends Grab<List<LqSchedule>, Integer> {
	private static Logger logger = LoggerFactory.getLogger(GrabForLqMatch.class);

	@Override
	public List<LqSchedule> parse(String content, Integer p) {
		List<LqSchedule> list = Lists.newArrayList();
		if(StringUtils.isEmpty(content)){
			return list;
		}
		try {
			List<Element> elements = DOMXmlUtil.fromtoXml(content);
			for(Element el : elements) {
				LqSchedule lq = new LqSchedule();
				String[] text = el.getText().split("\\^");
				lq.setSid(Integer.parseInt(text[0]));
				lq.setSclassid(Short.parseShort(text[1]));
				lq.setMatchkind(Short.parseShort(text[2]));
				
				lq.setMatchtime(DateUtils.parseDate(text[6], "yyyy-MM-dd HH:mm:ss"));
				lq.setMatchstate(Short.parseShort(text[7]));
				lq.setRemaintime(text[8]);
				lq.setHometeamid(Integer.parseInt(text[9]));
				lq.setHometeam(text[10].split(",")[0]);
				
				lq.setGuestteamid(Integer.parseInt(text[11]));
				lq.setGuestteam(text[12].split(",")[0]);
				
				lq.setHomeOrder(text[13]);
				lq.setGuestOrder(text[14]);
				
				lq.setHomescore(Short.parseShort(text[15]));
				lq.setGuestscore(Short.parseShort(text[16]));
				
				lq.setHomeone(Short.parseShort(text[17]));
				lq.setGuestone(Short.parseShort(text[18]));
				lq.setHometwo(Short.parseShort(text[19]));
				lq.setGuesttwo(Short.parseShort(text[20]));
				lq.setHomethree(Short.parseShort(text[21]));
				lq.setGuestthree(Short.parseShort(text[22]));
				lq.setHomefour(Short.parseShort(text[23]));
				lq.setGuestfour(Short.parseShort(text[24]));
				
				Short addtime = Short.parseShort(text[25]);
				lq.setAddtime(addtime);
				if(addtime == 1)
				{
					lq.setHomeaddtime1(Short.parseShort(text[26]));
					lq.setGuestaddtime1(Short.parseShort(text[27]));
				}
				else if(addtime == 2)
				{
					lq.setHomeaddtime1(Short.parseShort(text[26]));
					lq.setGuestaddtime1(Short.parseShort(text[27]));
					lq.setHomeaddtime2(Short.parseShort(text[28]));
					lq.setGuestaddtime2(Short.parseShort(text[29]));
				}
				else if(addtime == 3)
				{
					lq.setHomeaddtime1(Short.parseShort(text[26]));
					lq.setGuestaddtime1(Short.parseShort(text[27]));
					lq.setHomeaddtime2(Short.parseShort(text[28]));
					lq.setGuestaddtime2(Short.parseShort(text[29]));
					lq.setHomeaddtime3(Short.parseShort(text[30]));
					lq.setGuestaddtime3(Short.parseShort(text[31]));
				}
				
				lq.setAddtechnic(Boolean.valueOf(text[32]));
				lq.setTv(text[33]);
				lq.setExplain(text[34]);
				lq.setIsneutral(Boolean.parseBoolean(text[35]));
				lq.setMatchseason(text[36].replace("赛季", ""));
				
				lq.setLocation(text[38]);
				lq.setRoundtype1(text[39]);
				lq.setRoundtype2(text[40]);
				
				list.add(lq);
			}
		} catch (Exception e) {
			logger.error("GrabForChangeMatch(parse)处理数据异常", e);
		}
		return list;
	}
	
	/**
	 * js时间格式格式化
	 * @author zhangcb
	 * @param time
	 * @return
	 */
	public static String getTime(String time){
    	if(!StringUtils.isEmpty(time)){
    		String [] temp=time.split(",");
    		int M = Integer.valueOf(temp[1]) + 1;
    		int d = Integer.valueOf(temp[2]);
    		int h = Integer.valueOf(temp[3]);
    		int m =Integer.valueOf(temp[4]);
    		int s=Integer.valueOf(temp[5]);
    		String year =  temp[0] ;
    		String month = M < 10? "0" + M:M+"";
    		String day = d<10?"0"+d:d+"";
    		String hour=h<10?"0"+h:h+"";
    		String mi=m<10?"0"+m:m+"";
    		String ss=s<10?"0"+s:s+"";
    		return year+"-"+month+"-"+day+" "+hour+":"+mi+":"+ss;
    	}
    	return null;
    }
}
