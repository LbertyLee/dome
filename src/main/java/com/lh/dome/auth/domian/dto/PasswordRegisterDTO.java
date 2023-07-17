package com.lh.dome.auth.domian.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRegisterDTO {

    /*** 用户名 */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /** 密码*/
    @NotBlank(message = "密码不能为空")
    private String password;
}
