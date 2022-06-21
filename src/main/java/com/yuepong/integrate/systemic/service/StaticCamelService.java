package com.yuepong.integrate.systemic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName StaticCamelService
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.5.20 15:37
 **/
@Component("StaticCamelService")
public class StaticCamelService {
    
    @Autowired
    private CamelService camelService;
    
    public static CamelService staticCamelService;
    
    @PostConstruct
    public void init() {
        staticCamelService = this.camelService;
    }
    
}
