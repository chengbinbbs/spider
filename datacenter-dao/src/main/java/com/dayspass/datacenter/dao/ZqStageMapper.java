package com.dayspass.datacenter.dao;

import java.util.List;
import java.util.Map;

import com.dayspass.datacenter.domain.ZqStage;

public interface ZqStageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZqStage record);

    int insertSelective(ZqStage record);

    ZqStage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZqStage record);

    int updateByPrimaryKey(ZqStage record);

	List<ZqStage> queryAll(Map<String, Object> param);

	List<ZqStage> queryZcStageList(Map<String, Object> param);

	List<ZqStage> queryBdStageList(Map<String, Object> param);

	int updateCurrentStage(Map<String, Object> param);

	List<ZqStage> queryZucai();

	List<ZqStage> queryJcStageList(Map<String, Object> param);
}