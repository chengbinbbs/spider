package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.LqPlayerTechnic;

public interface LqPlayerTechnicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LqPlayerTechnic record);

    int insertSelective(LqPlayerTechnic record);

    LqPlayerTechnic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LqPlayerTechnic record);

    int updateByPrimaryKey(LqPlayerTechnic record);
}