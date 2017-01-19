package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.LqLocation;

public interface LqLocationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LqLocation record);

    int insertSelective(LqLocation record);

    LqLocation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LqLocation record);

    int updateByPrimaryKey(LqLocation record);
}