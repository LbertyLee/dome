package com.lh.demo.system.service;

import com.lh.demo.system.domain.SysMenu;
import com.lh.demo.system.domain.vo.RouterVO;

import java.util.List;

public interface SysMenuService {
    /**
     * 获得权限列表
     *
     * @param loginId 登录id
     * @return {@link List}<{@link String}>
     */
    List<String> getPermissionList(Object loginId);

    List<RouterVO> getSysMenuList();

    /**
     * 获取前端路由所需要的菜单
     *
     * @param userId 用户id
     * @return {@link Object}
     */
    List<RouterVO> getRouters(Long userId);
}
