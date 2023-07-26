package com.lh.demo.system.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.demo.auth.domian.dto.PasswordLoginDTO;
import com.lh.demo.auth.domian.dto.PasswordRegisterDTO;
import com.lh.demo.common.domain.PaginateData;
import com.lh.demo.common.domain.RespResult;
import com.lh.demo.common.exception.ServiceException;
import com.lh.demo.common.utils.PaginateUtils;
import com.lh.demo.common.utils.SecurityUtils;
import com.lh.demo.common.utils.uuid.IdUtils;
import com.lh.demo.system.domain.SysRole;
import com.lh.demo.system.domain.SysUserRole;
import com.lh.demo.system.domain.vo.SysUserVO;
import com.lh.demo.system.mapper.SysUserRoleMapper;
import org.springframework.beans.BeanUtils;
import com.lh.demo.system.domain.SysUser;
import com.lh.demo.system.domain.dto.SysUserDTO;
import com.lh.demo.system.mapper.SysUserMapper;
import com.lh.demo.system.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;


    /**
     * （用户名+密码）注册系统用户
     *
     * @param passwordRegisterDTO 密码注册dto
     */
    @Override
    public void registerSystemUser(PasswordRegisterDTO passwordRegisterDTO) {
        Boolean res= sysUserMapper.checkSystemUserNameExist(passwordRegisterDTO.getUserName());
        if(res){
            throw new ServiceException("该用户名已存在,请重新输入");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(passwordRegisterDTO,sysUser);
        this.insertSystemUser(sysUser);
    }

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
                value -> querySysUserWrapper.like(SysUser::getUserName,sysUserDTO.getUserName()));
        //根据昵称进行模糊查询
        Optional.ofNullable(sysUserDTO.getNickname()).ifPresent(
                value -> querySysUserWrapper.like(SysUser::getNickname,sysUserDTO.getNickname()));
        Page<SysUser> sysUserPage = sysUserMapper.selectPage(page, querySysUserWrapper);

        return PaginateUtils.build(page,sysUserPage.getRecords());
    }

    /**
     * 根据用户id查询系统用户
     *
     * @param userid 用户标识
     * @return {@link SysUserVO}
     */
    @Override
    public SysUser getSystemUserByUserid(Long userid) {
        return this.sysUserMapper.selectById(userid);
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
     * 根据用户id系统用户信息
     *
     * @param userId 用户id
     * @return {@link SysUser}
     */
    @Override
    public SysUser selectSysUserInfoByUserId(Long userId) {
        return this.sysUserMapper.selectById(userId);
    }

    /**
     * 添加系统用户
     *
     * @param sysUserDTO 系统用户dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSystemUser(SysUserDTO sysUserDTO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDTO,sysUser);
        sysUser.setId(IdUtils.randomSnowflake());
        //新增系统用户
        this.insertSystemUser(sysUser);
        //添加角色关联
        SysUserRole sysUserRole = new SysUserRole().setUserId(sysUser.getId()).setRoleId(sysUserDTO.getRoleId());
        this.sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    public void updateSystemUser(SysUserDTO sysUserDTO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDTO,sysUser);
        this.updateSysUser(sysUser);
        //修改角色关联
        SysUserRole sysUserRole = new SysUserRole().setRoleId(sysUserDTO.getRoleId()).setUserId(sysUserDTO.getId());
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId,sysUserRole.getUserId());
        this.sysUserRoleMapper.update(sysUserRole,queryWrapper);
    }

    /**
     * 删除系统用户
     *
     * @param userIds 用户id
     */
    @Override
    public void deleteSystemUser(List<Long> userIds) {
        this.sysUserMapper.deleteBatchIds(userIds);

    }

    /**
     * 插入系统用户
     *
     * @param sysUser 系统用户
     */
    public void insertSystemUser(SysUser sysUser) {
        //检查用户名是否存在
        Boolean res= sysUserMapper.checkSystemUserNameExist(sysUser.getUserName());
        if(res){
            throw new ServiceException("该用户名已存在,请重新输入");
        }
        sysUser.setCreateTime(DateTime.now()).setUpdateTime(DateTime.now())
                //用户密码加密
                .setPassword(SecurityUtils.encodeBcrypt(sysUser.getPassword()));
        this.sysUserMapper.insert(sysUser);
    }

    /**
     * 更新系统用户
     *
     * @param sysUser 系统用户
     */
    public void updateSysUser(SysUser sysUser){
        LambdaQueryWrapper<SysUser> sysUserLambdaUpdateChainWrapper = new LambdaQueryWrapper<>();
        sysUserLambdaUpdateChainWrapper.eq(SysUser::getId,sysUser.getId());
        this.sysUserMapper.update(sysUser,sysUserLambdaUpdateChainWrapper);
    }

}
