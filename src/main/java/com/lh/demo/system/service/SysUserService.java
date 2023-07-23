package com.lh.demo.system.service;

import com.lh.demo.auth.domian.dto.PasswordLoginDTO;
import com.lh.demo.auth.domian.dto.PasswordRegisterDTO;
import com.lh.demo.common.domain.PaginateData;
import com.lh.demo.common.domain.RespResult;
import com.lh.demo.system.domain.SysUser;
import com.lh.demo.system.domain.dto.SysUserDTO;

/**
 * 系统用户服务
 *
 * @  lh
 * @date 2023/04/27
 */
public interface SysUserService   {
    /**
     * 添加系统用户
     *
     * @param sysUserDTO 系统用户dto
     * @return int
     */
    void addSystemUser(SysUserDTO sysUserDTO);
    void registerSystemUser(PasswordRegisterDTO passwordRegisterDTO);

    /**
     * 选择一个
     *
     * @param passwordLoginDTO 密码登录dto
     * @return {@link SysUser}
     */
    SysUser selectSystemUser(PasswordLoginDTO passwordLoginDTO);

    SysUser selectSysUserByUserName(String username);

    /**
     * 查询系统用户列表
     *
     * @param sysUserDTO 系统用户dto
     * @return {@link RespResult}
     */
    PaginateData<SysUser> getSystemUserList(SysUserDTO sysUserDTO);

    SysUser selectSysUserInfo(Long userId);
}
