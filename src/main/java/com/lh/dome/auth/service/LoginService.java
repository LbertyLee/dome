package com.lh.dome.auth.service;

import com.lh.dome.auth.domian.dto.PasswordLoginDTO;
import com.lh.dome.auth.domian.vo.LoginDataVO;

/**
 * 登录服务
 *
 * @  lh
 * @date 2023/04/27
 */
public interface LoginService {
    LoginDataVO passwordLogin(PasswordLoginDTO passwordLoginDTO);
}
