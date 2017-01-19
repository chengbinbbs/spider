package com.dayspass.datacenter.service.football.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqPlayerMapper;
import com.dayspass.datacenter.domain.ZqPlayer;

@Service("zqPlayerService")
public class ZqPlayerService {

	@Autowired
	private ZqPlayerMapper zqPlayerMapper;
	
	public int deleteByPrimaryKey(Integer playerID){
		return zqPlayerMapper.deleteByPrimaryKey(playerID);
	}

	public int insertSelective(ZqPlayer record){
    	return zqPlayerMapper.insertSelective(record);
    }

	public ZqPlayer selectByPrimaryKey(Integer playerID){
    	return zqPlayerMapper.selectByPrimaryKey(playerID);
    }

	public int updateByPrimaryKeySelective(ZqPlayer record){
    	return zqPlayerMapper.updateByPrimaryKeySelective(record);
    }

}
