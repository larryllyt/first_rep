package com.ly.controller;

import com.ly.commons.Code;
import com.ly.entity.Employee;
import com.ly.service.EmployeeService;
import com.ly.dto.EmpParamDto;
import com.ly.vo.EmployeeVo;
import com.ly.vo.ResultVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName EmpController
 * @Description TODO 1.员工信息查询 getEmp[emp:query]
 *                   2.员工信息添加 addEmp[emp:update_delete_insert]
 *                   3.员工信息删除 cutEmp[emp:update_delete_insert]
 *                   4.员工信息更新 editEmp[emp:update_delete_insert]
 * @Author 赖昱
 * @Date 2023/5/17 - 20:47
 */
@RestController
@RequestMapping("/emp")
public class EmpController {

    @Resource
    private EmployeeService employeeService;

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('emp:query')")
    public ResultVo getEmp(@RequestBody EmpParamDto empParamDto){
        ResultVo resultVo = ResultVo.FAIL();
        // 参数有问题
        if (empParamDto == null
                || (empParamDto.getUsername() == null && empParamDto.getDeptId() == null)
        ){
            resultVo.setEnumCode(Code.NOT_FOUND_PARAM);
            return resultVo;
        }
        // 执行到这说明没有问题
        List<EmployeeVo> empList = employeeService.getEmpByPage(empParamDto);
        if (empList == null){
            resultVo.setEnumCode(Code.NOT_GET_PARAM);
            return resultVo;
        }
        resultVo.setEnumCode(Code.GET_SUCCESS);
        // 负载数据
        resultVo.setList(empList);
        return resultVo;
    }

    @PutMapping("/add")
    @PreAuthorize("hasAuthority('emp:update_delete_insert')")
    public ResultVo addEmp(@RequestBody Employee employee){
        ResultVo resultVo = ResultVo.FAIL();
        if (employee == null){
            resultVo.setEnumCode(Code.NOT_FOUND_PARAM);
            return resultVo;
        }
        // 参数没问题添加
        int result = employeeService.insertSelective(employee);
        if (result != 1){
            resultVo.setEnumCode(Code.ADD_FAIL);
            return resultVo;
        }
        // 添加成功
        resultVo.setEnumCode(Code.ADD_SUCCESS);
        return resultVo;
    }

    @DeleteMapping("/cut")
    @PreAuthorize("hasAuthority('emp:update_delete_insert')")
    public ResultVo cutEmp(@RequestBody Long id){
        ResultVo resultVo = ResultVo.FAIL();
        if (id == null){
            resultVo.setEnumCode(Code.NOT_FOUND_PARAM);
            return resultVo;
        }
        int result = employeeService.deleteByPrimaryKey(id);
        if (result != 1){
            resultVo.setEnumCode(Code.CUT_FAIL);
            return resultVo;
        }
        // 删除成功
        resultVo.setEnumCode(Code.CUT_SUCCESS);
        return resultVo;
    }

    @PatchMapping("/edit")
    @PreAuthorize("hasAuthority('emp:update_delete_insert')")
    public ResultVo editEmp(@RequestBody Employee employee){
        ResultVo resultVo = ResultVo.FAIL();
        if (employee == null){
            resultVo.setEnumCode(Code.NOT_FOUND_PARAM);
            return resultVo;
        }
        int result = employeeService.updateByPrimaryKeySelective(employee);
        if (result != 1){
            resultVo.setEnumCode(Code.EDIT_FAIL);
            return resultVo;
        }
        // 更新成功
        resultVo.setEnumCode(Code.EDIT_SUCCESS);
        return resultVo;
    }

}
