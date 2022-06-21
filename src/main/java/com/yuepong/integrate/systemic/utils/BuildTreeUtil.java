package com.yuepong.integrate.systemic.utils;

import com.yuepong.integrate.systemic.entity.common.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BuildTreeUtil
 * @Description 树形数据工具类
 * @Author lixuefei
 * @Date 2022.5.17 10:06
 **/
public class BuildTreeUtil {
    
    /**
     * 功能描述: <生成树结构>
     *
     * @Param: [T]
     * @Author: zl
     * @Date: 2021/6/1 9:10
     */
    public static List<Node> buildTree(List<? extends Node> nodeList) {
        List<Node> nodes = new ArrayList<>();
        if (nodeList != null) {
            for (Node node : nodeList) {
                String parentId = node.getParentId();
                if (parentId == null || "".equals(parentId) || parentId.equals(node.getId())) {
                    nodes.add(node);
                    continue;
                }
                for (Node parent : nodeList) {
                    if (parent.getId().equals(parentId)) {
                        List<Node> parentList = parent.getChildren();
                        if (parentList == null) {
                            parentList = new ArrayList<>();
                            parentList.add(node);
                            parent.setChildren(parentList);
                        } else {
                            parentList.add(node);
                        }
                    }
                }
            }
        }
        return nodes;
    }
}
