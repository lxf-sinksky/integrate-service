package com.yuepong.integrate.systemic.camel.strategy;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MapListAggregationStrategy
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.6.20 15:28
 **/
public class MapListAggregationStrategy implements AggregationStrategy {
    
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Map<String, Object> newBody = newExchange.getIn().getBody(HashMap.class);
        Boolean blackValue = newExchange.getIn().getHeader("blackValue", Boolean.class);
        List<Map<String, Object>> bodyList = null;
        if (oldExchange == null) {
            bodyList = new ArrayList<Map<String, Object>>();
            if (blackValue == null || !blackValue) {
                bodyList.add(newBody);
            }
            newExchange.getIn().setBody(bodyList);
            return newExchange;
        } else {
            bodyList = oldExchange.getIn().getBody(ArrayList.class);
            if (blackValue == null || !blackValue) {
                bodyList.add(newBody);
            }
            return oldExchange;
        }
        
    }
}
