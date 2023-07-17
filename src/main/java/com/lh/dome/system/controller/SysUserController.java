package com.lh.dome.system.controller;

import com.lh.dome.common.domain.RespResult;
import com.lh.dome.system.domain.dto.SysUserDTO;
import com.lh.dome.system.service.SysUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户控制器
 *
 * @  lh
 * @date 2023/04/27
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController {

    /** 系统用户服务*/
    @Resource
    private SysUserService sysUserService;

    @PostMapping
    public RespResult addSystemUser(@RequestBody @Valid SysUserDTO sysUserDTO){
        sysUserService.addSystemUser(sysUserDTO);
        return RespResult.success();
    }
}
