package com.ly.service.impl;

import com.ly.dao.AuthorityMapper;
import com.ly.vo.UserVo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ly.dao.UserMapper;
import com.ly.entity.User;
import com.ly.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthorityMapper authorityMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(UserVo userVo) {
        // 确定参数不为空
        if (userVo == null){
            return 0;
        }
        // 转换
        User user = UserVo.toUser(userVo);
        // 将名文加密成密文
        String encode = passwordEncoder.encode(user.getPassword());
        // set方法回填
        user.setPassword(encode);
        int i = userMapper.insertSelective(user);
        return i;
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserVo userVo) {
        if (userVo == null){
            return 0;
        }
        // 转换
        User user = UserVo.toUser(userVo);
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    /**
     * 通过用户名查询用户所有信息包括权限并封装到一个UserVo中
     * @param username
     * @return
     */
    @Override
    public UserVo getUserByUsername(String username) {
        if (username == null){
            return null;
        }
        // 查询账户基本信息
        User user = userMapper.selectByUsername(username);
        if (user == null){
            return null;
        }
        // 查询账户权限集合
        List<String> authList = authorityMapper.selectByUsername(user.getId());
        // 转换成交互类
        UserVo userVo = UserVo.toUserVo(user);
        // 设置权限
        userVo.setAuthorityList(authList);
        // 查询出来了返回
        return userVo;
    }

    /**
     * 通过用户名查询用户权限集合
     * @param username
     * @return
     */
    @Override
    public List<String> getAuthByUsername(String username) {
        if (username == null){
            return null;
        }
        // 查询user
        User user = userMapper.selectByUsername(username);
        // 用户不存在
        if (user == null){
            return null;
        }
        // 继续查到权限
        List<String> authList = authorityMapper.selectByUsername(user.getId());
        return authList;
    }

}
