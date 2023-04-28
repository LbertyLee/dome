package com.lh.dome.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lh.dome.system.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 选择角色用户id列表
     *
     * @param userId 用户id
     * @return {@link List}<{@link String}>
     */
    List<String> selectRoleListByUserId(@Param("userId") Long userId);
}
