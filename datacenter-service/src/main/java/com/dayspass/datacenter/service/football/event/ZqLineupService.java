package com.dayspass.datacenter.service.football.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqLineupMapper;
import com.dayspass.datacenter.domain.ZqLineup;

@Service("zqLineupService")
public class ZqLineupService {

	@Autowired
	private ZqLineupMapper zqLineupMapper;

	public int deleteByPrimaryKey(Integer id){
		return zqLineupMapper.deleteByPrimaryKey(id);
	}

	public int insertSelective(ZqLineup record){
		return zqLineupMapper.insertSelective(record);
	}

	public ZqLineup selectByPrimaryKey(Integer id){
		return zqLineupMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(ZqLineup record){
		return zqLineupMapper.updateByPrimaryKeySelective(record);
	}

	public ZqLineup selectByScheduleID(int scheduleid){
		return zqLineupMapper.selectByScheduleID(scheduleid);
	}
}
