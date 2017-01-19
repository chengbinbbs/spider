package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.LqTeamOrder;

public interface LqTeamOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LqTeamOrder record);

    int insertSelective(LqTeamOrder record);

    LqTeamOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LqTeamOrder record);

    int updateByPrimaryKey(LqTeamOrder record);
}