package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.BasketBallMatch;

public interface BasketBallMatchMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(BasketBallMatch record);

    int insertSelective(BasketBallMatch record);

    BasketBallMatch selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(BasketBallMatch record);

    int updateByPrimaryKey(BasketBallMatch record);

	BasketBallMatch findMatchByMatchCode(String matchCode);
}