package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.ZqSubSclassInSeason;

public interface ZqSubSclassInSeasonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZqSubSclassInSeason record);

    int insertSelective(ZqSubSclassInSeason record);

    ZqSubSclassInSeason selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZqSubSclassInSeason record);

    int updateByPrimaryKey(ZqSubSclassInSeason record);
}