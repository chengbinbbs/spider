package com.dayspass.datacenter.grab.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.date.util.DateUtils;
import com.dayspass.datacenter.domain.Match;
import com.dayspass.datacenter.grab.handler.GrabForDayMatch;
import com.dayspass.datacenter.service.match.MatchService;
import com.dayspass.datacenter.web.util.ScheduleUtil;

@Component("taskDayMatch")
public class TaskDayMatch {
	private static Logger logger = LoggerFactory.getLogger(TaskDayMatch.class);
	//private static MemCached memcache = MemCached.getInstance();
	private static Map<String, String> mapleague = new HashMap<String, String>();
	public static BlockingQueue<Match> queueone = new LinkedBlockingQueue<Match>();// 插库
	public static BlockingQueue<Match> queuetwo = new LinkedBlockingQueue<Match>();// 更新缓存
	public static BlockingQueue<Integer> queuethree = new LinkedBlockingQueue<Integer>();// 需要更新库的比赛id
	public String curperiod = "";

	@Autowired
	private GrabForDayMatch grabDayMatch;
	@Autowired
	private MatchService scheduleService;

	@Value("${grab.matchurl}")
	private String matchurl; // 当日完整赛事url
	@Value("${config.league}")
	private String league; // 即时比分支持赛事
	@Value("${jsbf.match}")
	private String position;
	
	/**
	 * 抓取当前期完整赛事数据
 	 * @author kouyi
	 */
	public void grabSchedule() {
		logger.debug("-----开始抓取当天完整赛事对阵-----");
		try {
			long start = System.currentTimeMillis();
			initLeague();
			List<Match> list = grabDayMatch.collect(matchurl, mapleague, ScheduleUtil.CHARTSET_GBK);
			if(StringUtils.isEmpty(list)) {
				return;
			}
			for (Match schedule : list) {
				String key = ScheduleUtil.KEYSCHDULE + schedule.getScheduleId();
				//if (!memcache.contains(key)) {// 插入
				//	queueone.put(schedule);
				//} else {// 更新
				//	queuetwo.put(schedule);
				//}
			}
			long end = System.currentTimeMillis();
			logger.info("-----成功抓取当天完整赛事对阵 " + list.size() + " 条,用时" + (end - start) / 1000 + "s-----");
		} catch (Exception e) {
			logger.error("grabSchedule抓取异常：", e);
		}
	}

	/**
	 * 队列插库
	 * @author kouyi
	 */
	public void consumQueueOne() {
		if (queueone.isEmpty()) {
			return;
		}
		int sucnum = 0;//记录新对阵数量
		boolean flag = true;// 线程停止
		while (flag) {
			try {
				Match sche = queueone.take(); // 消费队列段数据
				int row = scheduleService.insert(sche);
				if (row > 0) {
					//memcache.set(ScheduleUtil.KEYSCHDULE + sche.getScheduleId(), sche, ScheduleUtil.MATCHEXITIME);
					if (row == 2) {//缓存过期，库中已 经有该对阵
						if (!queuethree.contains(sche.getScheduleId())) {
							queuethree.put(sche.getScheduleId());
						}
					} else {//新开对阵
						sucnum++;
					}
				}
			} catch (Exception e) {
				logger.error("oddsConsumQueueOne当日完整赛事入库异常：", e);
			}
			if (queueone.isEmpty()) {
				flag = false;
			}
		}
		if(sucnum > 0) {//有新开对阵则从库中取最新,并删除大对阵缓存 
			//memcache.deleteBykey(ScheduleUtil.jsbfkey(0));
		}
	}

