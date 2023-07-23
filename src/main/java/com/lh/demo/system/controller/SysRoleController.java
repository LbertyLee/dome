package com.lh.demo.system.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.lh.demo.common.domain.RespResult;
import com.lh.demo.common.utils.SecurityUtils;
import com.lh.demo.system.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/role")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 获取用户角色信息
     *
     * @return {@link RespResult}
     */
    @SaCheckRole("admin")
    @GetMapping()
    public RespResult getSysRole(){
        return RespResult.success(sysRoleService.getRoleListByUserId());
    }

    /**
     * 获取系统角色列表
     *
     * @return {@link RespResult}
     */
    @GetMapping("/list")
    public RespResult getSysRoleList(){
        return RespResult.success(sysRoleService.getRoleList());

    }
}
