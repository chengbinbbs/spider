package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.LqPlayer;

public interface LqPlayerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LqPlayer record);

    int insertSelective(LqPlayer record);

    LqPlayer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LqPlayer record);

    int updateByPrimaryKeyWithBLOBs(LqPlayer record);

    int updateByPrimaryKey(LqPlayer record);
}