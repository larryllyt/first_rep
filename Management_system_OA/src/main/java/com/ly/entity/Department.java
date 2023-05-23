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
public class Department implements Serializable {
    /**
    * 主键ID
    */
    private Long id;

    /**
    * 部门代号
    */
    private String departmentCode;

    /**
    * 部门名称
    */
    private String departmentName;

    /**
    * 部门地址
    */
    private String address;

    /**
    * 备注
    */
    private String remark;

    private static final long serialVersionUID = 1L;
}