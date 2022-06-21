package com.yuepong.integrate.operation.camel.script;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

/**
 * @ClassName SimulationRoute
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.5.17 8:38
 **/
// @Component
public class SimulationRoute extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {
        
        from("timer:syncBook?repeatCount=1").routeId("syncBook").routeDescription("同步book").autoStartup(false)
                .setHeader("materialId", simple("book"))
                .to("direct:syncBookStart");
        
        from("direct:syncBookStart").routeId("syncBookStart").routeDescription("开始同步book")
                // 全量信息，获取所有book
                .to("direct:getAllBook");
        
        from("direct:getAllBook").routeId("getAllBook").routeDescription("获取mysql8所有book信息")
                .to("sql:select * from book?dataSource=#mysql8")
                .setHeader("MDataList", body())
                .split(body())
                .marshal().json(JsonLibrary.Fastjson, false)
                
                .to("direct:saveBook");
        
        from("direct:saveBook").routeId("saveBook").routeDescription("插入book信息到sqlserver数据库")
                .setHeader("MData", body())
                .to("sql:INSERT INTO book(id, name, price) VALUES (:#${headers.MData[id]}, :#${headers.MData[name]}, :#${headers.MData[price]})?dataSource=#mysqlserver");
        
        
    }
}
