package com.lh.dome.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 系统用户
 *
 * @  lh
 * @date 2023/04/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysUser {


    /*** 用户id */
    private Long id;

    /*** 用户名 */
    private String userName;

    /*** 昵称 */
    private String nickname;

    /*** 头像 */
    private String avatar;

    /*** 电话号码 */
    private  String phone;

    /*** 密码 */
    private String password;

    /*** 创建时间 */
    private Date createTime;

    /*** 更新时间 */
    private Date updateTime;



}
