package com.lh.dome.common.redis.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson配置
 *
 * @author lihong
 * @date 2023/07/18
 */
@Configuration
@ConfigurationProperties(prefix = "spring.data.redis")
@Data
public class RedissonConfig {

    /*** 主机*/
    private String host;

    /***端口*/
    private String port;

    /*** 密码*/
    private String  password;

    /**数据库*/
    private int database;

    /**
     * redisson协议前缀
     */
    private static final String SCHEMA_PREFIX = "redis://";

    /**
     * redisson客户端
     *
     * @return {@link RedissonClient}
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        String address = SCHEMA_PREFIX + host + ":" + port;
        config.useSingleServer()
                .setAddress(address)
                .setDatabase(10);
        return Redisson.create(config);
    }

}