	/**
	 * 队列更新缓存
	 * @author kouyi
	 */
	@SuppressWarnings("unchecked")
	public void consumQueueTwo() {
		if (queuetwo.isEmpty()) {
			return;
		}
		/*String jsbfkey = ScheduleUtil.jsbfkey(0);
		Map<Integer, Match> jsbfmap = (Map<Integer, Match>) memcache.get(jsbfkey);
		boolean flag = true;// 线程停止
		while (flag) {
			try {
				Match sche = queuetwo.take(); // 消费队列段数据
				String key = ScheduleUtil.KEYSCHDULE + sche.getScheduleId();
				Match lastsche = (Match) memcache.get(key);
				if (!StringUtils.isEmpty(lastsche) && !ScheduleUtil.samedm(sche).equals(ScheduleUtil.samedm(lastsche))) {
					memcache.set(key, sche, ScheduleUtil.MATCHEXITIME);//更新完整赛事缓存
					if(!StringUtils.isEmpty(jsbfmap)) {//更新即时比分文件缓存
						Match jsbfsche = jsbfmap.get(sche.getScheduleId());
						if(!StringUtils.isEmpty(jsbfsche) && !ScheduleUtil.samedm(sche).equals(ScheduleUtil.samedm(jsbfsche))) {
							ScheduleUtil.schesynchro(jsbfsche, sche);
							jsbfmap.put(sche.getScheduleId(), jsbfsche);
						}
					}
					//队列更新库
					if (!queuethree.contains(sche.getScheduleId())) {
						queuethree.put(sche.getScheduleId());
					}
				}
			} catch (Exception e) {
				logger.error("oddsConsumQueueTwo当日完整赛事更新异常：", e);
			}
			if (queuetwo.isEmpty()) {
				flag = false;
			}
		}
		memcache.set(jsbfkey, jsbfmap, ScheduleUtil.JSBFEXITIME);*/
	}

	/**
	 * 队列更新数据库中对阵
	 * @author kouyi
	 */
	public void consumQueueThree() {
		if (queuethree.isEmpty()) {
			return;
		}
		boolean flag = true;// 线程停止
		while (flag) {
			/*try {
				Integer scheduleid = queuethree.take(); // 消费队列段数据
				Match sche = (Match) memcache.get(ScheduleUtil.KEYSCHDULE + scheduleid);
				if (!StringUtils.isEmpty(sche)) {
					int row = scheduleService.update(sche);
					if(row > 0 && sche.getMatchState() != 0) {
						memcache.deleteBykey(ScheduleUtil.KEYODDSDX + sche.getScheduleId());//删除大小球赔率缓存
						memcache.deleteBykey(ScheduleUtil.KEYANALYSIS + sche.getScheduleId()); //删除分析页抓取缓存
					}
				}
			} catch (Exception e) {
				logger.error("oddsConsumQueueThree更新库中对阵异常：", e);
			}
			if (queuethree.isEmpty()) {
				flag = false;
			}*/
		}
	}
	
	/**
	 * 生成即时比分对阵文件-当前期
 	 * @author kouyi
	 */
	@SuppressWarnings("unchecked")
	public void createJsbfMatch() {
		Map<Integer, Match> mapsche = null;
/*		try {
			String key = ScheduleUtil.jsbfkey(0);
			if(curperiod.equals("")) {
				curperiod = key;
			}
			if (!memcache.contains(key)) {
				// 与完整赛事缓存同步初始化
				mapsche = matchInit(scheduleService.queryScheduleList(ScheduleUtil.matchTime(0), ScheduleUtil.matchTime(1)));
				memcache.set(key, mapsche, ScheduleUtil.JSBFEXITIME);
				if(!curperiod.equals(key) && createLastJsbfMatch()) {//跳期需删除上一期正在进行的比赛
					curperiod = key;//赋值最新key
				}
			} else {
				mapsche = (Map<Integer, Match>) memcache.get(key);
			}
			
			FileUtils.write(new File(position + ScheduleUtil.todaystr(0) + ".json"), JsonUtil.JsonArray(doSort(mapsche), ScheduleUtil.show), ScheduleUtil.CHARTSET_UTF8);
		} catch (Exception e) {
			logger.error("生成当前期即时比分对阵文件异常", e);
		}*/
	}

	/**
	 * 即时比分查库后与缓存同步
 	 * @author kouyi
	 * @param schelist
	 */
	public Map<Integer, Match> matchInit(List<Match> schelist) {
		if (StringUtils.isEmpty(schelist)) {
			return null;
		}
		Map<Integer, Match> mapsche = new HashMap<Integer, Match>();
		/*for (Match sche : schelist) {
			String key = ScheduleUtil.KEYSCHDULE + sche.getScheduleId();
			Match lastsche = (Match) memcache.get(key);
			//已经过开赛半小时 状态还是未开赛的比赛 可能延期，此处直接过滤
			if(new Date().getTime() - lastsche.getMatchTime().getTime() > 30*60*1000 && lastsche.getMatchState() == 0) {
				continue;
			}
			//比赛距离开赛时间超过150分钟还在进行中，直接过滤
			if(new Date().getTime() - lastsche.getMatchTime().getTime() >= 150*60*1000 && lastsche.getMatchState() > 0 && lastsche.getMatchState() < 4) {
				continue;
			}
			//同步
			if (!StringUtils.isEmpty(lastsche) && !ScheduleUtil.samedm(sche).equals(ScheduleUtil.samedm(lastsche))) {
				ScheduleUtil.schesynchro(sche, lastsche);
			}
			mapsche.put(sche.getScheduleId(), sche);
		}*/
		return mapsche;
	}

