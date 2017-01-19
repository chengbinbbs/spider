package com.dayspass.datacenter.dao;

import java.util.List;
import java.util.Map;

import com.dayspass.datacenter.domain.ZqPlayerInTeam;

public interface ZqPlayerInTeamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZqPlayerInTeam record);

    int insertSelective(ZqPlayerInTeam record);

    ZqPlayerInTeam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZqPlayerInTeam record);

    int updateByPrimaryKey(ZqPlayerInTeam record);

	List<ZqPlayerInTeam> queryAll(Map<String, Object> param);
}