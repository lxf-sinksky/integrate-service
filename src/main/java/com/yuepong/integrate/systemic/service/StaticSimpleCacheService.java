package com.yuepong.integrate.systemic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName StaticSimpleCacheService
 * @Description 提供静态服务
 * @Author lixuefei
 * @Date 2022.5.20 15:37
 **/
@Component("StaticSimpleCacheService")
public class StaticSimpleCacheService {
    
    @Autowired
    private SimpleCacheService simpleCacheService;
    
    public static SimpleCacheService staticSimpleCacheService;
    
    @PostConstruct
    public void init() {
        staticSimpleCacheService = this.simpleCacheService;
    }
    
}
