package com.dayspass.datacenter.dao;

import java.util.List;
import java.util.Map;

import com.dayspass.datacenter.domain.ZqSubSclass;

public interface ZqSubSclassMapper {
    int deleteByPrimaryKey(Integer subsclassid);

    int insert(ZqSubSclass record);

    int insertSelective(ZqSubSclass record);

    ZqSubSclass selectByPrimaryKey(Integer subsclassid);

    int updateByPrimaryKeySelective(ZqSubSclass record);

    int updateByPrimaryKey(ZqSubSclass record);

	List<ZqSubSclass> queryAll(Map<String, Object> maps);
}