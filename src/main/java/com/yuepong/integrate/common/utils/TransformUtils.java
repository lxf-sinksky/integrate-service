package com.yuepong.integrate.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @ClassName TransformUtils
 * @Description 转换操作工具类
 * @Author lixuefei
 * @Date 2021.6.16 10:07
 **/
@Component("TransformUtils")
public class TransformUtils {
    
    
    /**
     * 修改字段名称
     *
     * @param jsonString:字符串json
     * @param oldFieldName:旧的字段名称
     * @param newFieldName:新的字段名称
     * @return JSONObject
     */
    public static JSONObject changeFieldName(String jsonString, String oldFieldName, String newFieldName) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if (!oldFieldName.equals(newFieldName)) {
            jsonObject.put(newFieldName, jsonObject.getString(oldFieldName));
            jsonObject.remove(oldFieldName);
        }
        return jsonObject;
    }
    
    /**
     * 修改字段名称
     *
     * @param condition:条件
     * @param jsonString:字符串json
     * @param oldFieldName:旧的字段名称
     * @param newFieldName:新的字段名称
     * @return JSONObject
     * @throws ScriptException
     */
    public static JSONObject changeFieldName(String condition, String jsonString, String oldFieldName, String newFieldName) throws ScriptException {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if (!oldFieldName.equals(newFieldName)) {
            boolean result = scriptEngine(condition, jsonObject);
            if (result) {
                jsonObject.put(newFieldName, jsonObject.getString(oldFieldName));
                jsonObject.remove(oldFieldName);
            }
        }
        return jsonObject;
    }
    
    
    /**
     * 给字段设置一个值，会覆盖原本的值
     *
     * @param jsonString:字符串json
     * @param fieldName:需要设置值的字段名称
     * @param val:设置的值
     * @return JSONObject
     */
    public static JSONObject setValue(String jsonString, String fieldName, Object val) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        jsonObject.put(fieldName, val);
        return jsonObject;
    }
    
    /**
     * 给字段设置一个值，会覆盖原本的值
     *
     * @param condition:条件
     * @param jsonString:字符串json
     * @param fieldName:需要设置值的字段名称
     * @param val:设置的值
     * @return JSONObject
     */
    public static JSONObject setValue(String condition, String jsonString, String fieldName, Object val) throws ScriptException {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        boolean result = scriptEngine(condition, jsonObject);
        if (result) {
            jsonObject.put(fieldName, val);
        }
        return jsonObject;
    }
    
    /**
     * 移除一个字段
     *
     * @param jsonString:字符串json
     * @param fieldName:需要移除的字段名称
     * @return JSONObject
     */
    public static JSONObject removeField(String jsonString, String fieldName) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        jsonObject.remove(fieldName);
        return jsonObject;
    }
    
    /**
     * 移除一个字段
     *
     * @param condition:条件
     * @param jsonString:字符串json
     * @param fieldName:需要移除的字段名称
     * @return JSONObject
     */
    public static JSONObject removeField(String condition, String jsonString, String fieldName) throws ScriptException {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        boolean result = scriptEngine(condition, jsonObject);
        if (result) {
            jsonObject.remove(fieldName);
        }
        return jsonObject;
    }
    
    /**
     * 添加字段，并设置一个固定值
     *
     * @param jsonString:字符串json
     * @param fieldName:需要添加的字段名称
     * @param val:添加的字段的值
     */
    public static JSONObject addField(String jsonString, String fieldName, Object val) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        jsonObject.put(fieldName, val);
        return jsonObject;
    }
    
    /**
     * 添加字段，并设置一个固定值
     *
     * @param condition:条件
     * @param jsonString:字符串json
     * @param fieldName:需要添加的字段名称
     * @param val:添加的字段的值
     * @return JSONObject
     */
    public static JSONObject addField(String condition, String jsonString, String fieldName, Object val) throws ScriptException {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        boolean result = scriptEngine(condition, jsonObject);
        if (result) {
            jsonObject.put(fieldName, val);
        }
        return jsonObject;
    }
    
    /**
     * 给一个空缺的字段设置默认值
     *
     * @param jsonString:字符串json
     * @param field:需要设置默认值的字段名称
     * @param val:设置的默认值
     * @return JSONObject
     */
    public static JSONObject setDefaultValue(String jsonString, String field, Object val) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if (jsonObject.get(field).toString() == null || jsonObject.get(field).toString().length() == 0) {
            jsonObject.put(field, val);
        }
        return jsonObject;
    }
    
    /**
     * 给一个空缺的字段设置默认值
     *
     * @param condition:条件
     * @param jsonString:字符串json
     * @param field:需要设置默认值的字段名称
     * @param val:设置的默认值
     * @return JSONObject
     */
    public static JSONObject setDefaultValue(String condition, String jsonString, String field, Object val) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if (jsonObject.get(field).toString() == null || jsonObject.get(field).toString().length() == 0) {
            jsonObject.put(field, val);
        }
        return jsonObject;
    }
    
    /**
     * 给字段设置一个前缀
     *
     * @param jsonString:字符串json
     * @param field:需要设置前缀的字段名称
     * @param val:设置的前缀
     * @return JSONObject
     */
    public static JSONObject setPrefixValue(String jsonString, String field, Object val) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        jsonObject.put(field, val.toString() + jsonObject.get(field));
        return jsonObject;
    }
    
    /**
     * 给字段设置一个前缀
     *
     * @param condition:条件
     * @param jsonString:字符串json
     * @param field:需要设置前缀的字段名称
     * @param val:设置的前缀
     * @return JSONObject
     */
    public static JSONObject setPrefixValue(String condition, String jsonString, String field, Object val) throws ScriptException {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        boolean result = scriptEngine(condition, jsonObject);
        if (result) {
            jsonObject.put(field, val.toString() + jsonObject.get(field));
        }
        return jsonObject;
    }
    
    /**
     * 给字段设置一个后缀
     *
     * @param jsonString:字符串json
     * @param field:需要设置后缀的字段名称
     * @param val:设置的后缀
     * @return JSONObject
     */
    public static JSONObject setSuffixValue(String jsonString, String field, Object val) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        jsonObject.put(field, jsonObject.get(field) + val.toString());
        return jsonObject;
    }
    
    /**
     * 给字段设置一个后缀
     *
     * @param condition:条件
     * @param jsonString:字符串json
     * @param field:需要设置后缀的字段名称
     * @param val:设置的后缀
     * @return JSONObject
     */
    public static JSONObject setSuffixValue(String condition, String jsonString, String field, Object val) throws ScriptException {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        boolean result = scriptEngine(condition, jsonObject);
        if (result) {
            jsonObject.put(field, jsonObject.get(field) + val.toString());
        }
        return jsonObject;
    }
    
    
    private static boolean scriptEngine(String condition, JSONObject jsonObject) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        condition = condition.replace("`", "\"");
        conditionPut(engine, condition, jsonObject);
        return (Boolean) engine.eval(condition);
    }
    
    private static void conditionPut(ScriptEngine engine, String condition, JSONObject jsonObject) {
        String str = condition;
        if (str.contains("[")) {
            int start = str.indexOf("[") + 1;
            int end = str.indexOf("]");
            String fieldName = condition.substring(start, end);
            engine.put(fieldName, jsonObject.get(fieldName));
            condition = condition.replace("[".concat(fieldName).concat("]"), fieldName);
            conditionPut(engine, condition, jsonObject);
        }
    }
    
    
}
