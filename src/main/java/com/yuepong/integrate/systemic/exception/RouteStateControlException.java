package com.yuepong.integrate.systemic.exception;

/**
 * @ClassName ServiceException
 * @Description TODO
 * @Author lixuefei
 * @Date 2021.5.24 9:35
 **/
public class RouteStateControlException extends RuntimeException {

    public RouteStateControlException() {
        super();
    }

    public RouteStateControlException(String message) {
        super(message);
    }

    public RouteStateControlException(String message, Throwable cause) {
        super(message, cause);
    }

    public RouteStateControlException(Throwable cause) {
        super(cause);
    }

    public RouteStateControlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
