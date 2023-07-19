package com.lh.demo.system.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lh.demo.auth.domian.dto.PasswordLoginDTO;
import com.lh.demo.auth.domian.dto.PasswordRegisterDTO;
import com.lh.demo.common.exception.ServiceException;
import com.lh.demo.common.utils.SecurityUtils;
import com.lh.demo.common.utils.uuid.IdUtils;
import org.springframework.beans.BeanUtils;
import com.lh.demo.system.domain.SysUser;
import com.lh.demo.system.domain.dto.SysUserDTO;
import com.lh.demo.system.mapper.SysUserMapper;
import com.lh.demo.system.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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

    @Override
    public void addSystemUser(PasswordRegisterDTO passwordRegisterDTO) {
       addSystemUser(passwordRegisterDTO);
    }

    /**
     * 查询用户谢谢
     *
     * @param passwordLoginDTO 密码登录dto
     * @return {@link SysUser}
     */
    @Override
    public SysUser selectSystemUser(PasswordLoginDTO passwordLoginDTO) {
        return sysUserMapper.selectOne(
                new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, passwordLoginDTO.getUsername()));
    }

    @Override
    public SysUser selectSysUserByUserName(String username) {
        return  sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName,username));
    }
}
