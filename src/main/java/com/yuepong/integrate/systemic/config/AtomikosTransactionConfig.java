package com.yuepong.integrate.systemic.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * @ClassName AtomikosTransactionConfig
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.6.14 14:48
 **/
@Configuration
@EnableTransactionManagement
public class AtomikosTransactionConfig {
    
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        // 显示sql
        hibernateJpaVendorAdapter.setShowSql(false);
        // 自动生成/更新表
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        // 设置数据库类型
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }
    
    
    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(60000);
        return userTransactionImp;
    }
    
    @Bean(name = "atomikosTransactionManager")
    public TransactionManager atomikosTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        AtomikosJtaPlatform.transactionManager = userTransactionManager;
        return userTransactionManager;
    }
    
    
}
