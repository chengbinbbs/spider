package com.dayspass.datacenter.dao;

import java.util.List;
import java.util.Map;

import com.dayspass.datacenter.domain.ZqCupMatch;

public interface ZqCupMatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZqCupMatch record);

    int insertSelective(ZqCupMatch record);

    ZqCupMatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZqCupMatch record);

    int updateByPrimaryKey(ZqCupMatch record);

	List<ZqCupMatch> queryAll(Map<String, Object> cupparam);
}