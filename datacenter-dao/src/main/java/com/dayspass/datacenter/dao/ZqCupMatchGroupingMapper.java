package com.dayspass.datacenter.dao;

import java.util.List;
import java.util.Map;

import com.dayspass.datacenter.domain.ZqCupMatchGrouping;

public interface ZqCupMatchGroupingMapper {
    int deleteByPrimaryKey(Integer groupid);

    int insert(ZqCupMatchGrouping record);

    int insertSelective(ZqCupMatchGrouping record);

    ZqCupMatchGrouping selectByPrimaryKey(Integer groupid);

    int updateByPrimaryKeySelective(ZqCupMatchGrouping record);

    int updateByPrimaryKey(ZqCupMatchGrouping record);

	List<ZqCupMatchGrouping> queryAll(Map<String, Object> param);
}