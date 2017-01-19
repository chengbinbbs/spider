package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.ZqTeam;

public interface ZqTeamMapper {
    int deleteByPrimaryKey(Integer teamid);

    int insert(ZqTeam record);

    int insertSelective(ZqTeam record);

    ZqTeam selectByPrimaryKey(Integer teamid);

    int updateByPrimaryKeySelective(ZqTeam record);

    int updateByPrimaryKey(ZqTeam record);
}