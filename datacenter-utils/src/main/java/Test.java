import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dayspass.datacenter.bean.User;
import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.http.util.HttpClientUtil;

public class Test {

	private static final Logger logger = LoggerFactory.getLogger(Test.class);
	
	public static void grabJczqMatch(){
    	try {
			
    		String pagecontent = HttpClientUtil.callHttpGet("http://info.sporttery.com/basketball/match_result.php", "gbk");
        	
        	if(StringUtils.isEmpty(pagecontent)){
                logger.info("<JczqMatchTask - grabJczqMatch> : 抓取竞彩足球对阵为空.");
                return;
            }
        	parseData(pagecontent);
		} catch (Exception e) {
			logger.info("<JczqMatchTask - grabJczqMatch> : 抓取竞彩足球对阵异常:"+e);
		}
    }
	
	private static void parseData(String pagecontent) {
		try {
			Document doc = Jsoup.parse(pagecontent);
            Elements matchnodes = doc.getElementsByClass("match_list");
            if (matchnodes == null || matchnodes.size() < 2) {
                logger.info("抓取竞彩足球对阵为空!");
                return;
            }
            Elements nodes = matchnodes.get(1).getAllElements();
            if(null == nodes || nodes.size() == 0)
            {
            	logger.info("抓取竞彩足球对阵为空!");
                return;
            }
            for(int i = 0; i < nodes.size(); i++)
            {
            	Elements trs = nodes.get(i).getElementsByTag("tr");
            	if(trs != null && trs.size() > 0)
            	{
            		for(int j = 0; j < trs.size(); j++)
            		{
            			Elements tds = trs.get(j).getElementsByTag("td");
            			if(tds != null && tds.size() >= 11)
                    	{
            				String weekday = tds.get(1).text();
//            				if(weekStr.length() == 5){
//            					issueCode = getIssueDay(weekStr.substring(0,2),matchTime);
//                                basketBallMatch.setTeamId(weekStr.substring(2,5));
//                            }
            				
            				String status = tds.get(8).text();
            				if(!"已完成".equals(status))
            				{
            					System.out.println("已开售");
            					continue;
            				}
            				Elements one = tds.get(4).children();
            				String onescore = one.get(0).text();
            				String twoscore = one.get(1).text();
            				
            				Elements two = tds.get(5).children();
            				String threescore = two.get(0).text();
            				String fourscore = two.get(1).text();
            				
            				Elements last = tds.get(7).getElementsByTag("span");
            				String lastscore = last.text();
            				
            				System.out.println(weekday + "-" + onescore + "-" + twoscore + "-" + threescore+ "-" + fourscore+ "-" + lastscore);
                    	}
            		}
            	}
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		grabJczqMatch();
	}
}
