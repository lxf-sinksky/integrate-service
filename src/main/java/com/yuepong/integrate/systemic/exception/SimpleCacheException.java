package com.yuepong.integrate.systemic.exception;

/**
 * @ClassName SimpleCacheException
 * @Description 简易缓存异常
 * @Author lixuefei
 * @Date 2021.5.24 9:35
 **/
public class SimpleCacheException extends RuntimeException {
    
    public SimpleCacheException() {
        super();
    }
    
    public SimpleCacheException(String message) {
        super(message);
    }
    
    public SimpleCacheException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public SimpleCacheException(Throwable cause) {
        super(cause);
    }
    
    public SimpleCacheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
