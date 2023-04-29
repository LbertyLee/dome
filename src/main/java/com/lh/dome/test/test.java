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


    @SaIgnore
    @GetMapping
    @Idempotent(value = "demo",expireSeconds = 5)
    public RespResult tests(){
        return RespResult.success("测试通过");
    }
}
