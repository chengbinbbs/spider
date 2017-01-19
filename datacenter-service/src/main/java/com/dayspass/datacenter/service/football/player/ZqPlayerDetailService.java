package com.dayspass.datacenter.service.football.player;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqPlayerDetailMapper;
import com.dayspass.datacenter.domain.ZqPlayerDetail;

@Service("zqPlayerDetailService")
public class ZqPlayerDetailService {

	@Autowired
	private ZqPlayerDetailMapper zqPlayerDetailMapper;
	
	public int deleteByPrimaryKey(Integer id){
		return zqPlayerDetailMapper.deleteByPrimaryKey(id);
	}

	public int insertSelective(ZqPlayerDetail record){
    	return zqPlayerDetailMapper.insertSelective(record);
    }

	public ZqPlayerDetail selectByPrimaryKey(Integer id){
    	return zqPlayerDetailMapper.selectByPrimaryKey(id);
    }

	public int updateByPrimaryKeySelective(ZqPlayerDetail record){
    	return zqPlayerDetailMapper.updateByPrimaryKeySelective(record);
    }

	public List<ZqPlayerDetail> queryAll(Map<String, Object> param) {
		return zqPlayerDetailMapper.queryAll(param);
	}
}
