package com.dayspass.datacenter.grab.task.jc;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.contants.FootBallContants;
import com.dayspass.datacenter.date.util.DateUtils;
import com.dayspass.datacenter.domain.FootBallMatch;
import com.dayspass.datacenter.http.util.HttpClientUtil;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.dayspass.datacenter.service.match.FootBallMatchService;
import com.google.common.collect.Maps;

@Component("jczqMatchTask")
public class JczqMatchTask {

	private static final Logger logger = LoggerFactory.getLogger(JczqMatchTask.class);
    
	@Value("${jczq.match.url}")
    private String MATCHURL;
	
	@Autowired
	private FootBallMatchService footBallMatchService;
	@Autowired
	private JedisClient jedisClient;
    
	/**
	 * 抓取竞彩足球对阵
	 */
    public void grabJczqMatch(){
    	try {
			
    		String pagecontent = HttpClientUtil.callHttpGet(MATCHURL, "gbk");
        	
        	if(StringUtils.isEmpty(pagecontent)){
                logger.info("<JczqMatchTask - grabJczqMatch> : 抓取竞彩足球对阵为空.");
                return;
            }
        	parseData(pagecontent);
		} catch (Exception e) {
			logger.info("<JczqMatchTask - grabJczqMatch> : 抓取竞彩足球对阵异常:"+e);
		}
    }

    /**
     * 解析对阵
     * @param pagecontent
     */
	private void parseData(String pagecontent) {
		try {
			Document doc = Jsoup.parse(pagecontent);
            Elements matchnodes = doc.getElementsByClass("match_list");
            if (matchnodes == null || matchnodes.size() != 2) {
                logger.info("抓取竞彩足球对阵为空!");
                return;
            }
            Elements trs = matchnodes.get(1).getElementsByTag("tr");
            if(null == trs || trs.size() == 0)
            {
            	logger.info("抓取竞彩足球对阵为空!");
                return;
            }
            Map<Integer, Map<String, FootBallMatch>> matchmap = Maps.newHashMap();
            Map<String, FootBallMatch> matchlist = null;
            int issueCode = 0;
            for (int i = 0 ; i < trs.size(); i++) {
            	Element tr = trs.get(i);
            	Elements tds = tr.getElementsByTag("td");
            	if(null == tds ||tds.size() == 1)
            	{
            		if(!StringUtils.isEmpty(matchlist))
            		{
            			matchmap.put(issueCode, matchlist);
            		}
            		matchlist = Maps.newHashMap();
            		continue;
            	}
            	FootBallMatch match = new FootBallMatch();
            	//周三001
            	String week = tds.get(0).text();
            	//联赛名称
            	String league = tds.get(1).attr("title");
            	match.setLeagueName(league);
            	//主客队
            	Elements spans = tds.get(2).getElementsByTag("span");
            	String hometeam = spans.get(0).text();
            	match.setHostName(hometeam);
            	String guestteam = spans.get(2).text();
            	match.setGuestName(guestteam);
            	//比赛时间
            	String time = tds.get(3).text();
            	Date matchtime = DateUtils.parseDate(time, "yyyy-MM-dd HH:mm");
            	match.setMatchTime(matchtime);
            	int issue = issueCode = getIssue(week.substring(0, 2), matchtime);
            	String teamid = week.substring(2, 5);
            	match.setIssue(issue);
            	match.setTeamId(teamid);
            	match.setMatchCode(issue + teamid);
            	
            	//竞彩id
            	String jcwId = tds.get(4).getElementsByTag("a").attr("href").split("=")[1];
            	match.setJcwId(Integer.parseInt(jcwId));
            	//比赛开售状态
            	String status = tds.get(5).text();
            	match.setStatus(FootBallContants.STATUS_DEFAULT);
            	
            	String spfStatus = parseStatus(tds.get(6));
                String rqspfStatus = parseStatus(tds.get(7));
                String bfStatus = parseStatus(tds.get(8));
                String zjqStatus = parseStatus(tds.get(9));
                String bqcStatus = parseStatus(tds.get(10));
                setMatchStatus(match, spfStatus, rqspfStatus, bfStatus, zjqStatus, bqcStatus);
                matchlist.put(match.getMatchCode(), match);
                
                if(i == trs.size() - 1)
                {
                	if(!matchmap.containsKey(issueCode))
                	{
                		matchmap.put(issueCode, matchlist);
                	}
                	else
                	{
                		Map<String, FootBallMatch> tempmap = matchmap.get(issueCode);
                		tempmap.putAll(matchlist);
                		matchmap.put(issueCode, tempmap);
                	}
                }
			}
            
            //遍历更新
            for(Map.Entry<Integer, Map<String, FootBallMatch>> map:matchmap.entrySet()){
            	Integer issue = map.getKey();
            	Map<String, FootBallMatch> newmatch = map.getValue();
            	String key = "JCZQ_MATCH_DATA_" + issue;
            	if(jedisClient.get(key) != null)
            	{
            		Map<String, FootBallMatch> cachematch = null;
            		for(Map.Entry<String, FootBallMatch> cachemap:cachematch.entrySet()){
            			for(Map.Entry<String, FootBallMatch> newmap:newmatch.entrySet()){
                			footBallMatchService.addOrUpdateMatch(newmap.getValue());
                		}
            		}
            	}
            	else
            	{
            		for(Map.Entry<String, FootBallMatch> newmap:newmatch.entrySet()){
            			footBallMatchService.addOrUpdateMatch(newmap.getValue());
            		}
            		jedisClient.set(key, null);
            	}
            }
			
		} catch (Exception e) {
			logger.info("抓取竞彩足球对阵解析异常:"+e);
		}
	}
	
