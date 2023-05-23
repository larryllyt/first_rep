package com.ly.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ly.dao.RoleUserMapper;
import com.ly.entity.RoleUser;
import com.ly.service.RoleUserService;
@Service
public class RoleUserServiceImpl implements RoleUserService{

    @Resource
    private RoleUserMapper roleUserMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return roleUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RoleUser record) {
        return roleUserMapper.insert(record);
    }

    @Override
    public int insertSelective(RoleUser record) {
        return roleUserMapper.insertSelective(record);
    }

    @Override
    public RoleUser selectByPrimaryKey(Long id) {
        return roleUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RoleUser record) {
        return roleUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RoleUser record) {
        return roleUserMapper.updateByPrimaryKey(record);
    }

}
