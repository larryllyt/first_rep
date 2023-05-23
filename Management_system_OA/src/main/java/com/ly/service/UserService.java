package com.ly.service;

import com.ly.entity.User;
import com.ly.vo.UserVo;

import java.util.List;

public interface UserService{


    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(UserVo userVo);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserVo userVo);

    int updateByPrimaryKey(User record);
    UserVo getUserByUsername(String username);

    List<String> getAuthByUsername(String username);
}
