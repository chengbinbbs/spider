package com.dayspass.datacenter.dao;

import com.dayspass.datacenter.domain.Issue;

public interface IssueMapper {
    int deleteByPrimaryKey(Long ID);

    int insert(Issue record);

    int insertSelective(Issue record);

    Issue selectByPrimaryKey(Long ID);

    int updateByPrimaryKeySelective(Issue record);

    int updateByPrimaryKey(Issue record);
}