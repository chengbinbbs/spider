package com.dayspass.datacenter.service.football.league;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqSclassMapper;
import com.dayspass.datacenter.domain.ZqSclass;

@Service("zqSclassService")
public class ZqSclassService {

	@Autowired
	private ZqSclassMapper zqSclassMapper;
	
	public int deleteByLeagueId(Integer leagueId){
		return zqSclassMapper.deleteByLeagueId(leagueId);
	}

	public int insertSelective(ZqSclass record){
    	return zqSclassMapper.insertSelective(record);
    }

	public ZqSclass selectByLeagueId(Integer leagueId){
    	return zqSclassMapper.selectByLeagueId(leagueId);
    }

	public int updateByPrimaryKeySelective(ZqSclass record){
    	return zqSclassMapper.updateByPrimaryKeySelective(record);
    }

	public List<ZqSclass> queryAll(Map<String, Object> params) {
		return zqSclassMapper.queryAll(params);
	}

}
