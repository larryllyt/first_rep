package com.ly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName PageParamDto
 * @Description TODO 分页查询页面参数
 * @Author 赖昱
 * @Date 2023/5/18 - 0:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParamDto implements Serializable {

    // 第几页
    private Integer pageNum = 1;

    // 一页可以装多少条记录
    private Integer pageSize = 5;

    // 被转换成的第几条
    private Integer pageNumByLimit = 0;

    /**
     * 将参数转换成limit子句的参数
     */
    public void pageParamToLimitParam(){
        pageNumByLimit = (pageNum - 1) * 5;
    }
}
