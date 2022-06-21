package com.yuepong.integrate.systemic.camel.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuepong.integrate.systemic.exception.TypeConvertException;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Body;
import org.apache.camel.component.sql.ResultSetIterator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;


/**
 * @ClassName TypeConvertUtil
 * @Description 类型转换工具类
 * @Author lixuefei
 * @Date 2022.6.16 9:11
 **/
@Component("TypeConvertUtil")
@Slf4j
public class TypeConvertUtil {
    
    /**
     * 将body转换成JSON格式 <br/>
     * 适用于JSONObject 与 Map 之间互转 <br/>
     * 也可将对象转换成JSON <br/>
     *
     * @param body: Camel Body
     * @return new Body
     */
    public static Object toJson(@Body Object body) {
        if (body instanceof ArrayList) {
            throw new TypeConvertException("Cannot convert ArrayList to JSON. You should use split before converting");
        } else if (body.getClass().toString().contains("Map")) {
            return JSONObject.parseObject(JSONObject.toJSONString(body));
        } else if (body instanceof ResultSetIterator) {
            throw new TypeConvertException("Cannot convert org.apache.camel.component.sql.ResultSetIterator to JSON. You should use split before converting");
        } else if (body instanceof String) {
            if (canBeConvertedToJson(body)) {
                return JSONObject.parseObject((String) body);
            } else {
                throw new TypeConvertException("String \"" + body + "\" cannot be converted to JSON");
            }
        } else if (body instanceof JSONObject) {
            return body;
        } else {
            if (canBeConvertedToJson(body)) {
                return JSONObject.toJSON(body);
            } else {
                throw new TypeConvertException("Converting " + body.getClass() + " to JSON is not supported");
            }
        }
    }
    
    /**
     * 将body转换成JsonArray格式 <br/>
     * 适用于JSONArray 与 List&lt;Map&lt;String,Object&gt;&gt; 之间互转 <br/>
     * 也可将ResultSetIterator 转为 JSONArray <br/>
     *
     * @param body: Camel Body
     * @return new Body
     */
    public static Object toJsonArray(@Body Object body) {
        if (body instanceof ArrayList) {
            if (canBeConvertedToJsonArray(body)) {
                return JSONArray.parseArray(JSON.toJSONString(body));
            } else {
                throw new TypeConvertException("Failed to convert " + body.getClass() + " to JsonArray");
            }
        } else if (body instanceof ResultSetIterator) {
            List<Object> list = new ArrayList<>();
            while (((ResultSetIterator) body).hasNext()) {
                list.add(((ResultSetIterator) body).next());
            }
            if (canBeConvertedToJsonArray(list)) {
                return JSONArray.parseArray(JSON.toJSONString(list));
            } else {
                throw new TypeConvertException("Failed to convert " + body.getClass() + " to JsonArray");
            }
        } else if (body instanceof String) {
            if (canBeConvertedToJsonArray(body)) {
                return JSONArray.parseArray((String) body);
            } else {
                throw new TypeConvertException("String \"" + body + "\" cannot be converted to JsonArray");
            }
        } else {
            throw new TypeConvertException("Converting " + body.getClass() + " to JsonArray is not supported");
        }
    }
    
    
    /**
     * 将body转换成Map格式 <br/>
     * 适用于Map 与 JSONObject 之间互转 <br/>
     * 也可将实体类转换成Map <br/>
     *
     * @param body: Camel Body
     * @return new Body
     */
    public static Object toMap(@Body Object body) {
        if (body instanceof ArrayList) {
            throw new TypeConvertException("Cannot convert ArrayList to Map. You should use split before converting");
        } else if (body instanceof ResultSetIterator) {
            throw new TypeConvertException("Cannot convert org.apache.camel.component.sql.ResultSetIterator to Map. You should use split before converting");
        } else if (body instanceof JSONObject) {
            return new HashMap<>((JSONObject) body);
        } else if (body.getClass().toString().contains("Map")) {
            return body;
        } else if (body instanceof String) {
            if (canBeConvertedToJson(body)) {
                return new HashMap<>(JSONObject.parseObject((String) body));
            } else {
                throw new TypeConvertException("String \"" + body + "\" cannot be converted to Map");
            }
        } else {
            if (canBeConvertedToMap(body)) {
                return convertToMap(body);
            } else {
                throw new TypeConvertException("Converting " + body.getClass() + " to Map is not supported");
            }
        }
    }
    
    /**
     * 将body转换成MapList格式 <br/>
     * 适用于List&lt;Map&lt;String,Object&gt;&gt; 与 JSONArray 之间互转 <br/>
     * 也可将ResultSetIterator 转为 List&lt;Map&lt;String,Object&gt;&gt; <br/>
     *
     * @param body: Camel Body
     * @return new Body
     */
    public static Object toMapList(@Body Object body) {
        if (body instanceof ArrayList) {
            return body;
        } else if (body instanceof ResultSetIterator) {
            List<Object> list = new ArrayList<>();
            while (((ResultSetIterator) body).hasNext()) {
                list.add(((ResultSetIterator) body).next());
            }
            return list;
        } else if (body instanceof JSONArray) {
            List<Map<String, Object>> list = new ArrayList<>();
            for (Object obj : (JSONArray) body) {
                list.add(new HashMap<>((JSONObject) obj));
            }
            return list;
        } else if (body instanceof String) {
            if (canBeConvertedToJsonArray(body)) {
                JSONArray array = JSONArray.parseArray((String) body);
                List<Map<String, Object>> list = new ArrayList<>();
                for (Object obj : array) {
                    list.add(new HashMap<>((JSONObject) obj));
                }
                return list;
            } else {
                throw new TypeConvertException("String \"" + body + "\" cannot be converted to MapList");
            }
        } else {
            throw new TypeConvertException("Converting " + body.getClass() + " to MapList is not supported");
        }
    }
    
    
    private static Map<String, Object> convertToMap(Object body) {
        try {
            Map<String, Object> map = new HashMap<>();
            Class<?> clazz = body.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String value = field.get(body) != null ? field.get(body).toString() : "";
                map.put(field.getName(), value);
            }
            return map;
        } catch (IllegalAccessException e) {
            throw new TypeConvertException("Failed to convert " + body.getClass() + " to Map");
        }
    }
    
    private static boolean canBeConvertedToMap(Object body) {
        try {
            Class<?> clazz = body.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private static boolean canBeConvertedToJson(Object body) {
        try {
            if (body instanceof String) {
                JSON.parse((String) body);
            } else {
                JSONObject.toJSON(body);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private static boolean canBeConvertedToJsonArray(Object body) {
        try {
            if (body instanceof String) {
                JSONArray.parseArray((String) body);
            } else {
                JSONArray.parseArray(JSON.toJSONString(body));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
