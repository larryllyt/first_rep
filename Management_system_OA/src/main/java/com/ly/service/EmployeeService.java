package com.ly.service;

import com.ly.dto.ExportParamDto;
import com.ly.entity.Employee;
import com.ly.dto.EmpParamDto;
import com.ly.vo.EmployeeVo;

import java.util.List;

public interface EmployeeService{


    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<EmployeeVo> getEmpByPage(EmpParamDto empParamDto);

    List<EmployeeVo> getALl();
}
