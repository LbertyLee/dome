package com.lh.dome.auth.controller.login;

import com.lh.dome.auth.domian.dto.PasswordLoginDTO;
import com.lh.dome.auth.service.LoginService;
import com.lh.dome.common.domain.RespResult;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {


    @Resource
    private LoginService loginService;

    /**
     * 密码登录
     *
     * @param passwordLoginDTO 密码登录dto
     * @return {@link RespResult}
     */
    @PostMapping("/password")
    public RespResult passwordLogin(@RequestBody @Valid PasswordLoginDTO passwordLoginDTO){
        return RespResult.success(loginService.passwordLogin(passwordLoginDTO));
    }


}
