package com.lh.demo.filestorage.service.impl;

import cn.hutool.core.date.DateTime;
import cn.xuyanwu.spring.file.storage.FileInfo;
import com.lh.demo.common.exception.FileStorageException;
import com.lh.demo.common.utils.AESExample;
import com.lh.demo.common.utils.StringUtils;
import com.lh.demo.filestorage.constant.FileDetailStatus;
import com.lh.demo.filestorage.domain.FileDetail;
import com.lh.demo.filestorage.domain.vo.FileInfoVO;
import com.lh.demo.filestorage.mapper.FileDetailMapper;
import com.lh.demo.filestorage.service.FileDetailService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * 文件详细服务impl
 *
 * @  lihong
 * @date 2023/05/15
 */
@Slf4j
@Service
public class FileDetailServiceImpl  implements FileDetailService  {

    @Resource
    private FileDetailMapper fileDetailMapper;

    @Value("${spring.storage.bucket-name}")
    private String bucketName;


    @Value("${spring.storage.region}")
    private String Region;

    @Value("${spring.storage.domain}")
    private String  domain;


    @Value("${spring.storage.secretId}")
    private String secretId;

    @Value("${spring.storage.secretKey}")
    private String secretKey;

    /**
     *保存上传记录
     * @param fileInfo 文件信息
     * @return {@code String}
     */
    @SneakyThrows
    @Override
    public boolean record(FileInfo fileInfo) {
        FileDetail fileDetail = new FileDetail();
        BeanUtils.copyProperties(fileInfo,fileDetail);
        fileDetail.setUrl(AESExample.encrypt(fileInfo.getUrl()))
                .setCreateTime(DateTime.now()).setUpdateTime(DateTime.now());
        return fileDetailMapper.insert(fileDetail)==1;
    }

    /**
     * 激活文件
     *
     * @param fileId 文件标识
     * @return {@code Boolean}
     */
    @Override
    public Boolean activeFile(List<String> fileId) {
        if(StringUtils.isEmpty(fileId)){
            throw new FileStorageException("文件ID不能为空");
        }
        fileDetailMapper.updateListById(fileId);
        return true;
    }

    /**
     * 获取文件信息
     *
     * @param fileId 文件标识
     * @return {@code FileInfo}
     */
    @Override
    public List<FileInfoVO> getFileInfo(List<String> fileId) {
        List<FileDetail> fileDetails = fileDetailMapper.selectBatchIds(fileId);
        return fileDetails.stream()
                .filter (f-> !Objects.equals(f.getStatus(), FileDetailStatus.INACTIVE))
                .map(f -> {
                    FileInfoVO fileInfoVO = new FileInfoVO();
                    BeanUtils.copyProperties(f, fileInfoVO);
                    fileInfoVO.setUrl(AESExample.decrypt(f.getUrl()));
                    return fileInfoVO;
                }).toList();
    }
}
