package com.lh.demo.system.domain;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 系统角色
 *
 * @  lh
 * @date 2023/04/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysRole {

    /*** 角色id*/
    private  Long roleId;

    /*** 角色名*/
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /*** 显示顺序*/
    private Integer roleSort;

    /*** 角色状态 角色状态（0正常 1停用）*/
    private Boolean status;

    /**
     * 删除标志（0：代表存在 1：代表删除）*/
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  Boolean delFlag;

    /*** 备注*/
    private String remark;

    /*** 创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /*** 更新时间*/
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;




}
