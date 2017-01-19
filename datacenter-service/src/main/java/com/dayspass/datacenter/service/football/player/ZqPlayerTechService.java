package com.dayspass.datacenter.service.football.player;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqPlayerTechMapper;
import com.dayspass.datacenter.domain.ZqPlayerTech;

@Service("zqPlayerTechService")
public class ZqPlayerTechService {

	@Autowired
	private ZqPlayerTechMapper zqPlayerTechMapper;
	
	public int deleteByPrimaryKey(Integer id){
		return zqPlayerTechMapper.deleteByPrimaryKey(id);
	}

	public int insertSelective(ZqPlayerTech record){
    	return zqPlayerTechMapper.insertSelective(record);
    }

	public ZqPlayerTech selectByPrimaryKey(Integer id){
    	return zqPlayerTechMapper.selectByPrimaryKey(id);
    }

	public int updateByPrimaryKeySelective(ZqPlayerTech record){
    	return zqPlayerTechMapper.updateByPrimaryKeySelective(record);
    }

	public List<ZqPlayerTech> queryAll(Map<String, Object> param) {
		return zqPlayerTechMapper.queryAll(param);
	}
}
