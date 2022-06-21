package com.yuepong.integrate.systemic.camel.utils;

import com.alibaba.fastjson.JSONObject;
import com.yuepong.integrate.systemic.exception.DataOperationException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName DataOperationUtil
 * @Description 数据操作工具类
 * @Author lixuefei
 * @Date 2022.6.17 13:38
 **/
@Component("DataOperationUtil")
public class DataOperationUtil {
    
    public static Object put(Object body, String key, Object value) {
        if (body.getClass().toString().contains("Map")) {
            Map<String, Object> map = (Map<String, Object>) body;
            map.put(key, value);
            return map;
        } else if (body instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) body;
            jsonObject.put(key, value);
            return jsonObject;
        } else {
            throw new DataOperationException("DataOperationUtil supports only Map or JSON data formats");
        }
    }
    
    public static Object remove(Object body, String key) {
        if (body.getClass().toString().contains("Map")) {
            Map<String, Object> map = (Map<String, Object>) body;
            map.remove(key);
            return map;
        } else if (body instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) body;
            jsonObject.remove(key);
            return jsonObject;
        } else {
            throw new DataOperationException("DataOperationUtil supports only Map or JSON data formats");
        }
    }
    
    
}
