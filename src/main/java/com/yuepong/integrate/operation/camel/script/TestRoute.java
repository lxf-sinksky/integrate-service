package com.yuepong.integrate.operation.camel.script;


import com.yuepong.integrate.systemic.camel.strategy.MapListAggregationStrategy;
import com.yuepong.integrate.systemic.service.CamelService;
import com.yuepong.integrate.systemic.service.DataSourceService;
import com.yuepong.integrate.systemic.service.SimpleCacheService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @ClassName TestRoute
 * @Description 测试路由
 * @Author lixuefei
 * @Date 2022.4.15 9:22
 **/
@Component
public class TestRoute extends RouteBuilder {
    @Autowired
    private DataSourceService dataSourceService;
    @Autowired
    private CamelService camelService;
    @Autowired
    private SimpleCacheService simpleCacheService;
    
    
    @Override
    public void configure() throws Exception {
        
        
        from("timer:name?repeatCount=1")
                .setHeader("namespace", constant("book"))
                .to("sql:select id,name from book;?dataSource=#mysql8&outputType=SelectList")
                .split(body(), new MapListAggregationStrategy())
                .log("${body}")
                .setHeader("blackKey", simple("${body.get('id')}"))
                .to("direct:getBlacklist")
                .end()
                .log("${body}");
        
        
        /*from("quartz://groupName/timerName?cron=0/1 * * * * ?")
                .delay(3000)
                .log("bbb");*/
        
        
        /*from("debezium-sqlserver:aaa?offsetStorageFileName=/tmp/offset.dat&databaseHostname=localhost&databaseUser=lxf&databasePassword=636669&databaseDbname=test&databaseServerName=my-app-connector&databaseHistoryFileFilename=/tmp/dbhistory.dat&tableIncludeList=dbo.book")
                .log("从 Debezium 收到的事件:")
                .log("    标识: ${headers.CamelDebeziumIdentifier}")
                .log("    元数据: ${headers.CamelDebeziumSourceMetadata}")
                .log("    操作: '${headers.CamelDebeziumOperation}'")
                .log("    数据库: '${headers.CamelDebeziumSourceMetadata[db]}' 表: '${headers.CamelDebeziumSourceMetadata[table]}'")
                .log("    主键: ${headers.CamelDebeziumKey}")
                .log("    更变之前的值: ${headers.CamelDebeziumBefore}")
                .log("    更变之后的值: ${body}");*/
        
        
        /*from("debezium-mysql:dbz-test-1?" +
                "offsetStorageFileName=/usr/offset-file-1.dat&" +
                "databaseHostname=localhost&databaseUser=root&" +
                "databasePassword=636669&" +
                "databaseServerName=my-app-connector&" +
                "databaseHistoryFileFilename=/usr/history-file-1.dat&" +
                "databaseServerId=1&" +
                "databaseIncludeList=mytest&" +
                "tableWhitelist=mytest.book")
                .log("从 Debezium 收到的事件:")
                .log("    标识: ${headers.CamelDebeziumIdentifier}")
                .log("    元数据: ${headers.CamelDebeziumSourceMetadata}")
                .log("    操作: '${headers.CamelDebeziumOperation}'")
                .log("    数据库: '${headers.CamelDebeziumSourceMetadata[db]}' 表: '${headers.CamelDebeziumSourceMetadata[table]}'")
                .log("    主键: ${headers.CamelDebeziumKey}")
                .log("    更变之前的值: '${headers.CamelDebeziumBefore}'")
                .log("    更变之后的值: '${body}'")
                .log("    事件的ddlsql文本: '${headers.CamelDebeziumDdlSQL}'");*/
        
        
    }
    
    
}
