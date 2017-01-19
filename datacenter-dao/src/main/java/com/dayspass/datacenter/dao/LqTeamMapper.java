package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.LqTeam;
import com.dayspass.datacenter.domain.LqTeamWithBLOBs;

public interface LqTeamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LqTeamWithBLOBs record);

    int insertSelective(LqTeamWithBLOBs record);

    LqTeamWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LqTeamWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(LqTeamWithBLOBs record);

    int updateByPrimaryKey(LqTeam record);
}