package com.yuepong.integrate.systemic.exception;

/**
 * @ClassName AnchorPointException
 * @Description 锚点异常
 * @Author lixuefei
 * @Date 2021.5.24 9:35
 **/
public class AnchorPointException extends RuntimeException {
    
    public AnchorPointException() {
        super();
    }
    
    public AnchorPointException(String message) {
        super(message);
    }
    
    public AnchorPointException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public AnchorPointException(Throwable cause) {
        super(cause);
    }
    
    public AnchorPointException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
