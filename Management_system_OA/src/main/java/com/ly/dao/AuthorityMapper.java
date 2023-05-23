package com.ly.dao;

import com.ly.entity.Authority;

import java.util.List;

public interface AuthorityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

    List<String> selectByUsername(Long uid);
}