package com.ly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName ExportParamDto
 * @Description TODO 文件导出传输参数
 * @Author 赖昱
 * @Date 2023/5/19 - 5:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportParamDto implements Serializable {

    /**
     * 导出的文件名
     */
    private String fileName;

    /**
     * 导出的地址
     */
    private String path;
}
