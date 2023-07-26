package com.lh.demo.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 系统用户角色
 *
 * @author lihong
 * @date 2023/07/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysUserRole {

    private Long UserId;

    private  Long RoleId;
}
