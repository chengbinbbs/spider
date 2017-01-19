package com.dayspass.datacenter.service.football.league;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqSubSclassMapper;
import com.dayspass.datacenter.domain.ZqSubSclass;

@Service("zqSubSclassService")
public class ZqSubSclassService {

	@Autowired
	private ZqSubSclassMapper zqSubSclassMapper;
	
	public int deleteByPrimaryKey(Integer subsclassid){
		return zqSubSclassMapper.deleteByPrimaryKey(subsclassid);
	}

	public int insertSelective(ZqSubSclass record){
    	return zqSubSclassMapper.insertSelective(record);
    }

	public ZqSubSclass selectByPrimaryKey(Integer subsclassid){
    	return zqSubSclassMapper.selectByPrimaryKey(subsclassid);
    }

	public int updateByPrimaryKeySelective(ZqSubSclass record){
    	return zqSubSclassMapper.updateByPrimaryKeySelective(record);
    }

	public List<ZqSubSclass> queryAll(Map<String, Object> maps) {
		return zqSubSclassMapper.queryAll(maps);
	}

}
