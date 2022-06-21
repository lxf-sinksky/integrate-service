package com.yuepong.integrate.systemic.service;

/**
 * @ClassName AnchorPointService
 * @Description 锚点服务类
 * @Author lixuefei
 * @Date 2022.5.26 10:40
 **/
public interface AnchorPointService {
    
    /**
     * 获取锚点信息
     *
     * @param namespace: 命名空间
     * @return 锚点信息
     */
    String getAnchor(String namespace);
    
    /**
     * 设置锚点
     *
     * @param namespace: 命名空间
     * @param value:     锚点值
     */
    void setAnchor(String namespace, String value);
}
