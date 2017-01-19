package com.dayspass.datacenter.service.football.league;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqCupMatchMapper;
import com.dayspass.datacenter.domain.ZqCupMatch;

@Service("zqCupMatchService")
public class ZqCupMatchService {

	@Autowired
	private ZqCupMatchMapper zqCupMatchMapper;
	
	public int deleteByPrimaryKey(Integer id){
		return zqCupMatchMapper.deleteByPrimaryKey(id);
	}

	public int insertSelective(ZqCupMatch record){
    	return zqCupMatchMapper.insertSelective(record);
    }

	public ZqCupMatch selectByPrimaryKey(Integer id){
    	return zqCupMatchMapper.selectByPrimaryKey(id);
    }

	public int updateByPrimaryKeySelective(ZqCupMatch record){
    	return zqCupMatchMapper.updateByPrimaryKeySelective(record);
    }

	public List<ZqCupMatch> queryAll(Map<String, Object> cupparam) {
		return zqCupMatchMapper.queryAll(cupparam);
	}
}
