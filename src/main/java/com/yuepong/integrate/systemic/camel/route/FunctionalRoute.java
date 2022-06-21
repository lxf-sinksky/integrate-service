package com.yuepong.integrate.systemic.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @ClassName FunctionalRoute
 * @Description 功能性路由
 * @Author lixuefei
 * @Date 2022.6.14 22:28
 **/
@Component
public class FunctionalRoute extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {
        
        /**
         * 定时清除缓存
         */
        from("cron:expelCache?schedule=0/60 * * * * ?").routeId("expelCache").routeDescription("定时清除过期缓存")
                .bean("SimpleCacheUtil", "expelCache");
        
        /**
         * 新增黑名单
         */
        from("direct:putBlacklist").routeId("putBlacklist").routeDescription("新增黑名单")
                .log("${headers.blackKey}")
                .bean("BlacklistUtil", "putBlacklist");
        
        /**
         * 查询黑名单
         */
        from("direct:getBlacklist").routeId("getBlacklist").routeDescription("查询黑名单")
                .bean("BlacklistUtil", "getBlacklist")
                .choice().when(simple("${body} == true"))
                .log("BlackKey: ${headers.blackKey} 在黑名单中，已中止路由")
                .stop()
                .otherwise()
                .log("BlackKey: ${headers.blackKey} 不在黑名单中，继续执行路由");
        
        
    }
}
