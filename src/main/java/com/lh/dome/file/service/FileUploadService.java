package com.lh.dome.file.service;

import com.lh.dome.file.domain.vo.FileInfoVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件细节服务
 *
 * @author lihong
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
