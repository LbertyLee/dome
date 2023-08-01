package com.lh.demo.test;

import cn.dev33.satoken.annotation.SaIgnore;
import com.lh.demo.common.domain.RespResult;
import com.lh.demo.system.service.SysUserService;
import jakarta.annotation.Resource;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/excel")
public class ExcelTest {
    @Resource
    private SysUserService sysUserService;

    @SaIgnore
    @GetMapping
    public RespResult test(HttpServletResponse response){
        sysUserService.exportSysUser(response,"系统用户信息表");
        return RespResult.success();
    }
}
