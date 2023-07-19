package com.lh.demo.filestorage.service;

import com.lh.demo.filestorage.domain.vo.FileInfoVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件细节服务
 *
 * @  lihong
 * @date 2023/04/28
 */
public interface FileUploadService {
    /**
     * 上传
     *
     * @param file 文件
     * @return {@code Object}
     */
    FileInfoVO upload(MultipartFile file);
}
