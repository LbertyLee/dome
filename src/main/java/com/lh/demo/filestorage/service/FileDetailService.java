package com.lh.demo.filestorage.service;

import cn.xuyanwu.spring.file.storage.FileInfo;
import com.lh.demo.filestorage.domain.vo.FileInfoVO;

import java.util.List;

public interface FileDetailService {


    /**
     *保存上传记录
     * @param fileInfo 文件信息
     * @return {@code String}
     */
    boolean record(FileInfo fileInfo);

    /**
     * 激活文件
     *
     * @param fileId 文件标识
     * @return {@code Boolean}
     */
    Boolean activeFile(List<String>  fileId);

    /**
     * 获取文件信息
     *
     * @param fileId 文件标识
     * @return {@code FileInfo}
     */
    List<FileInfoVO>  getFileInfo(List<String> fileId);
}
