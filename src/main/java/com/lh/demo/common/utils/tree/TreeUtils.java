package com.lh.demo.common.utils.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 功能描述：树形数据构建工具
 *
 * @author lihong
 * @date 2023/07/24
 */
public class TreeUtils {

    /**
     * 方法描述：初始化树。此处不进行节点层级排序，排序由业务处理
     *
     * @param nodes 节点数据
     * @return java.util.List<TreeNode>
     */
    public static <T extends TreeNode> List<T> buildTree(List<T> nodes) {
        Map<String, List<TreeNode>> groups = (nodes = c(nodes)).stream().collect(Collectors.groupingBy(TreeNode::getParentId, LinkedHashMap::new, Collectors.toList()));
        return nodes.stream().filter(Objects::nonNull).peek(pnd -> pnd.setChildren(groups.get(pnd.getId()))).filter(TreeNode::getRootNode).collect(Collectors.toList());
    }

    /**
     * 方法描述：防止空指针异常处理
     *
     * @param c 集合数据
     * @return java.util.List<L>
     */
    private static <L> List<L> c(List<L> c) {
        return Optional.ofNullable(c).orElseGet(Collections::emptyList);
    }
}
