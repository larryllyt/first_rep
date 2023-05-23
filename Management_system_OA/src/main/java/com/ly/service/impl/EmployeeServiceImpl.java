package com.ly.service.impl;

import com.ly.dto.EmpParamDto;
import com.ly.vo.EmployeeVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ly.dao.EmployeeMapper;
import com.ly.entity.Employee;
import com.ly.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        if (id == null){
            return 0;
        }
        int i = employeeMapper.deleteByPrimaryKey(id);
        if (i == 0){
            return 0;
        }
        return 1;
    }

    @Override
    public int insert(Employee record) {
        return employeeMapper.insert(record);
    }

    @Override
    public int insertSelective(Employee record) {
        if (record == null){
            return 0;
        }
        // 添加
        int i = employeeMapper.insertSelective(record);
        // i == 0说明添加失败
        if (i == 0){
            return 0;
        }
        // 说明添加成功
        return 1;
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Employee record) {
        if (record == null){
            return 0;
        }
        int i = employeeMapper.updateByPrimaryKeySelective(record);
        if (i == 0){
            return 0;
        }
        return 1;
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        return employeeMapper.updateByPrimaryKey(record);
    }

    /**
     * 根据员工姓名或者部门编号查询员工信息并封装到List<EmployeeVo>
     * @param empParamDto
     * @return
     */
    @Override
    public List<EmployeeVo> getEmpByPage(EmpParamDto empParamDto) {
        // 转化参数
        empParamDto.pageParamToLimitParam();
        List<EmployeeVo> empList = employeeMapper.selectByPage(empParamDto);
        // 没有数据
        if (empList == null){
            return null;
        }
        return empList;
    }

    /**
     * 查询所有员工信息并封装到List<EmployeeVo>
     * @return
     */
    @Override
    public List<EmployeeVo> getALl() {
        // 直接查所有
        List<EmployeeVo> empList = employeeMapper.getAll();
        // 如果表没有数据
        if (empList == null){
            return null;
        }
        return empList;
    }


}
