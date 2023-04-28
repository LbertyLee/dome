package com.lh.dome.system.service.impl;


import com.lh.dome.common.utils.SecurityUtils;
import com.lh.dome.system.domain.SysRole;
import com.lh.dome.system.mapper.SysRoleMapper;
import com.lh.dome.system.service.SysRoleService;
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
}
