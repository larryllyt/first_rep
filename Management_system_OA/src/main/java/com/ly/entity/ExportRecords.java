package com.ly.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExportRecords implements Serializable {
    /**
    * 主键ID
    */
    private Long id;

    /**
    * 操作员名称
    */
    private String userName;

    /**
    * 导出时间
    */
    private Date exportTime;

    /**
    * 导出路径
    */
    private String exportUrl;

    /**
    * 备注
    */
    private String remark;

    private static final long serialVersionUID = 1L;
}