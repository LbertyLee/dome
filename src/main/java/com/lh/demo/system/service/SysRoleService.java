package com.lh.demo.system.service;

import com.lh.demo.common.domain.PaginateData;
import com.lh.demo.system.domain.SysRole;

import java.util.List;

public interface SysRoleService {
    /**
     * 通过用户id获取角色列表
     *
     * @return {@link List}<{@link String}>
     */
    List<String> getRoleListByUserId();
    List<String> StpInterfaceImpl(Object loginId);

    /**
     * 获取角色列表
     *
     * @return {@link List}<{@link SysRole}>
     */
    PaginateData<SysRole> getRoleList();

    /**
     * 得到用户角色
     *
     * @param userName 用户名
     * @return {@link List}<{@link SysRole}>
     */
    String getUserRole(String userName);
}
