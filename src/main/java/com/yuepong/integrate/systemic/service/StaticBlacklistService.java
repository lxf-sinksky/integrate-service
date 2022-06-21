package com.yuepong.integrate.systemic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName StaticBlacklistService
 * @Description 提供静态服务
 * @Author lixuefei
 * @Date 2022.5.20 15:37
 **/
@Component("StaticBlacklistService")
public class StaticBlacklistService {
    
    @Autowired
    private BlacklistService blacklistService;
    
    public static BlacklistService staticBlacklistService;
    
    @PostConstruct
    public void init() {
        staticBlacklistService = this.blacklistService;
    }
    
}
