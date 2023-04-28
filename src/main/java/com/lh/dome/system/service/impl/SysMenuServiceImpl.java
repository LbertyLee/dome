package com.lh.dome.system.service.impl;

import com.lh.dome.system.mapper.SysMenuMapper;
import com.lh.dome.system.mapper.SysUserMapper;
import com.lh.dome.system.service.SysMenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;



    /**
     * 获得权限列表
     *
     * @param loginId 登录id
     * @return {@link List}<{@link String}>
     */
    @Override
    public List<String> getPermissionList(Object loginId) {
        return sysMenuMapper.getPermissionList(loginId);
    }
}
