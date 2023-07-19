package com.lh.demo.common.redis.util;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * redisson布隆过滤器
 *
 * @author lihong
 * @date 2023/07/18
 */
@Component
public class BloomFilterUtil {

//    private final RedissonClient redissonClient;
//
//    @Autowired
//    public BloomFilterUtil(RedissonClient redissonClient) {
//        this.redissonClient = redissonClient;
//    }

    @Autowired
    private RedissonClient redissonClient;
    /**
     * 向布隆过滤器中添加数据
     *
     * @param bloomFilterName    布隆过滤器名字
     * @param value              价值
     * @param expectedInsertions 预期插入
     * @param falseProbability   错误概率
     */
    public void addToBloomFilter(String bloomFilterName, String value,long expectedInsertions,double falseProbability) {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(bloomFilterName);
        bloomFilter.tryInit(expectedInsertions, falseProbability);
        bloomFilter.add(value);
    }

    /**
     * 检查查询的数据是否在布隆过滤器中
     *
     * @param bloomFilterName 布隆过滤器名字
     * @param value           价值
     * @return boolean
     */
    public boolean mightExistInBloomFilter(String bloomFilterName, String value) {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(bloomFilterName);
        return bloomFilter.contains(value);
    }

}
