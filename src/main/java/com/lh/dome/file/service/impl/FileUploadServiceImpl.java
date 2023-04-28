package com.lh.dome.file.service.impl;

import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import com.lh.dome.file.domain.vo.FileInfoVO;
import com.lh.dome.file.service.FileUploadService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private FileStorageService fileStorageService;

//    @Value("${spring.file-storage.default-platform}")
//    private String defaultPlatform;




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
        BeanUtils.copyProperties(upload,new FileInfoVO());
        return fileInfoVO;
    }
}
