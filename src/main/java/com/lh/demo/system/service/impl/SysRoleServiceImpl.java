package com.lh.demo.system.service.impl;


import com.lh.demo.common.utils.SecurityUtils;
import com.lh.demo.system.domain.SysRole;
import com.lh.demo.system.mapper.SysRoleMapper;
import com.lh.demo.system.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;


    @Override
    public List<String> getRoleListByUserId(Object loginId) {
        return sysRoleMapper.selectRoleListByUserId(SecurityUtils.getUserId());
    }

    @Override
    public List<SysRole> getRoleList() {
        return null;
    }

    @Override
    public String getUserRole(String userName) {
        return sysRoleMapper.selectUserRoleByUserName(userName);
    }
}
