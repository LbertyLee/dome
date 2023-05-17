package com.lh.dome.filestorage.quartz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lh.dome.common.utils.AESExample;
import com.lh.dome.common.utils.StringUtils;
import com.lh.dome.filestorage.constant.FileDetailStatus;
import com.lh.dome.filestorage.domain.FileDetail;
import com.lh.dome.filestorage.mapper.FileDetailMapper;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.exception.MultiObjectDeleteException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.DeleteObjectsRequest;
import com.qcloud.cos.model.DeleteObjectsResult;
import com.qcloud.cos.region.Region;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class StorageSchedule {

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
     * 删除24小时内未激活的文件(每小时的20分执行)
     */
    @Scheduled(cron = "0 20 * * * ?")
    public void deleteInactiveFile() {
        //创建 COSClient 实例
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig();
        // 设置 bucket 的地域
        clientConfig.setRegion(new Region(Region));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        clientConfig.setSocketTimeout(30*1000);
        // 设置建立连接超时，默认 30s
        clientConfig.setConnectionTimeout(30*1000);
        // 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        //删除
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);
        ArrayList<DeleteObjectsRequest.KeyVersion> keyList = new ArrayList<>();
        //查询创建时间是1小时前且未激活的文件信息
        LambdaQueryWrapper<FileDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileDetail::getStatus, FileDetailStatus.INACTIVE)
                .lt(FileDetail::getCreateTime, LocalDateTime.now().minus(24, ChronoUnit.HOURS));
        List<FileDetail> fileDetails = fileDetailMapper.selectList(queryWrapper);
       //删除COS中文件
        if(StringUtils.isNotEmpty(fileDetails)){
            //添加多个需要删除的key
            fileDetails.forEach(f->{
                String url = f.getUrl();
                String key= AESExample.decrypt(url).replaceFirst("^" + domain, "");
                keyList.add((new DeleteObjectsRequest.KeyVersion(key)));
            });
            deleteObjectsRequest.setKeys(keyList);
            try {
                DeleteObjectsResult deleteObjectsResult = cosClient.deleteObjects(deleteObjectsRequest);
                List<DeleteObjectsResult.DeletedObject> deleteObjectResultArray = deleteObjectsResult.getDeletedObjects();
            } catch (MultiObjectDeleteException mde) {
                // 如果部分删除成功部分失败, 返回 MultiObjectDeleteException
                List<DeleteObjectsResult.DeletedObject> deleteObjects = mde.getDeletedObjects();
                List<MultiObjectDeleteException.DeleteError> deleteErrors = mde.getErrors();
            } catch (CosClientException e) {
                e.printStackTrace();
            }
            int i = fileDetailMapper.deleteBatchIds(fileDetails);
            log.info("<定时任务:>删除{}个文件",i);
        }else {
            log.info("<定时任务:>删除0个文件");
        }
        //确认本进程不再使用 cosClient 实例之后，关闭之
        cosClient.shutdown();
    }


}
