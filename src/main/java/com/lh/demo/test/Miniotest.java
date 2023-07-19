package com.lh.demo.test;

import com.lh.demo.common.domain.RespResult;

import com.lh.demo.common.minio.config.MinIoConfig;
import com.lh.demo.common.minio.util.MinioUtil;
import com.lh.demo.framework.annotation.Idempotent;

import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test/minio")
public class Miniotest {

    @Resource
    private MinioUtil minioUtil;

    @Resource
    private MinIoConfig minioConfig;


    /**
     * 测试接口幂等功能
     *
     * @return {@link RespResult}
     */

    @GetMapping
    @Idempotent(key = "demo",expireSeconds = 5)
    public RespResult tests(){
        return RespResult.success("测试通过");
    }

    /**
     * minio上传
     *
     * @param file 文件
     * @return {@link RespResult}
     */

    @PostMapping("/upload")
    public RespResult minioUpload(MultipartFile file, @RequestParam(required = false) String bucketName){
        bucketName = StringUtils.hasLength(bucketName) ? bucketName : minioConfig.getDefaultBucketName();
        String objectName = minioUtil.getDatePath() + file.getOriginalFilename();
        HashMap<String, String> objectInfo = new HashMap<>();
        try {
            minioUtil.upload(bucketName, objectName, file);
            String viewPath = minioUtil.getPresignedObjectUrl(bucketName, objectName, 1, TimeUnit.HOURS);
            objectInfo.put("objectName", objectName);
            //只能预览图片、txt等部分文件
            objectInfo.put("viewPath", viewPath);
        }catch (Exception e){
            throw new RuntimeException("文件上传失败");
        }
        return RespResult.success(objectInfo);
    }
    /**
     * 下载文件
     * @param bucketName 桶名称
     * @param objectName 对象名称
     * @param response  相应结果
     */
    @GetMapping("/download")
    public void downLoad(@RequestParam(required = false) String bucketName, String objectName, HttpServletResponse response) {
        // 获取文件
        minioUtil.downResponse(bucketName,objectName,response);
    }

    @DeleteMapping(value = "/delete")
    public RespResult deleteByPath(@RequestParam(required = false) String bucketName, String objectName) {
        bucketName = StringUtils.hasLength(bucketName) ? bucketName : minioConfig.getDefaultBucketName();
        minioUtil.delete(bucketName, objectName);
        return RespResult.success("删除成功");
    }


}
