package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.ZqScoreExplain;

public interface ZqScoreExplainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZqScoreExplain record);

    int insertSelective(ZqScoreExplain record);

    ZqScoreExplain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZqScoreExplain record);

    int updateByPrimaryKey(ZqScoreExplain record);
}