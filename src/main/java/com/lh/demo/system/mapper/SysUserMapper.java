package com.lh.demo.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lh.demo.system.domain.SysUser;
import com.lh.demo.system.domain.dto.SysUserDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户映射器
 *
 * @  lh
 * @date 2023/04/27
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 检查系统用户是否存在
     *
     * @param userName 用户名
     * @return {@link Boolean}
     */
    Boolean checkSystemUserNameExist(String userName);


}
