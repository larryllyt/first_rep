package com.ly.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ly.dao.ExportRecordsMapper;
import com.ly.entity.ExportRecords;
import com.ly.service.ExportRecordsService;
@Service
public class ExportRecordsServiceImpl implements ExportRecordsService{

    @Resource
    private ExportRecordsMapper exportRecordsMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return exportRecordsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ExportRecords record) {
        return exportRecordsMapper.insert(record);
    }

    @Override
    public int insertSelective(ExportRecords exportRecords) {
        if (exportRecords == null){
            return 0;
        }
        int i = exportRecordsMapper.insertSelective(exportRecords);
        return i;
    }

    @Override
    public ExportRecords selectByPrimaryKey(Long id) {
        return exportRecordsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ExportRecords record) {
        return exportRecordsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ExportRecords record) {
        return exportRecordsMapper.updateByPrimaryKey(record);
    }

}
