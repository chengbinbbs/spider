package com.dayspass.datacenter.dao;

import java.util.List;
import java.util.Map;

import com.dayspass.datacenter.domain.ZqSclass;

public interface ZqSclassMapper {
    int deleteByLeagueId(Integer leagueId);

    int insert(ZqSclass record);

    int insertSelective(ZqSclass record);

    ZqSclass selectByLeagueId(Integer leagueId);

    int updateByPrimaryKeySelective(ZqSclass record);

    int updateByPrimaryKeyWithBLOBs(ZqSclass record);

    int updateByPrimaryKey(ZqSclass record);

	List<ZqSclass> queryAll(Map<String, Object> param);
}