package com.lh.dome.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.lh.dome.auth.domian.dto.PasswordLoginDTO;
import com.lh.dome.auth.domian.vo.LoginDataVO;
import com.lh.dome.auth.service.LoginService;
import com.lh.dome.common.constant.ErrorCodeConstants;
import com.lh.dome.common.exception.AuthException;
import com.lh.dome.common.exception.ServiceException;
import com.lh.dome.common.utils.SecurityUtils;
import com.lh.dome.common.utils.StringUtils;
import com.lh.dome.system.domain.SysUser;
import com.lh.dome.system.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * 登录服务impl
 *
 * @  lh
 * @date 2023/04/27
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private SysUserService  sysUserService;
    @Override
    public LoginDataVO passwordLogin(PasswordLoginDTO passwordLoginDTO) {
        SysUser sysUser= sysUserService.selectSystemUser(passwordLoginDTO);
        if(StringUtils.isNull(sysUser)){
            throw new AuthException("账号或密码错误", ErrorCodeConstants.INVALID_USERNAME_OR_PASSWORD);
        }
        String password = passwordLoginDTO.getPassword();
        String passwordHash = sysUser.getPassword();
        if (!SecurityUtils.matchBcryptHash(password, passwordHash)) {
            throw new ServiceException("账号或密码错误", ErrorCodeConstants.INVALID_USERNAME_OR_PASSWORD);
        }
        LoginDataVO loginData = new LoginDataVO();
        StpUtil.login(sysUser.getId());
        loginData.setToken(StpUtil.getTokenValueByLoginId(sysUser.getId()));
        return loginData;
    }
}
