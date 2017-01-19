package com.dayspass.datacenter.dao;

import java.util.List;
import java.util.Map;

import com.dayspass.datacenter.domain.ZqPlayerDetail;

public interface ZqPlayerDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZqPlayerDetail record);

    int insertSelective(ZqPlayerDetail record);

    ZqPlayerDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZqPlayerDetail record);

    int updateByPrimaryKey(ZqPlayerDetail record);

	List<ZqPlayerDetail> queryAll(Map<String, Object> param);
}