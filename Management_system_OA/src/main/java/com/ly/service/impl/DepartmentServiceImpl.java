package com.ly.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ly.entity.Department;
import com.ly.dao.DepartmentMapper;
import com.ly.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        if (id == null){
            return 0;
        }
        int i = departmentMapper.deleteByPrimaryKey(id);
        if (i == 0){
            return 0;
        }
        return 1;
    }

    @Override
    public int insert(Department record) {
        return departmentMapper.insert(record);
    }

    @Override
    public int insertSelective(Department record) {
        if (record == null){
            return 0;
        }
        // 添加
        int i = departmentMapper.insertSelective(record);
        // i == 0说明添加失败
        if (i == 0){
            return 0;
        }
        // 说明添加成功
        return 1;
    }

    @Override
    public Department selectByPrimaryKey(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Department record) {
        if (record == null){
            return 0;
        }
        int i = departmentMapper.updateByPrimaryKeySelective(record);
        if (i == 0){
            return 0;
        }
        return 1;
    }

    @Override
    public int updateByPrimaryKey(Department record) {
        return departmentMapper.updateByPrimaryKey(record);
    }

    /**
     * 查询所有部门信息
     * @return
     */
    @Override
    public List<Department> getAll() {
        // 直接查询
        List<Department> deptList = departmentMapper.selectAll();
        if (deptList == null){
            return null;
        }
        return deptList;
    }

}
