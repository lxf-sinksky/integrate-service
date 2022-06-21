package com.yuepong.integrate.systemic.entity.datasource;

import lombok.Data;

/**
 * @ClassName DataSourceProperties
 * @Description 数据源配置属性类
 * @Author lixuefei
 * @Date 2022.6.8 16:35
 **/
@Data
public class DataSourceProperties {
    /**
     * 数据源连接URL
     */
    private String url;
    /**
     * 数据源连接用户名
     */
    private String username;
    /**
     * 数据源连接密码
     */
    private String password;
    /**
     * 数据源连接驱动类名称
     */
    private String driverClassName;
    /**
     * 是否开启全局事务
     */
    private Boolean globalTransaction;
    
}
