package com.yuepong.integrate.systemic.camel.spi;

import org.apache.camel.Exchange;
import org.apache.camel.spi.CamelLogger;
import org.apache.camel.spi.LogListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName CamelLogListener
 * @Description camel日志监听
 * @Author lixuefei
 * @Date 2022.6.10 11:30
 **/
@Component
public class CamelLogListener implements LogListener {
    @Override
    public String onLog(Exchange exchange, CamelLogger camelLogger, String message) {
        return null;
    }
}
