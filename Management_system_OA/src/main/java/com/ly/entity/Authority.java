package com.ly.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements Serializable {
    /**
    * 主键ID
    */
    private Long id;

    /**
    * 权限code
    */
    private String authorityCode;

    /**
    * 权限名称
    */
    private String authorityName;

    /**
    * 备注
    */
    private String remark;

    private static final long serialVersionUID = 1L;
}