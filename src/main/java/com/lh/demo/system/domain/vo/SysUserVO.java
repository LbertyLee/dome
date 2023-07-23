package com.lh.demo.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 系统用户VO
 *
 * @author lihong
 * @date 2023/07/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysUserVO {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /*** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
