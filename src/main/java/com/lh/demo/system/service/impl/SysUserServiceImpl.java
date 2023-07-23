package com.lh.demo.system.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.demo.auth.domian.dto.PasswordLoginDTO;
import com.lh.demo.auth.domian.dto.PasswordRegisterDTO;
import com.lh.demo.common.domain.PaginateData;
import com.lh.demo.common.domain.RespResult;
import com.lh.demo.common.exception.ServiceException;
import com.lh.demo.common.utils.PaginateUtils;
import com.lh.demo.common.utils.SecurityUtils;
import com.lh.demo.common.utils.uuid.IdUtils;
import org.springframework.beans.BeanUtils;
import com.lh.demo.system.domain.SysUser;
import com.lh.demo.system.domain.dto.SysUserDTO;
import com.lh.demo.system.mapper.SysUserMapper;
import com.lh.demo.system.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 系统用户服务实现类
 *
 * @  lh
 * @date 2023/04/27
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;


    /**
     * 查询系统用户列表
     *
     * @param sysUserDTO 系统用户dto
     * @return {@link RespResult}
     */
    @Override
    public PaginateData<SysUser> getSystemUserList(SysUserDTO sysUserDTO) {
        Page<SysUser> page = PaginateUtils.startPage();
        LambdaQueryWrapper<SysUser> querySysUserWrapper = new LambdaQueryWrapper<>();
        //根据用户名进行模糊查询
        Optional.ofNullable(sysUserDTO.getUserName()).ifPresent(
                value -> querySysUserWrapper.like(SysUser::getUserName,sysUserDTO.getUserName())
        );
        //根据昵称进行木户查询
        Optional.ofNullable(sysUserDTO.getNickname()).ifPresent(
                value -> querySysUserWrapper.like(SysUser::getNickname,sysUserDTO.getNickname())
        );
        Page<SysUser> sysUserPage = sysUserMapper.selectPage(page, querySysUserWrapper);
        return PaginateUtils.build(page,sysUserPage.getRecords());
    }


    /**
     * 新增系统用户
     *
     * @param sysUserDTO 系统用户dto
     */
    @Override
    public void addSystemUser(SysUserDTO sysUserDTO) {
        Boolean res= sysUserMapper.checkSystemUserExist(sysUserDTO);
        if(res){
            throw new ServiceException("该用户名已存在,请重新输入");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDTO,sysUser);
        sysUser.setId(IdUtils.randomSnowflake()).setCreateTime(DateTime.now()).setUpdateTime(DateTime.now())
                //用户密码加密
                .setPassword(SecurityUtils.encodeBcrypt(sysUserDTO.getPassword()));
        int insert = sysUserMapper.insert(sysUser);
        if(insert!=1){
            throw new SecurityException("添加用户失败");
        }
    }

    /**
     * 注册系统用户
     *
     * @param passwordRegisterDTO 密码注册dto
     */
    @Override
    public void registerSystemUser(PasswordRegisterDTO passwordRegisterDTO) {
        SysUserDTO sysUserDTO = new SysUserDTO();
        sysUserDTO.setUserName(passwordRegisterDTO.getUserName())
                  .setPassword(passwordRegisterDTO.getPassword())
                  .setNickname(passwordRegisterDTO.getUserName());
        this.addSystemUser(sysUserDTO);
    }

    /**
     * 查询系统用户
     *
     * @param passwordLoginDTO 密码登录dto
     * @return {@link SysUser}
     */
    @Override
    public SysUser selectSystemUser(PasswordLoginDTO passwordLoginDTO) {
        return sysUserMapper.selectOne(
                new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, passwordLoginDTO.getUsername()));
    }

    /**
     * 根据用户名查询系统用户信息
     *
     * @param username 用户名
     * @return {@link SysUser}
     */
    @Override
    public SysUser selectSysUserByUserName(String username) {
        return  sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName,username));
    }


    @Override
    public SysUser selectSysUserInfo(Long userId) {
        return this.sysUserMapper.selectById(userId);
    }



}
