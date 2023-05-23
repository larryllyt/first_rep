package com.ly.service;

import com.ly.entity.ExportRecords;
public interface ExportRecordsService{


    int deleteByPrimaryKey(Long id);

    int insert(ExportRecords record);

    int insertSelective(ExportRecords record);

    ExportRecords selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExportRecords record);

    int updateByPrimaryKey(ExportRecords record);

}
