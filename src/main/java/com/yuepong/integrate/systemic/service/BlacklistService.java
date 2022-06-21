package com.yuepong.integrate.systemic.service;

public interface BlacklistService {
    
    /**
     * 新增黑名单
     *
     * @param namespace: 命名空间
     * @param blackKey:  黑名单key
     */
    void putBlacklist(String namespace, String blackKey);
    
    /**
     * 根据namespace和blackKey查询是否存在黑名单
     *
     * @param namespace: 命名空间
     * @param blackKey:  黑名单key
     * @return 是否存在于黑名单
     */
    Boolean getBlacklist(String namespace, String blackKey);
    
}
