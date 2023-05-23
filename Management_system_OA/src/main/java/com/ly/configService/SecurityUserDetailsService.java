package com.ly.configService;

import com.ly.dao.AuthorityMapper;
import com.ly.dao.UserMapper;
import com.ly.entity.User;
import com.ly.vo.SecurityUserVo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SecurityUserDetailsService
 * @Description TODO 自定义用户详情服务接口
 * @Author 赖昱
 * @Date 2023/5/17 - 1:38
 */
@Component
public class SecurityUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthorityMapper authorityMapper;

    /**
     * 通过用户名获取用户详情信息类
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.通过用户名查询出用户信息
        User user = userMapper.selectByUsername(username);
        // 如果user == null
        if (user == null){
            throw new UsernameNotFoundException("账户不存在");
        }
        // 2.通过用户ID查询用户的权限存到一个List<String>中
        List<String> authList = authorityMapper.selectByUsername(user.getId());
        // 3.放到自定义用户信息类
        SecurityUserVo securityUser = new SecurityUserVo(user);
        // 4.将我们这里读到的权限集合放入到用户详情授权集合中
        // 写入权限
        securityUser.setAuthorityList(authList);
        // 5.返回这个用户详情信息类
        return securityUser;
    }
}
