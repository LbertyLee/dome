package com.lh.demo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lh.demo.common.utils.SecurityUtils;
import com.lh.demo.common.utils.StringUtils;
import com.lh.demo.common.utils.tree.TreeUtils;
import com.lh.demo.system.domain.SysMenu;
import com.lh.demo.system.domain.vo.RouterVO;
import com.lh.demo.system.mapper.SysMenuMapper;
import com.lh.demo.system.service.SysMenuService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.swing.tree.TreeNode;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;


    /**
     * 获得权限列表
     *
     * @param loginId 登录id
     * @return {@link List}<{@link String}>
     */
    @Override
    public List<String> getPermissionList(Object loginId) {
        return sysMenuMapper.getPermissionList(SecurityUtils.getUserId());
    }

    /**
     * 查询系统菜单列表
     *
     * @return {@link List}<{@link SysMenu}>
     */
    @Override
    public List<RouterVO> getSysMenuList() {
        return null;
    }

    @Override
    public List<RouterVO> getRouters(Long userId) {
        List<SysMenu> sysMenus = sysMenuMapper.QuerySysMenuByUserid(userId);
        // 获取根节点
        List<SysMenu> list = sysMenus.stream().filter(item -> item.getParentId().equals(0L)).toList();
        // 根据pid 进行分组
        Map<Long, List<SysMenu>> collect = sysMenus.stream().collect(Collectors.groupingBy(SysMenu::getParentId));
        recursionFnTree(list,collect);
        return buildRouterTree(list);
    }

    public List<RouterVO> buildRouterTree(List<SysMenu> sysMenus){
        return sysMenus.stream().map(sysMenu -> {
            RouterVO routerVO = new RouterVO();
            RouterVO.Meta meta = new RouterVO.Meta();
            meta.setTitle(sysMenu.getMenuName()).setIcon(sysMenu.getIcon());
            routerVO.setRouterName(sysMenu.getMenuName())
                    .setComponent(sysMenu.getComponent()).setPath("/"+sysMenu.getPath())
                    .setOrderNum(sysMenu.getOrderNum()).setQueryParam(sysMenu.getQueryParam()).setMeta(meta);
            List<SysMenu> children = sysMenu.getChildren();
            if(StringUtils.isNotNull(children)){
                routerVO.setChildren(buildRouterTree(children));
            }
            return routerVO;
        }).toList();

    }
    /**
     * 递归fn树
     *
     * @param list 列表
     * @param map  地图
     */// 递归
    public static void recursionFnTree(List<SysMenu> list, Map<Long, List<SysMenu>> map) {

        for (SysMenu sysMenu : list) {
            List<SysMenu> childList = map.get(sysMenu.getMenuId());
            sysMenu.setChildren(childList);
            if (null != childList && 0 < childList.size()) {
                recursionFnTree(childList, map);
            }
        }

    }

}
