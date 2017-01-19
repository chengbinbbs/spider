package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.LqSclassInfo;

public interface LqSclassInfoMapper {
    int deleteByPrimaryKey(Integer infoid);

    int insert(LqSclassInfo record);

    int insertSelective(LqSclassInfo record);

    LqSclassInfo selectByPrimaryKey(Integer infoid);

    int updateByPrimaryKeySelective(LqSclassInfo record);

    int updateByPrimaryKey(LqSclassInfo record);
}