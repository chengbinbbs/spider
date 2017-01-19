package com.dayspass.datacenter.handler.football;

import java.util.List;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.command.ChangeRoundCommand;
import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.grab.util.Grab;
import com.dayspass.datacenter.xml.util.DOMXmlUtil;
import com.google.common.collect.Lists;

@Component("grabForChangeMatch")
public class GrabForChangeMatch extends Grab<List<ChangeRoundCommand>, Integer> {
	private static Logger logger = LoggerFactory.getLogger(GrabForChangeMatch.class);

	@Override
	public List<ChangeRoundCommand> parse(String content, Integer p) {
		List<ChangeRoundCommand> list = Lists.newArrayList();
		if(StringUtils.isEmpty(content)){
			return list;
		}
		try {
			List<Element> elements = DOMXmlUtil.fromtoXml(content);
			for(Element el : elements) {
				ChangeRoundCommand crc = new ChangeRoundCommand();
				String[] text = el.getText().split("\\^");
				crc.setSid(Integer.parseInt(text[0]));
				crc.setMatchstate(Short.parseShort(text[1]));
				if(!StringUtils.isEmpty(text[2]))
				{
					crc.setHomescore(Short.parseShort(text[2]));
				}
				if(!StringUtils.isEmpty(text[3]))
				{
					crc.setGuestscore(Short.parseShort(text[3]));
				}
				if(!StringUtils.isEmpty(text[6]))
				{
					crc.setHomeRed(Short.parseShort(text[6]));
				}
				if(!StringUtils.isEmpty(text[7]))
				{
					crc.setGuestRed(Short.parseShort(text[7]));
				}
				if(StringUtils.isEmpty(text[4]) && StringUtils.isEmpty(text[5])){
					crc.setHalf("");
				}else{
					crc.setHalf(text[4]+"-"+text[5]);
				}
				String jstime = text[9];
				if(!StringUtils.isEmpty(jstime)){
					crc.setMatchtime(getTime(jstime));
				}else{
					crc.setMatchtime("");
				}
				if(!StringUtils.isEmpty(text[12]))
				{
					crc.setHomeYellow(Short.parseShort(text[12]));
				}
				if(!StringUtils.isEmpty(text[13]))
				{
					crc.setGuestYellow(Short.parseShort(text[13]));
				}
				list.add(crc);
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
