package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.ZqPlayer;

public interface ZqPlayerMapper {
    int deleteByPrimaryKey(Integer playerid);

    int insert(ZqPlayer record);

    int insertSelective(ZqPlayer record);

    ZqPlayer selectByPrimaryKey(Integer playerid);

    int updateByPrimaryKeySelective(ZqPlayer record);

    int updateByPrimaryKey(ZqPlayer record);
}