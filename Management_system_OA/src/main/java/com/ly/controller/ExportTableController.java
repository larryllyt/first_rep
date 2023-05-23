package com.ly.controller;

import com.ly.commons.Code;
import com.ly.dto.ExportParamDto;
import com.ly.entity.Department;
import com.ly.entity.ExportRecords;
import com.ly.exportService.ExportService;
import com.ly.service.DepartmentService;
import com.ly.service.EmployeeService;
import com.ly.service.ExportRecordsService;
import com.ly.vo.EmployeeVo;
import com.ly.vo.ResultVo;
import com.ly.vo.SecurityUserVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ExportTableController
 * @Description TODO 1.导出所有部门信息 exportDept[dept:export]
 *                   2.导出所有员工信息 exportEmp[emp:export]
 * @Author 赖昱
 * @Date 2023/5/17 - 20:49
 */
@RestController
@RequestMapping("/export")
public class ExportTableController {

    @Resource
    private DepartmentService departmentService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private ExportRecordsService recordsService;

    @Resource
    private ExportService exportService;

    @GetMapping("/dept")
    @PreAuthorize("hasAuthority('dept:export')")
    public ResultVo exportDept(@RequestBody ExportParamDto exportParamDto, Authentication authentication){
        ResultVo resultVo = ResultVo.FAIL();
        // 1.准备好存储要导出表的信息的List
        List<Department> deptList = departmentService.getAll();
        if (deptList == null){
            resultVo.setEnumCode(Code.NOT_GET_PARAM);
            return resultVo;
        }
        // 2.调用信息导出服务类生成电子表
        Boolean falt = exportService.exportDept(exportParamDto, deptList);
        // 3.判断是否导出成功
        if (falt != true){
            resultVo.setEnumCode(Code.EXPORT_FAIL);
            return resultVo;
        }
        // 4.获取导出者名称
        /*从认证对象中获取 用户数据*/
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        SecurityUserVo securityUserVo = (SecurityUserVo) authenticationToken.getPrincipal();
        String username = securityUserVo.getUsername();
        ExportRecords exportRecords = new ExportRecords();
        // 将信息参数写入到导出记录传输类中
        exportRecords.setUserName(username);
        exportRecords.setExportUrl(exportParamDto.getPath());
        exportRecords.setExportTime(new Date());
        // 5.记录导出
        int i = recordsService.insertSelective(exportRecords);
        if (i != 1){
            resultVo.setEnumCode(Code.EXPORT_RECORDS_FAIL);
            return resultVo;
        }
        resultVo.setEnumCode(Code.EXPORT_SUCCESS);
        return resultVo;
    }

    @GetMapping("/emp")
    @PreAuthorize("hasAuthority('emp:export')")
    public ResultVo exportEmp(@RequestBody ExportParamDto exportParamDto, Authentication authentication){
        ResultVo resultVo = ResultVo.FAIL();
        // 1.查
        List<EmployeeVo> empList = employeeService.getALl();
        if (empList == null){
            resultVo.setEnumCode(Code.NOT_GET_PARAM);
            return resultVo;
        }
        // 2.调用信息导出服务类生成电子表
        Boolean flat = exportService.exportEmp(exportParamDto,empList);
        // 3.判断是否导出成功
        if (flat != true){
            resultVo.setEnumCode(Code.EXPORT_FAIL);
            return resultVo;
        }
        // 4.获取导出者名称
        /*从认证对象中获取 用户数据*/
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        SecurityUserVo securityUserVo = (SecurityUserVo) authenticationToken.getPrincipal();
        String username = securityUserVo.getUsername();
        ExportRecords exportRecords = new ExportRecords();
        // 将信息参数写入到导出记录传输类中
        exportRecords.setUserName(username);
        exportRecords.setExportUrl(exportParamDto.getPath());
        exportRecords.setExportTime(new Date());
        // 5.记录导出
        int i = recordsService.insertSelective(exportRecords);
        if (i != 1){
            resultVo.setEnumCode(Code.EXPORT_RECORDS_FAIL);
            return resultVo;
        }
        resultVo.setEnumCode(Code.EXPORT_SUCCESS);
        return resultVo;
    }
}
