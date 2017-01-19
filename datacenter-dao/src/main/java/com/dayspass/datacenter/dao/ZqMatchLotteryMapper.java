package com.dayspass.datacenter.dao;

import java.util.List;
import java.util.Map;

import com.dayspass.datacenter.domain.ZqMatchLottery;

public interface ZqMatchLotteryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZqMatchLottery record);

    int insertSelective(ZqMatchLottery record);

    ZqMatchLottery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZqMatchLottery record);

    int updateByPrimaryKey(ZqMatchLottery record);

	List<ZqMatchLottery> queryAll(Map<String, Object> param);

	List<ZqMatchLottery> queryMatchListByStage(Map<String, Object> param);

	List<ZqMatchLottery> queryMatchLottery(Map<String, Object> param);
}