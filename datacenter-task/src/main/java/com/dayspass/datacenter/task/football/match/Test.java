package com.dayspass.datacenter.task.football.match;
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
			
    		String pagecontent = HttpClientUtil.callHttpGet("http://info.sporttery.cn/football/match_list.php", "gbk");
        	
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
            Elements nodes = matchnodes.get(1).children();
            if(null == nodes || nodes.size() == 0)
            {
            	logger.info("抓取竞彩足球对阵为空!");
                return;
            }
            boolean newIssue = false;
            int issueCode = 0;
            for(int i = 0; i < nodes.size(); i++)
            {
            	if(i % 2 == 0)
            	{
            		String issue = nodes.get(i).ownText().substring(0, 2);
            		System.out.println("新期次:" + issue);
            	}
            	else
            	{
            		Elements trs = nodes.get(i).getElementsByTag("tr");
                	if(trs != null && trs.size() > 0)
                	{
                		for(int j = 0; j < trs.size(); j++)
                		{
                			Elements tds = trs.get(j).getElementsByTag("td");
                			if(tds != null && tds.size() > 0)
                        	{
                				String weekday = tds.get(0).text();
                				String league = tds.get(1).text();
                				
                				Element team = tds.get(2);
                				String hometeam = team.getElementsByClass("zhu").text();
                				String guestteam = team.getElementsByClass("ke").text();
                				
                				String href = team.getElementsByTag("a").attr("href");
                				String jcwid = href.split("=")[1];
                				
                				String matchtime = tds.get(3).text();
                				System.out.println(weekday + "-" + league + "-" + hometeam+ "-" + guestteam+ "-" + jcwid + "-" + matchtime);
                				String status = tds.get(5).text();
                				if("已开售".equals(status))
                				{
                					System.out.println("已开售");
                				}
                				
                				String spfstatus = tds.get(6).getElementsByTag("div").attr("class");
                				String rqspfstatus = tds.get(7).getElementsByTag("div").attr("class");
                				String bfstatus = tds.get(8).getElementsByTag("div").attr("class");
                				String zjqstatus = tds.get(9).getElementsByTag("div").attr("class");
                				String bqcstatus = tds.get(10).getElementsByTag("div").attr("class");
                				System.out.println(spfstatus + "-" + rqspfstatus + "-" + bfstatus + "-" + zjqstatus + "-" + bqcstatus);
                        	}
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
