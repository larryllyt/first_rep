package com.ly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName SetRoleDto
 * @Description TODO 根据用户名设置权限数据传输类
 * @Author 赖昱
 * @Date 2023/5/19 - 4:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetRoleDto implements Serializable {

    private String username;

    private String RoleName;
}
