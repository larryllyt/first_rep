package com.ly.exportService;

import com.ly.dto.ExportParamDto;
import com.ly.entity.Department;
import com.ly.vo.EmployeeVo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExportService
 * @Description TODO 信息导出服务类
 * @Author 赖昱
 * @Date 2023/5/19 - 5:13
 */
@Service
public class ExportService {

    /**
     * 导出部门相关信息
     * @param exportParamDto 文件导出传输参数类
     * @param deptList 需要生成的信息集合
     * @return
     */
    public Boolean exportDept(ExportParamDto exportParamDto, List<Department> deptList) {
        // 创建excel表头
        List<String> column = new ArrayList<>();
        column.add("编号");
        column.add("部门代号");
        column.add("名称");
        column.add("地址");
        column.add("备注");
        // 表头对应的数据
        List<Map<String,Object>> data = new ArrayList<>();
        // 遍历获取到的需要导出的数据，k要和表头一样
        for (int i = 0; i < deptList.size(); i++){
            Map<String,Object> dataMap = new HashMap<>();
            // 将一行信息映射到一个map中
            Department department = deptList.get(i);
            dataMap.put("编号",department.getId());
            dataMap.put("部门代号",department.getDepartmentCode());
            dataMap.put("名称",department.getDepartmentName());
            dataMap.put("地址",department.getAddress());
            dataMap.put("备注",department.getRemark());
            data.add(dataMap);
        }
        //调用导出工具类
        Boolean flat = exportExcel(exportParamDto.getFileName(), exportParamDto.getPath(), column, data);
        return flat;
    }

    /**
     * 导出员工相关信息
     * @param exportParamDto 文件导出传输参数类
     * @param empList 需要生成的信息集合
     * @return
     */
    public Boolean exportEmp(ExportParamDto exportParamDto, List<EmployeeVo> empList) {
        // 创建excel表头
        List<String> column = new ArrayList<>();
        column.add("编号");
        column.add("部门名称");
        column.add("员工姓名");
        column.add("生日");
        column.add("性别");
        column.add("薪资");
        column.add("职业");
        column.add("员工住址");
        column.add("备注");
        // 表头对应的数据
        List<Map<String,Object>> data = new ArrayList<>();
        // 遍历获取到的需要导出的数据，k要和表头一样
        for (int i = 0; i < empList.size(); i++){
            Map<String,Object> dataMap = new HashMap<>();
            // 将一行信息映射到一个map中
            EmployeeVo employeeVo = empList.get(i);
            dataMap.put("编号",employeeVo.getId());
            dataMap.put("部门名称",employeeVo.getDeptName());
            dataMap.put("员工姓名",employeeVo.getUsername());
            dataMap.put("生日",employeeVo.getBirthday());
            dataMap.put("性别",employeeVo.getSex());
            dataMap.put("薪资",employeeVo.getSal());
            dataMap.put("职业",employeeVo.getProfession());
            dataMap.put("员工住址",employeeVo.getAddress());
            dataMap.put("备注",employeeVo.getRemark());
            data.add(dataMap);
        }
        // 调用服务
        Boolean flat = exportExcel(exportParamDto.getFileName(), exportParamDto.getPath(), column, data);
        return flat;
    }

    /**
     * 导出表方法
     * @param fileName
     * @param path
     * @param column
     * @param data
     */
    private Boolean exportExcel(String fileName, String path, List<String> column, List<Map<String, Object>> data) {
        String path1 = path + "\\" + fileName + ".xls";
        // 创建工作薄
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        // 创建sheet
        HSSFSheet sheet = hssfWorkbook.createSheet(fileName);
        // 表头
        HSSFRow headRow = sheet.createRow(0);
        for (int i = 0; i < column.size(); i++){
            headRow.createCell(i).setCellValue(column.get(i));
        }

        for (int i = 0; i < data.size(); i++) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            for (int x = 0; x < column.size(); x++) {
                dataRow.createCell(x).setCellValue(data.get(i).get(column.get(x))==null?"":data.get(i).get(column.get(x)).toString());
            }
        }
        Boolean flat = false;
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(path1);
            hssfWorkbook.write(fileOutputStream);
            fileOutputStream.close();
            flat = true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            return flat;
        }
    }
}
