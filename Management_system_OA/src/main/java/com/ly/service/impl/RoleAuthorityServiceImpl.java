package com.ly.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ly.dao.RoleAuthorityMapper;
import com.ly.entity.RoleAuthority;
import com.ly.service.RoleAuthorityService;
@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService{

    @Resource
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return roleAuthorityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RoleAuthority record) {
        return roleAuthorityMapper.insert(record);
    }

    @Override
    public int insertSelective(RoleAuthority record) {
        return roleAuthorityMapper.insertSelective(record);
    }

    @Override
    public RoleAuthority selectByPrimaryKey(Long id) {
        return roleAuthorityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RoleAuthority record) {
        return roleAuthorityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RoleAuthority record) {
        return roleAuthorityMapper.updateByPrimaryKey(record);
    }

}
