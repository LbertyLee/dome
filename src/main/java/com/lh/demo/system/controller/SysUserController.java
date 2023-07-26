package com.lh.demo.system.controller;

import com.lh.demo.common.domain.RespResult;
import com.lh.demo.system.domain.dto.SysUserDTO;
import com.lh.demo.system.service.SysUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统用户控制器
 *
 * @  lh
 * @date 2023/04/27
 */
@RestController
@RequestMapping("/system/users")
public class SysUserController {

    /** 系统用户服务*/
    @Resource
    private SysUserService sysUserService;

    /**
     * 查询系统用户列表
     *
     * @param sysUserDTO 系统用户dto
     * @return {@link RespResult}
     */
    @GetMapping("/list")
    public  RespResult getSystemUserList(SysUserDTO sysUserDTO){
        return  RespResult.success(sysUserService.getSystemUserList(sysUserDTO));
    }

    @GetMapping("/{userid}")
    public RespResult getSystemUserInfo(@PathVariable Long userid){
        return  RespResult.success(sysUserService.getSystemUserByUserid(userid));
    }

    /**
     * 添加系统用户
     *
     * @param sysUserDTO 系统用户dto
     * @return {@link RespResult}
     */
    @PostMapping
    public  RespResult addSystemUser(@RequestBody SysUserDTO sysUserDTO){
        sysUserService.addSystemUser(sysUserDTO);
        return  RespResult.success();
    }

    /**
     * 更新系统用户
     *
     * @param sysUserDTO 系统用户dto
     * @return {@link RespResult}
     */
    @PutMapping
    public RespResult updateSystemUser(@RequestBody SysUserDTO sysUserDTO){
        sysUserService.updateSystemUser(sysUserDTO);
        return  RespResult.success();
    }

    /**
     * 删除系统用户
     *
     * @param userIds 用户id
     * @return {@link RespResult}
     */
    @DeleteMapping("/{userIds}")
    public RespResult deleteSystemUser(@PathVariable List<Long> userIds){
        sysUserService.deleteSystemUser(userIds);
        return  RespResult.success();
    }
}



