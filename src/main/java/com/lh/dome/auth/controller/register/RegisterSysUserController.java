package com.lh.dome.auth.controller.register;

import com.lh.dome.auth.domian.dto.PasswordRegisterDTO;
import com.lh.dome.common.domain.RespResult;
import com.lh.dome.system.service.SysUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/register")
public class RegisterSysUserController {

    @Resource
    SysUserService sysUserService;

    @PostMapping("/password")
    public RespResult registerSysUser(@RequestBody @Valid PasswordRegisterDTO passwordRegisterDTO){
        sysUserService.addSystemUser(passwordRegisterDTO);
        return RespResult.success();
    }
}
