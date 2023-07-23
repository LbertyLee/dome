package com.lh.demo.auth.controller.login;

import com.lh.demo.common.domain.RespResult;
import com.lh.demo.common.utils.SecurityUtils;
import com.lh.demo.system.domain.SysUser;
import com.lh.demo.system.service.SysRoleService;
import com.lh.demo.system.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取用户信息控制器
 *
 * @author lihong
 * @date 2023/07/23
 */
@RestController
@RequestMapping("/userinfo")
public class GetUserInfoController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    @GetMapping
    public RespResult GetUserInfo(){
        Long userId = SecurityUtils.getUserId();
        SysUser sysUser =sysUserService.selectSysUserInfo(userId);
        List<String> roles=sysRoleService.getRoleListByUserId();
        List<String> permissions=new ArrayList<>();
        HashMap<String, Object> UserInfo = new HashMap<>();
        UserInfo.put("sysUser",sysUser);
        UserInfo.put("roles",roles);
        UserInfo.put("permissions",permissions);
        return RespResult.success(UserInfo);
    }
}
