package com.lh.demo.system.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 系统用户dto
 *
 * @  lh
 * @date 2023/04/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysUserDTO {

    private Long id;

    /*** 用户名 */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /*** 昵称 */
    private String nickname;

    /*** 头像 */
    private String avatar;

    /*** 电话号码 */
    private  String phone;

    /*** 角色id*/
    private Long roleId;

    /*** 密码 */
    @NotBlank(message = "密码不能为空")
    private String password;

}
