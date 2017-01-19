package com.dayspass.datacenter.service.football.score;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqScoreMapper;
import com.dayspass.datacenter.domain.ZqScore;

@Service("zqScoreService")
public class ZqScoreService {

	@Autowired
	private ZqScoreMapper zqScoreMapper;
	
	public int deleteByPrimaryKey(Integer id){
		return zqScoreMapper.deleteByPrimaryKey(id);
	}

	public int insertSelective(ZqScore record){
    	return zqScoreMapper.insertSelective(record);
    }

	public ZqScore selectByPrimaryKey(Integer id){
    	return zqScoreMapper.selectByPrimaryKey(id);
    }

	public int updateByPrimaryKeySelective(ZqScore record){
    	return zqScoreMapper.updateByPrimaryKeySelective(record);
    }

	public List<ZqScore> queryAll(Map<String, Object> param) {
		return zqScoreMapper.queryAll(param);
	}
}
