package com.lh.demo.auth.controller.login;

import com.lh.demo.auth.domian.dto.PasswordLoginDTO;
import com.lh.demo.auth.service.LoginService;
import com.lh.demo.common.domain.RespResult;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.bouncycastle.asn1.esf.SPuri;
import org.springframework.web.bind.annotation.*;


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
