package com.ly.vo;

import com.ly.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserVo
 * @Description TODO 账号信息交互类
 * @Author 赖昱
 * @Date 2023/5/18 - 13:14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 账户是否启用 启用1；未启用0
     */
    private String enabled;

    /**
     * 账户是否过期
     */
    private String accountNoExpired;

    /**
     * 密码是否过期
     */
    private String credentialsNoExpired;

    /**
     * 账户是否锁定
     */
    private String accountNoLocked;

    /**
     * 权限集合
     */
    private List<String> authorityList;

    /**
     * 将一个user转成userVo
     * @param user
     * @return
     */
    public static UserVo toUserVo(User user){
        UserVo userVo = new UserVo();
        // 设置主键 用户名 密码
        userVo.setId(user.getId());
        userVo.setUsername(user.getUsername());
        userVo.setPassword(user.getPassword());
        // 账户状态转换
        if (user.getEnabled() == 1){
            userVo.setEnabled("启用");
        }else {
            userVo.setEnabled("未启用");
        }
        // 账户是否过期转换
        if (user.getAccountNoExpired() == 1){
            userVo.setAccountNoExpired("未过期");
        }else {
            userVo.setAccountNoExpired("已过期");
        }
        // 密码是否过期转换
        if (user.getCredentialsNoExpired() == 1){
            userVo.setCredentialsNoExpired("未过期");
        }
        else {
            userVo.setCredentialsNoExpired("已过期");
        }
        // 账户是否锁定转换
        if (user.getAccountNoLocked() == 1){
            userVo.setAccountNoLocked("未锁定");
        }else {
            userVo.setAccountNoLocked("已锁定");
        }
        return userVo;
    }

    /**
     * 将一个userVo转成user
     * @return
     */
    public static User toUser(UserVo userVo){
        User user = new User();
        // 设置进去
        user.setId(userVo.getId());
        user.setUsername(userVo.getUsername());
        user.setPassword(userVo.getPassword());
        // 设置状态转换
        if (!userVo.getEnabled().equals("启用")){
            user.setEnabled(0);
        }else {
            user.setEnabled(1);
        }
        if (!userVo.getCredentialsNoExpired().equals("未过期")){
            user.setCredentialsNoExpired(0);
        }else {
            user.setCredentialsNoExpired(1);
        }
        if (!userVo.getAccountNoExpired().equals("未过期")){
            user.setAccountNoExpired(0);
        }else {
            user.setAccountNoExpired(1);
        }
        if (!userVo.getAccountNoLocked().equals("未锁定")){
            user.setAccountNoLocked(0);
        }else {
            user.setAccountNoLocked(1);
        }
        return user;
    }
}
