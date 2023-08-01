package com.lh.demo.system.service;

import com.lh.demo.auth.domian.dto.PasswordLoginDTO;
import com.lh.demo.auth.domian.dto.PasswordRegisterDTO;
import com.lh.demo.common.domain.PaginateData;
import com.lh.demo.common.domain.RespResult;
import com.lh.demo.system.domain.SysUser;
import com.lh.demo.system.domain.dto.SysUserDTO;
import com.lh.demo.system.domain.vo.SysUserVO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * 系统用户服务
 *
 * @  lh
 * @date 2023/04/27
 */
public interface SysUserService   {
    /**
     * 系统用户注册
     *
     * @param passwordRegisterDTO 密码注册dto
     */
    void registerSystemUser(PasswordRegisterDTO passwordRegisterDTO);

    /**
     * 登陆时查询系统用户
     *
     * @param passwordLoginDTO 密码登录dto
     * @return {@link SysUser}
     */
    SysUser selectSystemUser(PasswordLoginDTO passwordLoginDTO);

    /**
     * 查询系统用户列表
     *
     * @param sysUserDTO 系统用户dto
     * @return {@link RespResult}
     */
    PaginateData<SysUser> getSystemUserList(SysUserDTO sysUserDTO);


    /**
     * 查询系统用户信息
     *
     * @param userid 用户标识
     * @return {@link SysUserVO}
     */
    SysUser getSystemUserByUserid(Long userid);

    /**
     * 根据用户id系统用户信息
     *
     * @param userId 用户id
     * @return {@link SysUser}
     */
    SysUser selectSysUserInfoByUserId(Long userId);

    /**
     * 添加系统用户
     *
     * @param sysUserDTO 系统用户dto
     */
    void addSystemUser(SysUserDTO sysUserDTO);

    /**
     * 更新系统用户
     *
     * @param sysUserDTO 系统用户dto
     */
    void updateSystemUser(SysUserDTO sysUserDTO);

    /**
     * 删除系统用户
     *
     * @param userIds 用户id
     */
    void deleteSystemUser(List<Long> userIds);

    /**
     * 导出系统用户
     *
     * @param response 响应
     * @param fileName 文件名称
     */
    void exportSysUser(HttpServletResponse response, String fileName);


}
