package com.lh.demo.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RouterVO {

    /*** 菜单名称*/
    private String RouterName;

    /*** 显示顺序*/
    private Long orderNum;

    /*** 路由地址*/
    private String path;


    /*** 组件路径*/
    private String component;

    /*** 路由参数*/
    private String queryParam;

    /*** 子菜单*/
    private List<RouterVO> children = new ArrayList<>();

    /*** 路由元素*/
    private Meta Meta;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Meta{
        /*** 图标*/
        private String icon;
        /*** 链接*/
        private String link;
        /*** 标题*/
        private String title;
        /*** 是否缓存*/
        private String noCache;

    }
}
