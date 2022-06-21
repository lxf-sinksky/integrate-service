package com.yuepong.integrate.systemic.config;


import com.yuepong.integrate.systemic.utils.ResourceLoadUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName ExternalDataSourceConfig
 * @Description 数据源配置
 * @Author lixuefei
 * @Date 2022.5.7 13:56
 **/
@Configuration
@DependsOn("transactionManager")
public class DataSourceConfig {
    
    /**
     * 主数据源
     */
    @Bean("master")
    @Primary
    public DataSource masterDataSource(Environment env) throws SQLException {
        return ResourceLoadUtil.registerMysqlDataSource(env, "master");
    }
    
    
}
