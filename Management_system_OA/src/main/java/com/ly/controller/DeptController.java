package com.ly.controller;

import com.ly.commons.Code;
import com.ly.entity.Department;
import com.ly.service.DepartmentService;
import com.ly.vo.ResultVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DeptController
 * @Description TODO 1.部门信息查询 getDept[dept:query]
 *                   2.部门信息添加 addDept[dept:update_delete_insert]
 *                   3.部门信息删除 cutDept[dept:update_delete_insert]
 *                   4.部门信息更新 editDept[dept:update_delete_insert]
 * @Author 赖昱
 * @Date 2023/5/17 - 20:47
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DepartmentService departmentService;

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('dept:query')")
    public ResultVo getEmp(){
        ResultVo resultVo = ResultVo.FAIL();
        // 部门较少就不做分页查询了直接放所有部门数据
        List<Department> deptList = departmentService.getAll();
        if (deptList == null){
            resultVo.setEnumCode(Code.NOT_GET_PARAM);
        }
        resultVo.setEnumCode(Code.GET_SUCCESS);
        // 负载数据
        resultVo.setList(deptList);
        return resultVo;
    }

    @PutMapping("/add")
    @PreAuthorize("hasAuthority('dept:update_delete_insert')")
    public ResultVo adddept(@RequestBody Department department){
        ResultVo resultVo = ResultVo.FAIL();
        if (department == null){
            resultVo.setEnumCode(Code.NOT_FOUND_PARAM);
            return resultVo;
        }
        // 参数没问题添加
        int result = departmentService.insertSelective(department);
        if (result != 1){
            resultVo.setEnumCode(Code.ADD_FAIL);
            return resultVo;
        }
        // 添加成功
        resultVo.setEnumCode(Code.ADD_SUCCESS);
        return resultVo;
    }

    @DeleteMapping("/cut")
    @PreAuthorize("hasAuthority('dept:update_delete_insert')")
    public ResultVo cutDept(@RequestBody Long id){
        ResultVo resultVo = ResultVo.FAIL();
        if (id == null){
            resultVo.setEnumCode(Code.NOT_FOUND_PARAM);
            return resultVo;
        }
        int result = departmentService.deleteByPrimaryKey(id);
        if (result != 1){
            resultVo.setEnumCode(Code.CUT_FAIL);
            return resultVo;
        }
        // 删除成功
        resultVo.setEnumCode(Code.CUT_SUCCESS);
        return resultVo;
    }

    @PatchMapping("/edit")
    @PreAuthorize("hasAuthority('dept:update_delete_insert')")
    public ResultVo editEmp(@RequestBody Department department){
        ResultVo resultVo = ResultVo.FAIL();
        if (department == null){
            resultVo.setEnumCode(Code.NOT_FOUND_PARAM);
            return resultVo;
        }
        int result = departmentService.updateByPrimaryKeySelective(department);
        if (result != 1){
            resultVo.setEnumCode(Code.EDIT_FAIL);
            return resultVo;
        }
        // 更新成功
        resultVo.setEnumCode(Code.EDIT_SUCCESS);
        return resultVo;
    }

}
