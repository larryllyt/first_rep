package com.ly.vo;

import com.ly.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SecurityUserVo
 * @Description TODO 自定义用户信息类
 * @Author 赖昱
 * @Date 2023/5/17 - 1:40
 */
public class SecurityUserVo implements UserDetails {

    @Resource
    private final User user;

    public User getUser() {
        return user;
    }

    private List<String> authorityList;

    public List<String> getAuthorityList() {
        return authorityList;
    }

    // 设置权限信息
    public void setAuthorityList(List<String> authorityList) {
        this.authorityList = authorityList;
    }

    // 调用该方法将创建安全用户信息
    public SecurityUserVo(User user) {
        this.user = user;
    }

    // 授权的集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorityList = authorityList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountNoExpired().equals(1);
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountNoLocked().equals(1);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getCredentialsNoExpired().equals(1);
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled().equals(1);
    }
}
