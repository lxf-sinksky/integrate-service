package com.yuepong.integrate.systemic.exception;

/**
 * @ClassName NullRouteException
 * @Description 路由为null异常
 * @Author lixuefei
 * @Date 2022.5.20 16:15
 **/
public class NullRouteException extends RuntimeException {

    public NullRouteException() {
        super();
    }

    public NullRouteException(String message) {
        super(message);
    }

    public NullRouteException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullRouteException(Throwable cause) {
        super(cause);
    }

    public NullRouteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
