package com.ly.service;

import com.ly.entity.RoleAuthority;
public interface RoleAuthorityService{


    int deleteByPrimaryKey(Long id);

    int insert(RoleAuthority record);

    int insertSelective(RoleAuthority record);

    RoleAuthority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleAuthority record);

    int updateByPrimaryKey(RoleAuthority record);

}
