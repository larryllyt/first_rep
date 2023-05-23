package com.ly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName EmpParamDto
 * @Description TODO 员工查询
 * @Author 赖昱
 * @Date 2023/5/18 - 8:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpParamDto extends PageParamDto {

    /**
     * 部门编号
     */
    private Long deptId;

    /**
     * 员工姓名
     */
    private String username;
}
