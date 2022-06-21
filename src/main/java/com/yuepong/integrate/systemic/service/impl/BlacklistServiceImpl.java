package com.yuepong.integrate.systemic.service.impl;

import com.yuepong.integrate.systemic.dao.BlacklistDao;
import com.yuepong.integrate.systemic.entity.blacklist.Blacklist;
import com.yuepong.integrate.systemic.exception.BlackliistException;
import com.yuepong.integrate.systemic.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BlacklistServiceImpl
 * @Description 黑名单服务实现类
 * @Author lixuefei
 * @Date 2022.6.20 10:33
 **/
@Service
public class BlacklistServiceImpl implements BlacklistService {
    
    @Autowired
    private BlacklistDao blacklistDao;
    
    /**
     * 新增黑名单
     *
     * @param namespace: 命名空间
     * @param blackKey:  黑名单key
     */
    @Override
    public void putBlacklist(String namespace, String blackKey) {
        if (namespace == null || namespace.length() == 0) {
            throw new BlackliistException("The namespace variable was not found in the headers message");
        }
        if (blackKey == null || blackKey.length() == 0) {
            throw new BlackliistException("blackKey cannot be empty!");
        }
        Blacklist oldBlacklist = blacklistDao.getByNamespaceAndBlackKey(namespace, blackKey);
        if (oldBlacklist == null) {
            Blacklist blacklist = new Blacklist();
            blacklist.setNamespace(namespace);
            blacklist.setBlackKey(blackKey);
            blacklistDao.save(blacklist);
        }
        
        
    }
    
    /**
     * 根据namespace和blackKey查询是否存在黑名单
     *
     * @param namespace: 命名空间
     * @param blackKey:  黑名单key
     * @return 是否存在于黑名单
     */
    @Override
    public Boolean getBlacklist(String namespace, String blackKey) {
        if (namespace == null || namespace.length() == 0) {
            throw new BlackliistException("The namespace variable was not found in the headers message");
        }
        if (blackKey == null || blackKey.length() == 0) {
            throw new BlackliistException("blackKey cannot be empty!");
        }
        Blacklist blacklist = blacklistDao.getByNamespaceAndBlackKey(namespace, blackKey);
        return blacklist != null;
    }
}
