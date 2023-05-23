package com.ly.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    /**
    * 主键
    */
    private Long id;

    /**
    * 部门编号
    */
    private Long deptId;

    /**
    * 员工姓名
    */
    private String username;

    /**
    * 生日
    */
    private Date birthday;

    /**
    * 1男、0女
    */
    private String sex;

    /**
    * 手机
    */
    private String tel;

    /**
    * 薪资
    */
    private Double sal;

    /**
    * 1攻城狮、2程序猿、3，码龙
    */
    private String profession;

    /**
    * 员工住址
    */
    private String address;

    /**
    * 备注
    */
    private String remark;

    private static final long serialVersionUID = 1L;
}