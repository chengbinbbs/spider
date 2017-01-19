package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.ZqTechnicStatist;

public interface ZqTechnicStatistMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(ZqTechnicStatist record);

    ZqTechnicStatist selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZqTechnicStatist record);

    int updateByPrimaryKey(ZqTechnicStatist record);

    ZqTechnicStatist selectByScheduleId(int scheduleID);


}