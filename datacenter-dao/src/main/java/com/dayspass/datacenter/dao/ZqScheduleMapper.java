package com.dayspass.datacenter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dayspass.datacenter.command.ZqJsBfCommand;
import com.dayspass.datacenter.domain.ZqSchedule;

public interface ZqScheduleMapper {
    int deleteByPrimaryKey(Integer scheduleid);

    int insert(ZqSchedule record);

    int insertSelective(ZqSchedule record);

    ZqSchedule selectBySid(Integer sid);

    int updateByPrimaryKeySelective(ZqSchedule record);

    int updateByPrimaryKey(ZqSchedule record);
    
    List<ZqJsBfCommand> queryMatchListBetween(Map<String, String> param);

    List<ZqJsBfCommand> queryMatchListByStage(Map<String, String> param);

	List<ZqSchedule> querySchedulesBetween(@Param("begin")String begin, @Param("end")String end);
}