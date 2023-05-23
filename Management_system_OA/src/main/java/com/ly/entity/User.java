package com.ly.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
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
    private Integer enabled;

    /**
    * 账户是否过期
    */
    private Integer accountNoExpired;

    /**
    * 密码是否过期
    */
    private Integer credentialsNoExpired;

    /**
    * 账户是否锁定
    */
    private Integer accountNoLocked;

    private static final long serialVersionUID = 1L;
}