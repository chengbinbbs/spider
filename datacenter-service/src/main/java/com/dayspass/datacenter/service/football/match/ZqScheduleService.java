package com.dayspass.datacenter.service.football.match;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.bean.LeagueIndex;
import com.dayspass.datacenter.bean.LeagueSort;
import com.dayspass.datacenter.command.ZqJsBfCommand;
import com.dayspass.datacenter.common.util.PingYinUtil;
import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.dao.ZqScheduleMapper;
import com.dayspass.datacenter.domain.ZqSchedule;
import com.dayspass.datacenter.properties.util.LeagueUtil;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.json.JSONObject;

@Service("zqScheduleService")
public class ZqScheduleService {

	private static Logger logger = LoggerFactory.getLogger(ZqScheduleService.class);

	//即时比分比赛缓存的key
	private String ZQ_JSBF_SCHEDULE = "zq_jsbf_schedule_";
	@Autowired
	private JedisClient jedisClient;
		
	@Autowired
	private ZqScheduleMapper zqScheduleMapper;
	
	public int deleteByPrimaryKey(Integer scheduleid){
		return zqScheduleMapper.deleteByPrimaryKey(scheduleid);
	}

	public int insertSelective(ZqSchedule record){
    	return zqScheduleMapper.insertSelective(record);
    }

	public ZqSchedule selectBySid(Integer sid){
    	return zqScheduleMapper.selectBySid(sid);
    }

	public int updateByPrimaryKeySelective(ZqSchedule record){
    	return zqScheduleMapper.updateByPrimaryKeySelective(record);
    }
	
	/**
	 * 比赛结束根据比分、盘口获取盘路结果
	 * @author zhangcb
	 * @param param
	 * @param sc
	 * @return
	 */
	public void getPanResult(int sid, int homescore, int guestscore, ZqSchedule sc) {
//		try
//		{
//			String comid = CompanyUtil.getStrCompanyIds(false);
//			LetGoal letgoal = oddsService.queryFirstLetGoalBySchedule(sid, comid);
//			if(letgoal != null)
//			{
//				double firstgoal = letgoal.getFirstgoal();  //盘口
//				double diff = homescore - guestscore - firstgoal;
//				Byte result = 1;  //盘路结果
//				if(diff > 0)
//				{
//					result = 1;
//				}
//				else if(diff == 0)
//				{
//					result = 2;
//				}
//				else
//				{
//					result = 3;
//				}
//				if(sc != null)
//				{
//					sc.setFirstgoal(firstgoal);
//					sc.setResult(result);
//				}
//			}
//			TotalScore totalscore = oddsService.queryFirstTotalScoreBySchedule(sid, comid);
//			if(totalscore != null)
//			{
//				double firstgoal = totalscore.getFirstgoal();  //盘口
//				double diff = homescore + guestscore - firstgoal;
//				Byte result = 1;  //盘路结果
//				if(diff > 0)
//				{
//					result = 1;
//				}
//				else if(diff == 0)
//				{
//					result = 2;
//				}
//				else
//				{
//					result = 3;
//				}
//				if(sc != null)
//				{
//					sc.setDxresult(result);
//				}
//			}
//		}
//		catch(Exception e)
//		{
//			logger.info("获取盘路结果异常,sid:"+sid+"，异常信息：{}",e.getMessage());
//		}
	}
	
	/**
	 * 查询某个时间段时间的即时比分赛事详情列表
	 * @author zhangcb
	 * @param begin
	 * @param end
	 * @return
	 */

	public List<ZqJsBfCommand> queryMatchListBetween(Map<String, String> param) {
		return zqScheduleMapper.queryMatchListBetween(param);

	}

