package com.lh.demo.system.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysMenu {
    /**
     * 菜单ID
     */
    private Long menuId;

    /*** 父菜单ID*/
    private Long parentId;

    /*** 菜单名称*/
    private String menuName;

    /*** 显示顺序*/
    private Integer orderNum;

    /*** 路由地址*/
    private String path;

    /*** 组件路径*/
    private String component;

    /*** 路由参数*/
    private String queryParam;

    /*** 是否为外链（0是 1否）*/
    private String isFrame;

    /*** 是否缓存（0:缓存 1:不缓存）*/
    private String isCache;

    /*** 类型（M目录 C菜单 F按钮）*/
    private String menuType;

    /*** 显示状态（0显示 1隐藏）*/
    private String visible;

    /*** 菜单状态（0正常 1停用）*/
    private String status;

    /*** 权限字符串*/
    private String perms;

    /*** 菜单图标*/
    private String icon;

    /*** 创建时间*/
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params = new HashMap<>();

    /**
     * 备注
     */
    private String remark;

    /**
     * 父菜单名称
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<>();
}
