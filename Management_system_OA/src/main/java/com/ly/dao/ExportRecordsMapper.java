package com.ly.dao;

import com.ly.entity.ExportRecords;

public interface ExportRecordsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExportRecords record);

    int insertSelective(ExportRecords record);

    ExportRecords selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExportRecords record);

    int updateByPrimaryKey(ExportRecords record);
}