package com.dayspass.datacenter.service.football.league;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqCupMatchGroupingMapper;
import com.dayspass.datacenter.domain.ZqCupMatchGrouping;

@Service("zqCupmatchGroupingService")
public class ZqCupmatchGroupingService {
	
	@Autowired
	private ZqCupMatchGroupingMapper zqCupmatchGroupingMapper;
	
	public int deleteByPrimaryKey(Integer groupid){
		return zqCupmatchGroupingMapper.deleteByPrimaryKey(groupid);
	}

	public int insertSelective(ZqCupMatchGrouping record){
    	return zqCupmatchGroupingMapper.insertSelective(record);
    }

	public ZqCupMatchGrouping selectByPrimaryKey(Integer groupid){
    	return zqCupmatchGroupingMapper.selectByPrimaryKey(groupid);
    }

	public int updateByPrimaryKeySelective(ZqCupMatchGrouping record){
    	return zqCupmatchGroupingMapper.updateByPrimaryKeySelective(record);
    }

	public List<ZqCupMatchGrouping> queryAll(Map<String, Object> param) {
		return zqCupmatchGroupingMapper.queryAll(param);
	}

}
