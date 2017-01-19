package com.dayspass.datacenter.service.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.FootBallMatchMapper;
import com.dayspass.datacenter.domain.FootBallMatch;

@Service("footBallMatchService")
public class FootBallMatchService {

	@Autowired
	private FootBallMatchMapper footBallMatchMapper;
	
	public int deleteByPrimaryKey(Integer ID){
		return footBallMatchMapper.deleteByPrimaryKey(ID);
	}

	public int insertSelective(FootBallMatch record){
    	return footBallMatchMapper.insertSelective(record);
    }

	public FootBallMatch selectByPrimaryKey(Integer ID){
    	return footBallMatchMapper.selectByPrimaryKey(ID);
    }

	public int updateByPrimaryKeySelective(FootBallMatch record){
    	return footBallMatchMapper.updateByPrimaryKeySelective(record);
    }
	
	public FootBallMatch findMatchByMatchCode(String matchCode){
    	return footBallMatchMapper.findMatchByMatchCode(matchCode);
    }

	public int addOrUpdateMatch(FootBallMatch match) {
		FootBallMatch footmatch = footBallMatchMapper.findMatchByMatchCode(match.getMatchCode());
		if(footmatch == null)
		{
			return footBallMatchMapper.insertSelective(match);
		}
		match.setID(footmatch.getID());
		return footBallMatchMapper.updateByPrimaryKeySelective(match);
	}
	

}
