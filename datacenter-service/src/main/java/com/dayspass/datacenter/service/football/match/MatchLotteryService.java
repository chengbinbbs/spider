package com.dayspass.datacenter.service.football.match;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqMatchLotteryMapper;
import com.dayspass.datacenter.domain.ZqMatchLottery;

@Service("matchLotteryService")
public class MatchLotteryService {

	@Autowired
	private ZqMatchLotteryMapper zqMatchLotteryMapper;
	
	public int deleteByPrimaryKey(Integer id){
		return zqMatchLotteryMapper.deleteByPrimaryKey(id);
	}

	public int insertSelective(ZqMatchLottery record){
    	return zqMatchLotteryMapper.insertSelective(record);
    }

	public ZqMatchLottery selectByPrimaryKey(Integer id){
    	return zqMatchLotteryMapper.selectByPrimaryKey(id);
    }

	public int updateByPrimaryKeySelective(ZqMatchLottery record){
    	return zqMatchLotteryMapper.updateByPrimaryKeySelective(record);
    }
	
	/**
	 * 条件查询
	 * @param param
	 * @return
	 */
	public List<ZqMatchLottery> queryAll(Map<String,Object> param){
		return zqMatchLotteryMapper.queryAll(param);
	}

	public List<ZqMatchLottery> queryMatchListByStage(Map<String, Object> param) {
		return zqMatchLotteryMapper.queryMatchListByStage(param);
	}

	public List<ZqMatchLottery> queryMatchLottery(Map<String, Object> param) {
		return zqMatchLotteryMapper.queryMatchLottery(param);
	}


}