	/**
	 * 获取缓存数据，更新即时比分比赛信息
	 * @author zhangcb
	 * @param matchList
	 */
	public void changeMatch(ZqJsBfCommand match) {
		try
		{
			int sid = match.getSid().intValue();
			//1.库里面完场的赛事不做处理
//			if(match.getMatchstate() == -1)
//			{
//				return;
//			}
			String key = ZQ_JSBF_SCHEDULE + sid;
			if(jedisClient.existsObject(key))
			{
				ZqSchedule sc = (ZqSchedule) jedisClient.getObject(key);
				if(match.getNeutrality() && !StringUtils.isEmpty(match.getSort()))
				{
					//反转
					match.setHomeOrder(sc.getGuestOrder());
					match.setGuestOrder(sc.getHomeOrder());
					match.setHomescore(sc.getGuestscore());
					match.setGuestscore(sc.getHomescore());
					match.setHomeRed(sc.getGuestRed());
					match.setGuestRed(sc.getHomeRed());
					match.setHomeYellow(sc.getGuestYellow());
					match.setGuestYellow(sc.getHomeYellow());
					match.setHomehalfscore(sc.getGuesthalfscore());
					match.setGuesthalfscore(sc.getHomehalfscore());
				}
				else
				{
					match.setHomeOrder(sc.getHomeOrder());
					match.setGuestOrder(sc.getGuestOrder());
					match.setHomescore(sc.getHomescore());
					match.setGuestscore(sc.getGuestscore());
					match.setHomeRed(sc.getHomeRed());
					match.setGuestRed(sc.getGuestRed());
					match.setHomeYellow(sc.getHomeYellow());
					match.setGuestYellow(sc.getGuestYellow());
					match.setHomehalfscore(sc.getHomehalfscore());
					match.setGuesthalfscore(sc.getGuesthalfscore());
				}
				
				match.setMatchstate(sc.getMatchstate());
				match.setMatchtime(sc.getMatchtime());
				match.setMatchtime2(sc.getMatchtime2());
				match.setRemark(sc.getExplainlist());
			}
			//球探状态异常时，程序自动判断比赛状态
			if(match.getMatchstate() == 0)
			{
				//距离开赛时间10分钟未开赛的状态变为推迟
				long count = (new Date().getTime() - match.getMatchtime().getTime())/60000;
				if(count > 10)
				{
					match.setMatchstate((short) -14);
				}
			}
			else if(match.getMatchstate() > 0 && match.getMatchstate() <= 4)
			{
				long hour = (new Date().getTime() - match.getMatchtime().getTime())/3600000;
				if(hour > 2)
				{
					match.setMatchstate((short) -1);
				}
			}
		}
		catch(Exception e)
		{
			logger.info("即时比分更新比赛状态异常，sid"+match.getSid()+"异常信息：{}",e.getMessage());
		}
	}

	/**
	 * 根据期次查询即时比分赛事列表
	 * @author zhangcb
	 * @param params
	 * @return
	 */
	public List<ZqJsBfCommand> queryMatchListByStage(Map<String, String> params) {
		return zqScheduleMapper.queryMatchListByStage(params);
	}

	/**
	 * 比分反转
	 * @author zhangcb
	 * @param match
	 */
	public void swapTeam(ZqJsBfCommand match) {
		try
		{
			if(match.getNeutrality() && !StringUtils.isEmpty(match.getSort()))
			{
				//反转
				String ordertemp = match.getHomeOrder();
				match.setHomeOrder(match.getGuestOrder());
				match.setGuestOrder(ordertemp);
				
				Short scoretemp = match.getHomescore();
				match.setHomescore(match.getGuestscore());
				match.setGuestscore(scoretemp);
				
				Short redtemp = match.getHomeRed();
				match.setHomeRed(match.getGuestRed());
				match.setGuestRed(redtemp);
				
				Short halfscoretemp = match.getHomehalfscore();
				match.setHomehalfscore(match.getGuesthalfscore());
				match.setGuesthalfscore(halfscoretemp);
			}
		}
		catch(Exception e)
		{
			logger.info("即时比分更新比赛状态异常，sid"+match.getSid()+"异常信息：{}",e.getMessage());
		}
	}

