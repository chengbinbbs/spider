package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.LqSclass;
import com.dayspass.datacenter.domain.LqSclassWithBLOBs;

public interface LqSclassMapper {
    int deleteByPrimaryKey(Integer sclassid);

    int insert(LqSclassWithBLOBs record);

    int insertSelective(LqSclassWithBLOBs record);

    LqSclassWithBLOBs selectByPrimaryKey(Integer sclassid);

    int updateByPrimaryKeySelective(LqSclassWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(LqSclassWithBLOBs record);

    int updateByPrimaryKey(LqSclass record);
}