	/**
	 * 即时比分排序
 	 * @author kouyi
	 * @param list
	 */
	public List<Match> doSort(Map<Integer, Match> map) {
		List<Match> live = new ArrayList<Match>();// 进行中
		if (StringUtils.isEmpty(map)) {
			return live;
		}
		List<Match> nostart = new ArrayList<Match>(); // 未开赛
		List<Match> over = new ArrayList<Match>(); // 已结束
		for (Entry<Integer, Match> entry : map.entrySet()) {
			Match sh = entry.getValue();
			sh.setBei(DateUtils.formatDate(sh.getMatchTime(), DateUtils.HOURMIN_FORMAT));
			int code = sh.getMatchState();
			if (code == 1 || code == 2 || code == 3 || code == 4) {
				live.add(sh);
			} else if (code == 0) {
				nostart.add(sh);
			} else {
				over.add(sh);
			}
		}

		Collections.sort(live);
		Collections.sort(nostart);
		Collections.sort(over);
		live.addAll(nostart);
		live.addAll(over);
		return live;
	}
	
	/**
	 * 跳期时生成即时比分上一期文件-删除正在进行和未开赛的比赛直播
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean createLastJsbfMatch() {
		/*try {
			Map<Integer, Match> lastmap = (Map<Integer, Match>) memcache.get(ScheduleUtil.jsbfkey(-1));
			if(!StringUtils.isEmpty(lastmap)) {
				Iterator<Entry<Integer, Match>> it = lastmap.entrySet().iterator();
				while(it.hasNext()){
					Entry<Integer, Match> entry = it.next();
					if(entry.getValue().getMatchState() > -1 && entry.getValue().getMatchState() < 4) {
						it.remove();
					}
				}
				FileUtils.write(new File(position + ScheduleUtil.todaystr(-1) + ".json"), JsonUtil.JsonArray(doSort(lastmap), ScheduleUtil.show), ScheduleUtil.CHARTSET_UTF8);
				return true;
			}
		} catch (Exception e) {
			logger.error("跳期时生成上一期对阵异常", e);
		}*/
		return false;
	}
	
	/**
	 * 生成历史前三期比赛期次
 	 * @author kouyi
	 */
	public void createDistoryPeriod() {
		/*try {
			List<Period> array = new ArrayList<Period>(3);
			String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
			
			Period pd = new Period();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(ScheduleUtil.today().toDate());
			cal.add(Calendar.DATE, -1);
			String one = DateUtils.formatDate(cal.getTime(), DateUtils.MONTHDAY_FORMAT);
			int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
			pd.setName(DateUtils.formatDate(cal.getTime(), DateUtils.PERIOD_FORMAT));
			pd.setValue(one + "(" + weekDays[week<0?0:week] + ")");
			array.add(pd);
			
			Period pd2 = new Period();
			cal.add(Calendar.DATE, -1);
			String two = DateUtils.formatDate(cal.getTime(), DateUtils.MONTHDAY_FORMAT);
			week = cal.get(Calendar.DAY_OF_WEEK) - 1;
			pd2.setName(DateUtils.formatDate(cal.getTime(), DateUtils.PERIOD_FORMAT));
			pd2.setValue(two + "(" + weekDays[week<0?0:week] + ")");
			array.add(pd2);
			
			Period pd3 = new Period();
			cal.add(Calendar.DATE, -1);
			String three = DateUtils.formatDate(cal.getTime(), DateUtils.MONTHDAY_FORMAT);
			week = cal.get(Calendar.DAY_OF_WEEK) - 1;
			pd3.setName(DateUtils.formatDate(cal.getTime(), DateUtils.PERIOD_FORMAT));
			pd3.setValue(three + "(" + weekDays[week<0?0:week] + ")");
			array.add(pd3);
			
			FileUtils.write(new File(position + "period.json"), JsonUtil.JsonArray(array), ScheduleUtil.CHARTSET_UTF8);
		} catch (Exception e) {
			logger.error("生成历史期次文件异常", e);
		}*/
	}
	
	public void initLeague() {
		// 初始化支持的赛事列表
		if (!StringUtils.isEmpty(league) && !league.equals("-1")) {
			for (String lg : league.split("\\,")) {
				mapleague.put(lg, lg);
			}
		}
	}

}
