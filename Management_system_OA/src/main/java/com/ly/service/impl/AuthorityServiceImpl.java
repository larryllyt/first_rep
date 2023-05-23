package com.ly.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ly.dao.AuthorityMapper;
import com.ly.entity.Authority;
import com.ly.service.AuthorityService;
@Service
public class AuthorityServiceImpl implements AuthorityService{

    @Resource
    private AuthorityMapper authorityMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return authorityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Authority record) {
        return authorityMapper.insert(record);
    }

    @Override
    public int insertSelective(Authority record) {
        return authorityMapper.insertSelective(record);
    }

    @Override
    public Authority selectByPrimaryKey(Long id) {
        return authorityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Authority record) {
        return authorityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Authority record) {
        return authorityMapper.updateByPrimaryKey(record);
    }

}
