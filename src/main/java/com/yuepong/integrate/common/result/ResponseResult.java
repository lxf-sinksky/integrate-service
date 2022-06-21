package com.yuepong.integrate.common.result;

import java.util.HashMap;

/**
 * @ClassName ResponseResult
 * @Description TODO
 * @Author SINSKSKY
 * @Date 2021/3/3 14:34
 **/
public class ResponseResult extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";

    public static final String MSG_TAG = "msg";

    public static final String DATA_TAG = "data";

    public static final String COUNT_TAG = "count";

    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(20000),
        /**
         * 警告
         */
        WARN(30000),
        /**
         * 错误
         */
        ERROR(40000),
        /**
         * 无权限
         */
        UNAUTH(40300),
        /**
         * 未登录、登录超时
         */
        UNLOGIN(-40100);
        
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 状态类型
     */
    private Type type;

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 数据对象
     */
    private Object data;

    /**
     * 条数
     */
    private Integer count;

    /**
     * 初始化一个新创建的 ResponseResult 对象，使其表示一个空消息。
     */
    public ResponseResult() {
    }

    /**
     * 初始化一个新创建的 ResponseResult 对象
     * @param type 状态类型
     * @param msg  返回内容
     */
    public ResponseResult(Type type, String msg) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 ResponseResult 对象
     * @param type 状态类型
     * @param msg  返回内容
     * @param data 数据对象
     */
    public ResponseResult(Type type, String msg, Object data) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        super.put(DATA_TAG, data);
    }

    /**
     * 初始化一个新创建的 ResponseResult 对象
     * @param type 状态类型
     * @param msg  返回内容
     * @param data 数据对象
     * @param count 条数
     */
    public ResponseResult(Type type, String msg, Integer count, Object data) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        super.put(COUNT_TAG, count);
        super.put(DATA_TAG, data);
    }

    /**
     * 返回成功消息
     * @return 成功消息
     */
    public static ResponseResult success() {
        return ResponseResult.success("操作成功！");
    }

    /**
     * 返回成功数据
     * @return 成功消息
     */
    public static ResponseResult success(Object data) {
        return ResponseResult.success("操作成功！", data);
    }

    /**
     * 返回成功消息
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResponseResult success(String msg) {
        return ResponseResult.success(msg, null);
    }

    /**
     * 返回成功消息
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResponseResult success(String msg, Object data) {
        return new ResponseResult(Type.SUCCESS, msg, data);
    }

    /**
     * 返回成功消息
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResponseResult success(String msg, Integer count, Object data) {
        return new ResponseResult(Type.SUCCESS, msg, count, data);
    }

    /**
     * 返回警告消息
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseResult warn(String msg) {
        return ResponseResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResponseResult warn(String msg, Object data) {
        return new ResponseResult(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     * @return
     */
    public static ResponseResult error(Object data) {
        return new ResponseResult(Type.ERROR, "操作失败！", data);
    }

    /**
     * 返回错误消息
     * @return
     */
    public static ResponseResult error() {
        return ResponseResult.error("操作失败！");
    }

    /**
     * 返回错误消息
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseResult error(String msg) {
        return ResponseResult.error(msg, null);
    }

    /**
     * 返回错误消息
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResponseResult error(String msg, Object data) {
        return new ResponseResult(Type.ERROR, msg, data);
    }

    /**
     * 无权限返回
     * @return
     */
    public static ResponseResult unauth() {
        return new ResponseResult(Type.UNAUTH, "您没有访问权限！", null);
    }
    /**
     * 无权限
     *
     * @param msg
     * @return com.wanda.labor.framework.web.domain.ResponseResult
     * @exception
     */
    public static ResponseResult unauth(String msg) {
        return new ResponseResult(Type.UNAUTH, msg, null);
    }
    /**
     * 未登录或登录超时。请重新登录
     *
     * @param
     * @return com.wanda.labor.framework.web.domain.ResponseResult
     * @exception
     */
    public static ResponseResult unlogin() {
        return new ResponseResult(Type.UNLOGIN, "未登录或登录超时，请重新登录！", null);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static class SUCCESS{

        public static ResponseResult data(Object data){
            return new ResponseResult(Type.SUCCESS, "操作成功", data);
        }

        public static ResponseResult iMessagesg(String msg){
            return new ResponseResult(Type.SUCCESS, msg, null);
        }

        public static ResponseResult imsgAndData(String msg, Object data){
            return new ResponseResult(Type.SUCCESS, msg, data);
        }
    }
}

