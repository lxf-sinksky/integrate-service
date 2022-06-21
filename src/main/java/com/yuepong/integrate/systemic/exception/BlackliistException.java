package com.yuepong.integrate.systemic.exception;

/**
 * @ClassName BlackliistException
 * @Description 黑名单异常
 * @Author lixuefei
 * @Date 2021.5.24 9:35
 **/
public class BlackliistException extends RuntimeException {
    
    public BlackliistException() {
        super();
    }
    
    public BlackliistException(String message) {
        super(message);
    }
    
    public BlackliistException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BlackliistException(Throwable cause) {
        super(cause);
    }
    
    public BlackliistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
