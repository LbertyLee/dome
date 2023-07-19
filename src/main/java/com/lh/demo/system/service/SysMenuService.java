package com.lh.demo.system.service;

import java.util.List;

public interface SysMenuService {
    /**
     * 获得权限列表
     *
     * @param loginId 登录id
     * @return {@link List}<{@link String}>
     */
    List<String> getPermissionList(Object loginId);
}
