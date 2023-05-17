package com.lh.dome.filestorage.controller;

import com.lh.dome.common.domain.RespResult;
import com.lh.dome.filestorage.service.FileUploadService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * @author lihong
 * @date 2023/04/28
 */

@RestController
@RequestMapping("/file/upload")
@Valid
public class FileUploadController {
    /**
     * 文件上传服务
     */
    @Resource
    FileUploadService fileUploadService;

    /**
     * 文件上传
     *
     * @param file 文件
     * @return {@code RespResult}
     */
    @PostMapping
    public RespResult fileUpload(@RequestParam("file") MultipartFile file){
        return RespResult.success(fileUploadService.upload(file));
    }


}
