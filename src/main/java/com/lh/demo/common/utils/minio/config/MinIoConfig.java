package com.lh.demo.common.utils.minio.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinIoConfig {
    /**
     * minio部署的机器ip地址
     */
    private String endpoint;

    /**
     * minio使用的端口
     */
    private Integer port;

    /**
     *唯一标识的账户
     */
    private String accessKey;

    /**
     * 账户的密码
     */
    private String secretKey;

    /**
     * 如果是true，则用的是https而不是http,默认值是true
     */
    private Boolean secure;

    /**
     * 默认使用的桶名称
     */
    private String defaultBucketName;

    /**
     * 对象交给spring管理
     */
    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(endpoint , port , secure)
                .credentials(accessKey, secretKey)
                .build();
    }


}
