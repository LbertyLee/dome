package com.lh.demo.common.service;

import cn.dev33.satoken.stp.StpInterface;
import com.lh.demo.system.service.SysMenuService;
import com.lh.demo.system.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

    /*** 系统角色服务*/
    @Resource
    SysRoleService sysRoleService;

    @Resource
    SysMenuService sysMenuService;



    /**
     * 查询用户的权限列表
     *
     * @param loginId   登录id
     * @param loginType 登录类型
     * @return {@link List}<{@link String}>
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return sysMenuService.getPermissionList(loginId);
    }

    /**
     * 获取用户的角色列表
     *
     * @param loginId   登录id
     * @param loginType 登录类型
     * @return {@link List}<{@link String}>
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType)
    {
        return sysRoleService.StpInterfaceImpl(loginId);
    }

}
