package com.ly.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName EmployeeVo
 * @Description TODO 员工信息交互类
 * @Author 赖昱
 * @Date 2023/5/18 - 12:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVo implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 员工姓名
     */
    private String username;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别
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
}
