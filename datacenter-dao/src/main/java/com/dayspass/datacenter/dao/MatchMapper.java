package com.dayspass.datacenter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayspass.datacenter.domain.Match;

public interface MatchMapper{
	
	/**
	 * 保存比赛
	 * @param record
	 * @return
	 */
    public int insert(Match sche);
    
    /**
     * 更新比赛信息
     * @param sche
     * @return
     */
    public int update(Match sche);

    /**
     * 查询单个比赛数据
     * @param scheduleid
     * @return
     */
    public Match queryScheduleInfo(Integer scheduleid);  
    
    /**
	 * 时间段查询比赛列表
	 * @author kouyi
	 * @param end 
	 * @param begin 
	 * @return
	 */
	public List<Match> queryScheduleListForTime(@Param("begin")String begin, @Param("end")String end);
    
    /**
	 * 查询两队历史交锋及赛程列表
	 * @author kouyi
	 * @param hometeamid 
	 * @param guestteamid 
	 * @return
	 */
	public List<Match> queryScheduleListForDistory(@Param("hometeamid")Integer hometeamid, @Param("guestteamid")Integer guestteamid);
    
	
    /**
	 * 查询满足大小球赔率抓取任务比赛列表
	 * @author kouyi
	 * @return List<Schedule>
	 */
	public List<Match> queryNoStartScheduleListForOdds();
	
    /**
	 * 查询满足分析页抓取任务比赛列表
	 * @author kouyi
	 * @return List<Schedule>
	 */
	public List<Match> queryNoStartScheduleListForAnalysis();
	
}