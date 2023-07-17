package com.lh.dome.system.service;

import com.lh.dome.system.domain.SysRole;

import java.util.List;

public interface SysRoleService {
    /**
     * 通过用户id获取角色列表
     *
     * @param loginId 登录id
     * @return {@link List}<{@link String}>
     */
    List<String> getRoleListByUserId(Object loginId);

    /**
     * 获取角色列表
     *
     * @return {@link List}<{@link SysRole}>
     */
    List<SysRole> getRoleList();

    /**
     * 得到用户角色
     *
     * @param userName 用户名
     * @return {@link List}<{@link SysRole}>
     */
    String getUserRole(String userName);
}
