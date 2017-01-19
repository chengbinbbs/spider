package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.FootBallMatch;

public interface FootBallMatchMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(FootBallMatch record);

    int insertSelective(FootBallMatch record);

    FootBallMatch selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(FootBallMatch record);

    int updateByPrimaryKey(FootBallMatch record);

	FootBallMatch findMatchByMatchCode(String matchCode);
}