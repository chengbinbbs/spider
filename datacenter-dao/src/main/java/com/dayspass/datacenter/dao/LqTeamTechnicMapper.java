package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.LqTeamTechnic;

public interface LqTeamTechnicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LqTeamTechnic record);

    int insertSelective(LqTeamTechnic record);

    LqTeamTechnic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LqTeamTechnic record);

    int updateByPrimaryKey(LqTeamTechnic record);
}