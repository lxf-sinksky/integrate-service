package com.yuepong.integrate.systemic.exception;

/**
 * @ClassName ServiceException
 * @Description 服务异常
 * @Author lixuefei
 * @Date 2021.5.24 9:35
 **/
public class ServiceException extends RuntimeException {
    
    public ServiceException() {
        super();
    }
    
    public ServiceException(String message) {
        super(message);
    }
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ServiceException(Throwable cause) {
        super(cause);
    }
    
    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
