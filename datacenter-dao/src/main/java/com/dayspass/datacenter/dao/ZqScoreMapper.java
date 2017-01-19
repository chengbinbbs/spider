package com.dayspass.datacenter.dao;

import java.util.List;
import java.util.Map;

import com.dayspass.datacenter.domain.ZqScore;

public interface ZqScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZqScore record);

    int insertSelective(ZqScore record);

    ZqScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZqScore record);

    int updateByPrimaryKey(ZqScore record);

	List<ZqScore> queryAll(Map<String, Object> param);
}