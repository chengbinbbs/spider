package com.dayspass.datacenter.service.football.player;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqPlayerInTeamMapper;
import com.dayspass.datacenter.domain.ZqPlayerInTeam;

@Service("zqPlayerInTeamService")
public class ZqPlayerInTeamService {

	@Autowired
	private ZqPlayerInTeamMapper zqPlayerInTeamMapper;
	
	public int deleteByPrimaryKey(Integer ID){
		return zqPlayerInTeamMapper.deleteByPrimaryKey(ID);
	}

	public int insertSelective(ZqPlayerInTeam record){
    	return zqPlayerInTeamMapper.insertSelective(record);
    }

	public ZqPlayerInTeam selectByPrimaryKey(Integer ID){
    	return zqPlayerInTeamMapper.selectByPrimaryKey(ID);
    }

	public int updateByPrimaryKeySelective(ZqPlayerInTeam record){
    	return zqPlayerInTeamMapper.updateByPrimaryKeySelective(record);
    }

	public List<ZqPlayerInTeam> queryAll(Map<String, Object> param) {
		return zqPlayerInTeamMapper.queryAll(param);
	}
}
