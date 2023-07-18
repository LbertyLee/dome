package com.lh.dome.test;


import cn.dev33.satoken.annotation.SaIgnore;
import com.lh.dome.common.redis.util.BloomFilterUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/bloom")
public class BloomFilterTest {

    @Resource
    BloomFilterUtil bloomFilterUtil;


    @GetMapping("/add")
    public String addToBloomFilter(@RequestParam String value) {
        long expectedInsertions = 100000; // 期望插入元素数量
        double falseProbability = 0.01; // 期望的误差率
        bloomFilterUtil.addToBloomFilter("my_bloom_filter", value, expectedInsertions, falseProbability);
        return "Added to Bloom Filter";
    }


    @GetMapping("/might")
    public String mightExistInBloomFilter(@RequestParam String value) {
        boolean exists = bloomFilterUtil.mightExistInBloomFilter("my_bloom_filter", value);
        if (exists) {
            return "Might exist in Bloom Filter";
        } else {
            return "Definitely does not exist in Bloom Filter";
        }
    }
}
