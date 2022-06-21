package com.yuepong.integrate.systemic.exception;

/**
 * @ClassName DataOperationException
 * @Description 数据操作异常
 * @Author lixuefei
 * @Date 2021.5.24 9:35
 **/
public class DataOperationException extends RuntimeException {
    
    public DataOperationException() {
        super();
    }
    
    public DataOperationException(String message) {
        super(message);
    }
    
    public DataOperationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DataOperationException(Throwable cause) {
        super(cause);
    }
    
    public DataOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
