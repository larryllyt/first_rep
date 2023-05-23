package com.ly.dao;

import com.ly.entity.Employee;
import com.ly.dto.EmpParamDto;
import com.ly.vo.EmployeeVo;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<EmployeeVo> selectByPage(EmpParamDto empParamDto);

    List<EmployeeVo> getAll();
}