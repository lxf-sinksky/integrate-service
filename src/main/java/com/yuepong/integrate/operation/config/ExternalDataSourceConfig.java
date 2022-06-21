package com.yuepong.integrate.operation.config;


import com.yuepong.integrate.systemic.utils.ResourceLoadUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName ExternalDataSourceConfig
 * @Description 外部数据源配置
 * @Author lixuefei
 * @Date 2022.5.7 13:56
 **/
@Configuration
@DependsOn("transactionManager")
public class ExternalDataSourceConfig {
    
    /**
     * 外部数据源配置类
     * 用于从登记的数据源信息生成DataSourceBean，注册到Spring容器中
     * 仅支持Mysql，SqlServer，Oracle 3种类型的数据库
     * <p>
     * 使用说明:
     * `@Bean("beanName")`注解中的beanName，必须与在配置文件中登记的数据源名称一致，该名称用于Camel发现注册的数据源Bean
     * 使用ResourceLoadUtil里的方法创建对应类型的数据源
     * - registerMysqlDataSource(Environment env, String beanName)
     * 用于创建Mysql数据源
     * - registerSqlServerDataSource(Environment env, String beanName)
     * 用于创建SqlServer数据源
     * - registerOracleDataSource(Environment env, String beanName)
     * 用于创建Oracle数据源
     * 仅需传入beanName，ResourceLoadUtil会自动加载配置文件中resource.datasource底下的同名数据源连接配置
     * <p>
     * 示例：
     *
     * @Bean("mysql8") public DataSource mysql8DataSource(Environment env) throws SQLException {
     * return ResourceLoadUtil.registerMysqlDataSource(env, "mysql8");
     * }
     */
    
    @Bean("mysql8")
    public DataSource mysql8DataSource(Environment env) throws SQLException {
        return ResourceLoadUtil.registerMysqlDataSource(env, "mysql8");
    }
    
    
    @Bean("mysql82")
    public DataSource mysql82DataSource(Environment env) throws SQLException {
        return ResourceLoadUtil.registerMysqlDataSource(env, "mysql82");
    }
    
    
    @Bean("mysqlserver")
    public DataSource mysqlserverDataSource(Environment env) throws SQLException {
        return ResourceLoadUtil.registerSqlServerDataSource(env, "mysqlserver");
    }
    
    /*@Bean("oracle-test")
    public DataSource oracleTestserverDataSource(Environment env) throws SQLException {
        return ResourceLoadUtil.registerOracleDataSource(env, "oracle-test");
    }*/
    
    
}
