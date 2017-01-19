package com.dayspass.datacenter.service.football.event;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqDetailResultMapper;
import com.dayspass.datacenter.domain.ZqDetailResult;

@Service("zqDetailResultService")
public class ZqDetailResultService {

	@Autowired
	private ZqDetailResultMapper zqDetailResultMapper;
	
    public int deleteByPrimaryKey(Integer id){
    	return zqDetailResultMapper.deleteByPrimaryKey(id);
    }

    public int insertSelective(ZqDetailResult record){
    	return zqDetailResultMapper.insertSelective(record);
    }

    public ZqDetailResult selectByPrimaryKey(Integer id){
    	return zqDetailResultMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ZqDetailResult record){
    	return zqDetailResultMapper.updateByPrimaryKeySelective(record);
    }

	public List<ZqDetailResult> queryAll(Map<String, Object> param) {
		return zqDetailResultMapper.queryAll(param);
	}

	public int deleteDetailResult(Integer sid, List<Integer> ids) {
		return zqDetailResultMapper.deleteDetailResult(sid, ids);
	}

}
