package com.lh.demo.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.demo.common.domain.PaginateData;
import com.lh.demo.common.utils.PaginateUtils;
import com.lh.demo.common.utils.SecurityUtils;
import com.lh.demo.common.utils.StringUtils;
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


    /**
     * 根据用户ID获取用户的角色列表
     *
     * @return {@link List}<{@link String}>
     */
    @Override
    public List<String> getRoleListByUserId() {
        return sysRoleMapper.selectRoleListByUserId(SecurityUtils.getUserId());
    }

    /**
     * stp接口impl
     *
     * @param loginId 登录id
     * @return {@link List}<{@link String}>
     */
    @Override
    public List<String> StpInterfaceImpl(Object loginId) {
        return sysRoleMapper.selectRoleListByUserId(SecurityUtils.getUserId());
    }

    /**
     * 获取系统角色列表
     *
     * @return {@link List}<{@link SysRole}>
     */
    @Override
    public PaginateData<SysRole> getRoleList() {
        Page<SysRole> Page = PaginateUtils.startPage();
        LambdaQueryWrapper<SysRole> sysRoleQueryWrapper = new LambdaQueryWrapper<>();
        sysRoleQueryWrapper.eq(SysRole::getDelFlag,0);
        List<SysRole> records = sysRoleMapper.selectPage(Page, sysRoleQueryWrapper).getRecords();
        return PaginateUtils.build(Page,records);
    }

    @Override
    public String getUserRole(String userName) {
        return sysRoleMapper.selectUserRoleByUserName(userName);
    }
}
