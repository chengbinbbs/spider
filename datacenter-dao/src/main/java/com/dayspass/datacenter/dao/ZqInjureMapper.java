package com.dayspass.datacenter.dao;

import org.apache.ibatis.annotations.Param;

import com.dayspass.datacenter.domain.ZqInjure;

public interface ZqInjureMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ZqInjure record);

    ZqInjure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZqInjure record);

	ZqInjure selectByScheduleID(@Param("scheduleid")int scheduleid);

}