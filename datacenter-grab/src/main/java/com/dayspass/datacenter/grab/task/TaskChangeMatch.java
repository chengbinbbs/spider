package com.dayspass.datacenter.grab.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.grab.handler.GrabForChangeMatch;

@Component("taskChangeMatch")
public class TaskChangeMatch {
	private static Logger logger = LoggerFactory.getLogger(TaskChangeMatch.class);
	//private static MemCached memcache = MemCached.getInstance();

	@Autowired
	private GrabForChangeMatch grabChangeMatch;

	@Value("${grab.changeurl}")
	private String changeurl; // 比分直播url
	@Value("${jsbf.change}")
	private String position;

	/**
	 * 抓取比分直播change文件
 	 * @author kouyi
	 */
	@SuppressWarnings("unchecked")
	public void grabChange() {
		logger.debug("-----开始抓取比分直播文件-----");
		long start = System.currentTimeMillis();
		/*try {
			List<Match> list = grabChangeMatch.collect(changeurl, null, ScheduleUtil.CHARTSET_GBK);
			if(!StringUtils.isEmpty(list)) {
				Map<Integer, Match> jsbfmap = (Map<Integer, Match>) memcache.get(ScheduleUtil.jsbfkey(0));
				if(StringUtils.isEmpty(jsbfmap)) {
					return;
				}
				for (Match sche : list) {
					String key = ScheduleUtil.KEYSCHDULE + sche.getScheduleId();
					//if (!memcache.contains(key)) {
					//	continue;
					//}
					// 更新完整赛事单个缓存
					Match lsche = (Match) memcache.get(key);
					if (!ScheduleUtil.samezm(sche).equals(ScheduleUtil.samezm(lsche))) {
						ScheduleUtil.schesynchro(lsche, sche);
						memcache.set(key, lsche, ScheduleUtil.MATCHEXITIME);//更新完整赛事缓存
						// 更新即时比分赛事缓存
						Match jsbfsche = jsbfmap.get(sche.getScheduleId());
						if (!StringUtils.isEmpty(jsbfsche)) {
							ScheduleUtil.schesynchro(jsbfsche, sche);
							jsbfmap.put(sche.getScheduleId(), jsbfsche);
						}
						//队列更新库
						if (!TaskDayMatch.queuethree.contains(sche.getScheduleId())) {
							TaskDayMatch.queuethree.put(sche.getScheduleId());
						}
					}
				}
				memcache.set(ScheduleUtil.jsbfkey(0), jsbfmap, ScheduleUtil.JSBFEXITIME);
			}
			FileUtils.write(new File(position + "change.json"), JsonUtil.JsonArray(list, ScheduleUtil.show), ScheduleUtil.CHARTSET_UTF8);
			long end = System.currentTimeMillis();
			logger.info("-----成功抓取比分直播数据 " + list.size() + " 条,用时" + (end - start) / 1000 + "s-----");
		} catch (Exception e) {
			logger.error("grabChange抓取异常：", e);
		}*/
	}

}
