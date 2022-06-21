package com.yuepong.integrate.systemic.exception;

/**
 * @ClassName TypeConvertException
 * @Description 类型转换异常
 * @Author lixuefei
 * @Date 2021.5.24 9:35
 **/
public class TypeConvertException extends RuntimeException {
    
    public TypeConvertException() {
        super();
    }
    
    public TypeConvertException(String message) {
        super(message);
    }
    
    public TypeConvertException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public TypeConvertException(Throwable cause) {
        super(cause);
    }
    
    public TypeConvertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
