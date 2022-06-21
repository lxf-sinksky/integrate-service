package com.yuepong.integrate.systemic.utils;

import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;
import com.mysql.cj.jdbc.MysqlXADataSource;
import com.yuepong.integrate.systemic.entity.datasource.DataSourceInfo;
import com.yuepong.integrate.systemic.entity.datasource.DataSourceProperties;
import oracle.jdbc.xa.client.OracleXADataSource;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName ResourceLoadUtil
 * @Description 资源加载工具类
 * @Author lixuefei
 * @Date 2022.5.12 8:35
 **/
public class ResourceLoadUtil {
    
    // 数据库类型
    private final static String MYSQL = "Mysql";
    private final static String SQLSERVER = "SqlServer";
    private final static String ORACLE = "Oracle";
    
    public static List<DataSourceInfo> dataSourceInfoList = new ArrayList<>();
    
    
    // 数据源信息
    public static class InfoType {
        public final static String ADDRESS = "address";
        public final static String DATABASENAME = "basename";
    }
    
    /**
     * 注册Mysql类型数据源
     *
     * @param beanName:数据源Bean名称
     * @return DataSource
     * @throws SQLException:sql异常
     */
    public static DataSource registerMysqlDataSource(Environment env, String beanName) throws SQLException {
        MysqlXADataSource dataSource = new MysqlXADataSource();
        DataSourceProperties properties = build(env, beanName, MYSQL);
        dataSource.setUrl(properties.getUrl());
        dataSource.setUser(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setPinGlobalTxToPhysicalConnection(true);
        
        // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(dataSource);
        xaDataSource.setUniqueResourceName(beanName);
        
        return properties.getGlobalTransaction() ? xaDataSource : dataSource;
    }
    
    
    /**
     * 注册SqlServer类型数据源
     *
     * @param beanName:数据源Bean名称
     * @return DataSource
     * @throws SQLException:sql异常
     */
    public static DataSource registerSqlServerDataSource(Environment env, String beanName) throws SQLException {
        SQLServerXADataSource dataSource = new SQLServerXADataSource();
        DataSourceProperties properties = build(env, beanName, SQLSERVER);
        dataSource.setURL(properties.getUrl());
        dataSource.setUser(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(dataSource);
        xaDataSource.setUniqueResourceName(beanName);
        return properties.getGlobalTransaction() ? xaDataSource : dataSource;
    }
    
    
    /**
     * 注册SqlServer类型数据源
     *
     * @param beanName:数据源Bean名称
     * @return DataSource
     * @throws SQLException:sql异常
     */
    public static DataSource registerOracleDataSource(Environment env, String beanName) throws SQLException {
        OracleXADataSource dataSource = new OracleXADataSource();
        DataSourceProperties properties = build(env, beanName, ORACLE);
        dataSource.setURL(properties.getUrl());
        dataSource.setUser(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(dataSource);
        xaDataSource.setUniqueResourceName(beanName);
        return properties.getGlobalTransaction() ? xaDataSource : dataSource;
    }
    
    
    private static DataSourceProperties build(Environment env, String prefix, String type) {
        String beanName = prefix;
        if ("master".equals(prefix)) {
            prefix = "spring.datasource." + prefix + ".";
        } else {
            prefix = "resource.datasource." + prefix + ".";
        }
        DataSourceProperties prop = new DataSourceProperties();
        prop.setUsername(env.getProperty(prefix + "username"));
        prop.setPassword(env.getProperty(prefix + "password"));
        prop.setUrl(env.getProperty(prefix + "url"));
        prop.setDriverClassName(env.getProperty(prefix + "driverClassName", ""));
        prop.setGlobalTransaction(Boolean.valueOf(env.getProperty(prefix + "globalTransaction", "false")));
        DataSourceInfo dataSourceInfo = DataSourceInfo.builder()
                .name(beanName)
                .type(type)
                .address(getUrlInfo(type, prop.getUrl(), InfoType.ADDRESS))
                .dataBaseName(getUrlInfo(type, prop.getUrl(), InfoType.DATABASENAME))
                .username(prop.getUsername())
                .password(prop.getPassword())
                .build();
        dataSourceInfoList.add(dataSourceInfo);
        return prop;
    }
    
    private static String getUrlInfo(String type, String url, String infoType) {
        String regex = "jdbc:(?<db>\\w+):.*((//)|@)(?<host>.+):(?<port>\\d+)(/|(;DatabaseName=)|:)(?<dbName>\\w+)\\??.*";
        String result = "";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);
        if (m.find()) {
            if (infoType.equals(InfoType.ADDRESS)) {
                result = m.group("host") + ":" + m.group("port");
            } else if (infoType.equals(InfoType.DATABASENAME)) {
                result = m.group("dbName");
            }
        }
        return result;
    }
    
    
}
