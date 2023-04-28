package com.lh.dome.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lh.dome.system.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 获得权限列表
     *
     * @param loginId 登录id
     * @return {@link List}<{@link String}>
     */
    List<String> getPermissionList(Object loginId);
}
