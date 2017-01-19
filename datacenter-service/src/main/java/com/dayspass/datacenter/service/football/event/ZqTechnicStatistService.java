package com.dayspass.datacenter.service.football.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqTechnicStatistMapper;
import com.dayspass.datacenter.domain.ZqTechnicStatist;

@Service("zqTechnicStatistService")
public class ZqTechnicStatistService {

	@Autowired
	private ZqTechnicStatistMapper zqTechnicStatistMapper;
	
	public int deleteByPrimaryKey(Integer id){
		return zqTechnicStatistMapper.deleteByPrimaryKey(id);
	}

	public int insertSelective(ZqTechnicStatist record){
    	return zqTechnicStatistMapper.insertSelective(record);
    }

	public ZqTechnicStatist selectByPrimaryKey(Integer id){
    	return zqTechnicStatistMapper.selectByPrimaryKey(id);
    }

	public int updateByPrimaryKeySelective(ZqTechnicStatist record){
    	return zqTechnicStatistMapper.updateByPrimaryKeySelective(record);
    }

	public ZqTechnicStatist selectByScheduleId(int sid) {
		return zqTechnicStatistMapper.selectByScheduleId(sid);
	}
}
