package com.yuepong.integrate.operation.config;

import org.springframework.context.annotation.Configuration;

/**
 * @ClassName JmsConfig
 * @Description JMS配置(未完成, 仅供参考)
 * @Author lixuefei
 * @Date 2022.6.9 10:08
 **/
@Configuration
public class JmsConfig {
    
    /*@Bean(name = "myMq")
    public ConnectionFactory myMq() throws JMSException {
        ActiveMQXAConnectionFactory activeMQXAConnectionFactory = new ActiveMQXAConnectionFactory();
        activeMQXAConnectionFactory.setBrokerURL("tcp://sinksky.cn:61616");
        activeMQXAConnectionFactory.setUser("admin");
        activeMQXAConnectionFactory.setPassword("123456");
        activeMQXAConnectionFactory.setUseGlobalPools(true);
        AtomikosConnectionFactoryBean atomikosConnectionFactoryBean = new AtomikosConnectionFactoryBean();
        atomikosConnectionFactoryBean.setUniqueResourceName("myMq");
        atomikosConnectionFactoryBean.setLocalTransactionMode(false);
        atomikosConnectionFactoryBean.setMaxPoolSize(30000);
        atomikosConnectionFactoryBean.setXaConnectionFactory(activeMQXAConnectionFactory);
        
        return atomikosConnectionFactoryBean;
    }*/
    
    
}
