package com.yuepong.integrate.systemic.entity.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName RouteNode
 * @Description 路由节点
 * @Author lixuefei
 * @Date 2022.5.17 9:22
 **/
@Data
public class Node {

    /**
     * 节点id
     */
    private String id;
    /**
     * 说明
     */
    private String remark;
    /**
     * 父节点
     */
    private String parentId;
    /**
     * 子节点列表
     */
    private List<Node> children = new ArrayList<>();

    public Node() {
    }


    public Node(String id, String remark) {
        this.id = id;
        this.remark = remark;
    }

    public Node(String id, String remark, String parentId) {
        this.id = id;
        this.remark = remark;
        this.parentId = parentId;
    }

    public Node addChild(Node node) {
        if (children == null) {
            this.children = new LinkedList<Node>();
        }
        children.add(node);
        return this;
    }


}