	/**
	 * 获取联赛索引
	 * @param qc
	 * @param matchList
	 * @return
	 */
	public JSONObject getLeagueIndex(String key,List<ZqJsBfCommand> matchList) {
		JSONObject json = new JSONObject();
		try 
		{
			if(jedisClient.existsObject(key))
			{
				return JSONObject.fromObject(jedisClient.getObject(key));
			}
			//快捷赛事筛选
			List<LeagueIndex> allindex = Lists.newArrayList();
			//竞彩赛事筛选
			List<LeagueIndex> jcindex = Lists.newArrayList();				
			
			if(!StringUtils.isEmpty(matchList))
			{
				Map<Integer, LeagueIndex> leagues = Maps.newHashMap();
				for(ZqJsBfCommand match:matchList)
				{
					Integer leagueid = match.getLeagueId();
					String league = match.getLeague();
					if(StringUtils.isEmpty(league))
					{
						continue;
					}
					LeagueIndex lg = null;
					if(leagues.containsKey(leagueid))
					{
						lg = leagues.get(leagueid);
					}
					else
					{
						lg = new LeagueIndex();
						lg.setId(leagueid);
						lg.setName(league);
						//联赛排序
						lg.setOrder(LeagueUtil.getLeagueOrder(league));
						//联赛字母索引
						String index = PingYinUtil.getFirstLetter(lg.getName(), true);
						lg.setIndex(index);
					}
					lg.setCount(lg.getCount() + 1);
					leagues.put(leagueid, lg);
				}
				
				List<String> europeantoplist = LeagueUtil.getLeagues("europeantop");		//欧洲顶级
				List<String> europeanlowlist = LeagueUtil.getLeagues("europeanlow");		//欧洲低级
				List<String> asianlist = LeagueUtil.getLeagues("asian");					//亚洲
				List<String> southamericalist = LeagueUtil.getLeagues("southamerica");		//南美洲
				List<String> northamericalist = LeagueUtil.getLeagues("northamerica");		//北美洲
				List<String> countrylist = LeagueUtil.getLeagues("country");				//国家
				List<LeagueIndex> europeantop = Lists.newArrayList();		//欧洲顶级
				List<LeagueIndex> europeanlow = Lists.newArrayList();		//欧洲低级
				List<LeagueIndex> asian = Lists.newArrayList();				//亚洲
				List<LeagueIndex> southamerica = Lists.newArrayList();		//南美洲
				List<LeagueIndex> northamerica = Lists.newArrayList();		//北美洲
				List<LeagueIndex> country = Lists.newArrayList();			//国家
				List<LeagueIndex> other = Lists.newArrayList();				//其它
				
				for(Map.Entry<Integer, LeagueIndex> entry:leagues.entrySet())
				{
					LeagueIndex lg = entry.getValue();
					String league = lg.getName();
					allindex.add(lg);
					
					//2.竞彩分类
					if(europeantoplist.contains(league))
					{
						lg.setTag("欧洲顶级");
						europeantop.add(lg);
					}
					else if(europeanlowlist.contains(league))
					{
						lg.setTag("欧低级别");
						europeanlow.add(lg);
					}
					else if(asianlist.contains(league))
					{
						lg.setTag("亚大洲");
						asian.add(lg);
					}
					else if(southamericalist.contains(league))
					{
						lg.setTag("南美洲");
						southamerica.add(lg);
					}
					else if(northamericalist.contains(league))
					{
						lg.setTag("中北美");
						northamerica.add(lg);
					}
					else if(countrylist.contains(league))
					{
						lg.setTag("国家队");
						country.add(lg);
					}
					else
					{
						lg.setTag("其它赛事");
						other.add(lg);
					}
				}
				//全部索引排序
				LeagueSort.sortByIndex(allindex);
				//竞彩赛事排序
				jcindex.addAll(europeantop);
				jcindex.addAll(europeanlow);
				jcindex.addAll(asian);
				jcindex.addAll(southamerica);
				jcindex.addAll(northamerica);
				jcindex.addAll(country);
				jcindex.addAll(other);
			}
			json.put("index", allindex);
			json.put("hot", jcindex);
			jedisClient.setObject(key, json, 10*60);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return json;
	}

	public List<ZqSchedule> querySchedulesBetween(String begin, String end) {
		return zqScheduleMapper.querySchedulesBetween(begin,end);
	}



}
