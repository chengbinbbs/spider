package com.dayspass.datacenter.service.football.match;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayspass.datacenter.dao.ZqStageMapper;
import com.dayspass.datacenter.domain.ZqStage;

@Service("zqStageService")
public class ZqStageService {

	@Autowired
	private ZqStageMapper zqStageMapper;
	
	public int deleteByPrimaryKey(Integer id){
		return zqStageMapper.deleteByPrimaryKey(id);
	}

	public int insertSelective(ZqStage record){
    	return zqStageMapper.insertSelective(record);
    }

	public ZqStage selectByPrimaryKey(Integer id){
    	return zqStageMapper.selectByPrimaryKey(id);
    }

	public int updateByPrimaryKeySelective(ZqStage record){
    	return zqStageMapper.updateByPrimaryKeySelective(record);
    }
	
	/**
	 * 条件查询
	 * @param param
	 * @return
	 */
	public List<ZqStage> queryAll(Map<String,Object> param){
		return zqStageMapper.queryAll(param);
	}

	/**
	 * 获取足彩近n期
	 * @author zhangcb
	 * @param param
	 * @return
	 */
	public List<ZqStage> queryZcStageList(Map<String, Object> param) {
		return zqStageMapper.queryZcStageList(param);
	}
	/**
	 * 获取北单近n期
	 * @author zhangcb
	 * @param param
	 * @return
	 */
	public List<ZqStage> queryBdStageList(Map<String, Object> param) {
		return zqStageMapper.queryBdStageList(param);
	}

	/**
	 * 更新当前期次，将当前期次置为0
	 * @author zhangcb
	 * @param param
	 */
	public int updateCurrentStage(Map<String, Object> param) {
		return zqStageMapper.updateCurrentStage(param);
	}

	public List<ZqStage> queryZucai() {
		return zqStageMapper.queryZucai();
	}

	public List<ZqStage> queryJcStageList(Map<String, Object> param) {
		return zqStageMapper.queryJcStageList(param);
	}


}
