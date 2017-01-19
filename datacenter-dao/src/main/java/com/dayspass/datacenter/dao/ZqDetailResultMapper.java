package com.dayspass.datacenter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dayspass.datacenter.domain.ZqDetailResult;

public interface ZqDetailResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZqDetailResult record);

    int insertSelective(ZqDetailResult record);

    ZqDetailResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZqDetailResult record);

    int updateByPrimaryKey(ZqDetailResult record);

	List<ZqDetailResult> queryAll(Map<String, Object> param);

	int deleteDetailResult(@Param("sid")Integer sid, @Param("ids")List<Integer> ids);
}