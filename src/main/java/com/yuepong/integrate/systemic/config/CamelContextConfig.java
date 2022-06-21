package com.yuepong.integrate.systemic.config;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName CamelContextConfiguration
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.6.10 12:27
 **/
@Configuration
public class CamelContextConfig {
    
    @Bean
    public CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext context) {
            
            }
            
            @Override
            public void afterApplicationStart(CamelContext camelContext) {
            }
        };
    }
    
}
