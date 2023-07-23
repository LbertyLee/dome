package com.lh.demo.system.controller;

import com.lh.demo.common.domain.RespResult;
import com.lh.demo.system.domain.dto.SysUserDTO;
import com.lh.demo.system.service.SysUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加系统用户
     *
     * @param sysUserDTO 系统用户dto
     * @return {@link RespResult}
     */
    @PostMapping
    public RespResult addSystemUser(@RequestBody @Valid SysUserDTO sysUserDTO){
        sysUserService.addSystemUser(sysUserDTO);
        return RespResult.success();
    }

    /**
     * 查询系统用户列表
     *
     * @param sysUserDTO 系统用户dto
     * @return {@link RespResult}
     */
    @GetMapping("/list")
    public  RespResult getSystemUserList(SysUserDTO sysUserDTO){
        return  RespResult.success(sysUserService.getSystemUserList(sysUserDTO));
    }
}