	private static String parseStatus(Element ele)
    {
		Elements div = ele.getElementsByTag("div");
		if(StringUtils.isEmpty(div)){
			return "";
		}
		String html = div.toString();
        int pos=html.indexOf("<div class=\"");
        html=html.substring(pos+12);
        pos=html.indexOf("\">");
        return html.substring(0,pos);
    }
	
	private static int getIssue(String week, Date date){
        String weekTmp = DateUtils.getWeekOfDate(date);
        while (!week.equals(weekTmp)){
            date = DateUtils.addDay(date,-1);
            weekTmp = DateUtils.getWeekOfDate(date);
        }
        String issue = DateUtils.formatDate(date, "yyyyMMdd");
        return Integer.parseInt(issue);
    }
	
	private static void setMatchStatus(FootBallMatch match, String spfStatus, String rqspfStatus, String bfStatus, String zjqStatus, String bqcStatus){
        //胜平负
        if(spfStatus.contains("kong1")) {
        	match.setSpfSingleStatus(FootBallContants.BET_STATUS_NOT_SELL);
        	match.setSpfPassStatus(FootBallContants.BET_STATUS_SALES);
        }else if (spfStatus.contains("dan1") ) {
        	match.setSpfSingleStatus(FootBallContants.BET_STATUS_SALES);
        	match.setSpfPassStatus(FootBallContants.BET_STATUS_SALES);
        }else if ( spfStatus.contains("closed1") ){
        	match.setSpfSingleStatus(FootBallContants.BET_STATUS_NOT_SELL);
        	match.setSpfPassStatus(FootBallContants.BET_STATUS_NOT_SELL);
        }
        //让球胜平负
        if(rqspfStatus.contains("kong1")) {
        	match.setRqSpfSingleStatus(FootBallContants.BET_STATUS_NOT_SELL);
        	match.setRqSpfPassStatus(FootBallContants.BET_STATUS_SALES);
        }else if (rqspfStatus.contains("dan1") ) {
        	match.setRqSpfSingleStatus(FootBallContants.BET_STATUS_SALES);
        	match.setRqSpfPassStatus(FootBallContants.BET_STATUS_SALES);
        }else if ( rqspfStatus.contains("closed1") ){
        	match.setRqSpfSingleStatus(FootBallContants.BET_STATUS_NOT_SELL);
        	match.setRqSpfPassStatus(FootBallContants.BET_STATUS_NOT_SELL);
        }
        //总进球
        if(zjqStatus.contains("kong1")) {
        	match.setZjqSingleStatus(FootBallContants.BET_STATUS_NOT_SELL);
        	match.setZjqPassStatus(FootBallContants.BET_STATUS_SALES);
        }else if (zjqStatus.contains("dan1") ) {
        	match.setZjqSingleStatus(FootBallContants.BET_STATUS_SALES);
        	match.setZjqPassStatus(FootBallContants.BET_STATUS_SALES);
        }else if ( zjqStatus.contains("closed1") ){
        	match.setZjqSingleStatus(FootBallContants.BET_STATUS_NOT_SELL);
        	match.setZjqPassStatus(FootBallContants.BET_STATUS_NOT_SELL);
        }
        //比分
        if(bfStatus.contains("kong1")) {
        	match.setBfSingleStatus(FootBallContants.BET_STATUS_NOT_SELL);
        	match.setBfPassStatus(FootBallContants.BET_STATUS_SALES);
        }else if (bfStatus.contains("dan1") ) {
        	match.setBfSingleStatus(FootBallContants.BET_STATUS_SALES);
        	match.setBfPassStatus(FootBallContants.BET_STATUS_SALES);
        }else if ( bfStatus.contains("closed1") ){
        	match.setBfSingleStatus(FootBallContants.BET_STATUS_NOT_SELL);
        	match.setBfPassStatus(FootBallContants.BET_STATUS_NOT_SELL);
        }
        //半全场
        if(bqcStatus.contains("kong1")) {
        	match.setBqcSingleStatus(FootBallContants.BET_STATUS_NOT_SELL);
        	match.setBqcPassStatus(FootBallContants.BET_STATUS_SALES);
        }else if (bqcStatus.contains("dan1") ) {
        	match.setBqcSingleStatus(FootBallContants.BET_STATUS_SALES);
        	match.setBqcPassStatus(FootBallContants.BET_STATUS_SALES);
        }else if ( bqcStatus.contains("closed1") ){
        	match.setBqcSingleStatus(FootBallContants.BET_STATUS_NOT_SELL);
        	match.setBqcPassStatus(FootBallContants.BET_STATUS_NOT_SELL);
        }
	}
	
	public static void main(String[] args) {
		String pagecontent = HttpClientUtil.callHttpGet("http://info.sporttery.cn/football/match_list.php", "gbk");
		int end = pagecontent.indexOf("<a href='/football/match_list.php'");
		int start = pagecontent.indexOf("更新时间：");
		System.out.println("statr:" + start +",end:" + end);
		System.out.println(pagecontent.substring(start, end).replace("更新时间：", "").trim());
		Calendar today = Calendar.getInstance();
		System.out.println(today.get(Calendar.YEAR));
		System.out.println((String)null);
	}
      
}
