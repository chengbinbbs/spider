package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.LqSchedule;

public interface LqScheduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LqSchedule record);

    int insertSelective(LqSchedule record);

    LqSchedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LqSchedule record);

    int updateByPrimaryKey(LqSchedule record);
}