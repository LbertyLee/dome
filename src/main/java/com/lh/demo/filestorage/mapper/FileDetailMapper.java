package com.lh.demo.filestorage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lh.demo.filestorage.domain.FileDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileDetailMapper extends BaseMapper<FileDetail> {
    int updateListById(@Param("fileId") List<String> fileId);
}
