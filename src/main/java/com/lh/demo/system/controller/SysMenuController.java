package com.lh.demo.system.controller;

import com.lh.demo.common.domain.RespResult;
import com.lh.demo.system.service.SysMenuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统菜单控制器
 *
 * @author lihong
 * @date 2023/07/24
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController {

    /**系统菜单服务*/
    @Resource
    private SysMenuService sysMenuService;


    @GetMapping("/router")
    public RespResult getSysRouterList(){

        return RespResult.success(sysMenuService.getSysMenuList());
    }
}
