package com.dayspass.datacenter.service.football.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqTeamMapper;
import com.dayspass.datacenter.domain.ZqTeam;

@Service("zqTeamService")
public class ZqTeamService {

	@Autowired
	private ZqTeamMapper zqTeamMapper;
	
	public int deleteByPrimaryKey(Integer teamID){
		return zqTeamMapper.deleteByPrimaryKey(teamID);
	}

	public int insertSelective(ZqTeam record){
    	return zqTeamMapper.insertSelective(record);
    }

	public ZqTeam selectByPrimaryKey(Integer teamID){
    	return zqTeamMapper.selectByPrimaryKey(teamID);
    }

	public int updateByPrimaryKeySelective(ZqTeam record){
    	return zqTeamMapper.updateByPrimaryKeySelective(record);
    }
}
