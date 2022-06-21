package com.yuepong.integrate.systemic.exception;

/**
 * @ClassName DataSourceLoadException
 * @Description 数据源无法定义类型异常
 * @Author lixuefei
 * @Date 2021.5.24 9:35
 **/
public class DataSourceLoadException extends RuntimeException {
    
    public DataSourceLoadException() {
        super();
    }
    
    public DataSourceLoadException(String message) {
        super(message);
    }
    
    public DataSourceLoadException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DataSourceLoadException(Throwable cause) {
        super(cause);
    }
    
    public DataSourceLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
