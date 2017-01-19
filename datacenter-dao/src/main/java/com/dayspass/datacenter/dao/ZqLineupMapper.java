package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.ZqLineup;

public interface ZqLineupMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ZqLineup record);

	int insertSelective(ZqLineup record);

	ZqLineup selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ZqLineup record);

	int updateByPrimaryKey(ZqLineup record);

	/**
	 * 查询比赛赛前阵容
	 * 
	 * @author zhangcb
	 * @param scheduleid
	 * @return
	 */
	ZqLineup selectByScheduleID(int scheduleid);

}