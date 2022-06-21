package com.yuepong.integrate.systemic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName StaticAnchorPointService
 * @Description 提供静态服务
 * @Author lixuefei
 * @Date 2022.6.15 13:56
 **/
@Component("StaticAnchorPointService")
public class StaticAnchorPointService {
    
    @Autowired
    private AnchorPointService anchorPointService;
    
    public static AnchorPointService staticAnchorPointService;
    
    @PostConstruct
    public void init() {
        staticAnchorPointService = this.anchorPointService;
    }
    
}
