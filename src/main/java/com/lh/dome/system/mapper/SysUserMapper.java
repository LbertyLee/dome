package com.lh.dome.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lh.dome.system.domain.SysUser;
import com.lh.dome.system.domain.dto.SysUserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户映射器
 *
 * @author lh
 * @date 2023/04/27
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 检查系统用户是否存在
     *
     * @param sysUserDTO 查询包装
     * @return {@link Boolean}
     */
    Boolean checkSystemUserExist(SysUserDTO sysUserDTO);


}
