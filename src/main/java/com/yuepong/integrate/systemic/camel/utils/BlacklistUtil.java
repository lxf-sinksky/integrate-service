package com.yuepong.integrate.systemic.camel.utils;

import com.yuepong.integrate.systemic.service.BlacklistService;
import com.yuepong.integrate.systemic.service.StaticBlacklistService;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @ClassName BlacklistUtil
 * @Description 黑名单工具类
 * @Author lixuefei
 * @Date 2022.6.15 11:43
 **/
@Component("BlacklistUtil")
@DependsOn("StaticBlacklistService")
public class BlacklistUtil {
    
    private static final BlacklistService blacklistService = StaticBlacklistService.staticBlacklistService;
    
    /**
     * 新增黑名单
     *
     * @param namespace: 命名空间
     * @param blackKey:  黑名单key
     */
    public static void putBlacklist(@Header("namespace") String namespace, @Header("blackKey") String blackKey) {
        blacklistService.putBlacklist(namespace, blackKey);
    }
    
    /**
     * 查询是否存在于黑名单
     *
     * @param namespace: 命名空间
     * @param blackKey:  黑名单key
     * @return 是否存在于黑名单
     */
    public static void getBlacklist(@Header("namespace") String namespace, @Header("blackKey") String blackKey, Exchange exchange) {
        exchange.getIn().setHeader("blackValue", blacklistService.getBlacklist(namespace, blackKey));
    }
    
}
