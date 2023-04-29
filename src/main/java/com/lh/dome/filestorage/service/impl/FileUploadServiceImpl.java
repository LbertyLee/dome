package com.lh.dome.filestorage.service.impl;

import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import com.lh.dome.filestorage.domain.vo.FileInfoVO;
import com.lh.dome.filestorage.service.FileUploadService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传服务impl
 *
 * @author lihong
 * @date 2023/04/28
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    /**
     * 文件存储服务
     */
    @Resource
    private FileStorageService fileStorageService;

    /**
     * 上传
     *
     * @param file 文件
     * @return {@code Object}
     */
    @Override
    public FileInfoVO upload(MultipartFile file) {
        FileInfo upload = fileStorageService.of(file).setPlatform("local-1").upload();
        FileInfoVO fileInfoVO = new FileInfoVO();
        BeanUtils.copyProperties(upload,fileInfoVO);
        return fileInfoVO;
    }
}
