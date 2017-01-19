package com.dayspass.datacenter.service.match;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.MatchMapper;
import com.dayspass.datacenter.domain.Match;

/**
 * 对阵服务
 * @author kouyi
 * @version 1.0 2016-12-05
 */
@Service("matchService")
public class MatchService {
	private static Logger logger = LoggerFactory.getLogger(MatchService.class);
	
	@Autowired
	private MatchMapper schemapper;
	
	/**
	 * 插入对阵数据
	 * @param sche
	 * @return int
	 */
	public int insert(Match sche) {
		try {
			return schemapper.insert(sche);
		} catch(DuplicateKeyException e) {//已经存在
			return 2;
		} catch(Exception e) {
			logger.error("插入对阵数据异常 scheduleid="+sche.getScheduleId(), e);
			return 0;
		}
	}
	
	/**
	 * 查询单场对阵数据
	 * @param sche
	 * @return schedule
	 */
	public Match queryScheduleInfo(Integer scheduleid) {
		try {
			return schemapper.queryScheduleInfo(scheduleid);
		} catch(Exception e) {
			logger.error("查询单场对阵数据异常", e);
			return null;
		}
	}
	
	/**
	 * 查询当前期即时比分对阵
	 * @param sche
	 * @return List
	 */
	public List<Match> queryScheduleList(String begin, String end) {
		try {
			return schemapper.queryScheduleListForTime(begin, end);
		} catch(Exception e) {
			logger.error("查询当前期即时比分数据异常", e);
			return null;
		}
	}
	
	/**
	 * 查询两队历史交锋及赛程
	 * @param sche
	 * @return List
	 */
	public List<Match> queryScheduleListForDistory(Integer hometeamid, Integer guestteamid) {
		try {
			return schemapper.queryScheduleListForDistory(hometeamid, guestteamid);
		} catch(Exception e) {
			logger.error("查询两队历史交锋及赛程异常", e);
			return null;
		}
	}
	
	/**
	 * 更新对阵数据
	 * @param sche
	 * @return int
	 */
	public int update(Match sche) {
		try {
			return schemapper.update(sche);
		} catch(Exception e) {
			logger.error("更新对阵数据异常", e);
			return 0;
		}
	}
	
}
