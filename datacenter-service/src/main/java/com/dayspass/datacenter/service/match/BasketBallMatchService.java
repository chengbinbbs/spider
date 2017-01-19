package com.dayspass.datacenter.service.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.BasketBallMatchMapper;
import com.dayspass.datacenter.domain.BasketBallMatch;

@Service("basketBallMatchService")
public class BasketBallMatchService {

	@Autowired
	private BasketBallMatchMapper basketBallMatchMapper;
	
	public int deleteByPrimaryKey(Integer ID){
		return basketBallMatchMapper.deleteByPrimaryKey(ID);
	}

	public int insertSelective(BasketBallMatch record){
		return basketBallMatchMapper.insertSelective(record);
	}

	public BasketBallMatch selectByPrimaryKey(Integer ID){
		return basketBallMatchMapper.selectByPrimaryKey(ID);
	}

	public int updateByPrimaryKeySelective(BasketBallMatch record){
		return basketBallMatchMapper.updateByPrimaryKeySelective(record);
	}

	public BasketBallMatch findMatchByMatchCode(String matchCode) {
		return basketBallMatchMapper.findMatchByMatchCode(matchCode);
	}

}
