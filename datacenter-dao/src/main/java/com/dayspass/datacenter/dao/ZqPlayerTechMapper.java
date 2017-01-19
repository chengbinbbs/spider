package com.dayspass.datacenter.dao;

import java.util.List;
import java.util.Map;

import com.dayspass.datacenter.domain.ZqPlayerTech;

public interface ZqPlayerTechMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZqPlayerTech record);

    int insertSelective(ZqPlayerTech record);

    ZqPlayerTech selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZqPlayerTech record);

    int updateByPrimaryKey(ZqPlayerTech record);

	List<ZqPlayerTech> queryAll(Map<String, Object> param);
}