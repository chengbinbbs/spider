package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.ZqSclassInfo;

public interface ZqSclassInfoMapper {
    int deleteByPrimaryKey(Integer infoid);

    int insert(ZqSclassInfo record);

    int insertSelective(ZqSclassInfo record);

    ZqSclassInfo selectByPrimaryKey(Integer infoid);

    int updateByPrimaryKeySelective(ZqSclassInfo record);

    int updateByPrimaryKey(ZqSclassInfo record);
}