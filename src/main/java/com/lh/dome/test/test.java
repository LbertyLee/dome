package com.lh.dome.test;

import cn.dev33.satoken.annotation.SaIgnore;
import com.lh.dome.common.domain.RespResult;

import com.lh.dome.framework.annotation.Idempotent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class test {


    /**
     * 测试接口幂等功能
     *
     * @return {@link RespResult}
     */
    @SaIgnore
    @GetMapping
    @Idempotent(key = "demo",expireSeconds = 5)
    public RespResult tests(){
        return RespResult.success("测试通过");
    }
}
