package com.dayspass.datacenter.service.football.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqInjureMapper;
import com.dayspass.datacenter.domain.ZqInjure;

@Service("zqInjureService")
public class ZqInjureService {

	@Autowired
	private ZqInjureMapper zqInjureMapper;
	
	public int deleteByPrimaryKey(Integer id){
		return zqInjureMapper.deleteByPrimaryKey(id);
	}

	public int insertSelective(ZqInjure record){
    	return zqInjureMapper.insertSelective(record);
    }

	public ZqInjure selectByPrimaryKey(Integer id){
    	return zqInjureMapper.selectByPrimaryKey(id);
    }

	public int updateByPrimaryKeySelective(ZqInjure record){
    	return zqInjureMapper.updateByPrimaryKeySelective(record);
    }

	public ZqInjure selectByScheduleID(int scheduleid) {
		return zqInjureMapper.selectByScheduleID(scheduleid);
	}
}
