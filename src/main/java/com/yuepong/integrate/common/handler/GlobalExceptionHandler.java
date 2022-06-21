package com.yuepong.integrate.common.handler;


import com.yuepong.integrate.systemic.exception.ServiceException;
import com.yuepong.integrate.common.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @ClassName ControllerHandlers
 * @Description TODO
 * @Author SINSKSKY
 * @Date 2021/3/3 14:29
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    /**
     * 权限校验失败 如果请求为ajax返回json，普通请求跳转页面
     */
    /*@ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(HttpServletRequest request, AuthorizationException e) {
        //log.error(e.getMessage(), e);
        if (ServletUtils.isAjaxRequest(request)) {
            return ResponseResult.unauth(PermissionUtils.getMsg(e.getMessage()));
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error/unauth");
            return modelAndView;
        }
    }*/
    
    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseResult handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return ResponseResult.error("不支持'" + e.getMethod() + "'请求！");
    }
    
    
    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return ResponseResult.error("运行时异常:" + e.getMessage());
    }
    
    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseResult.error("服务器错误，请联系管理员！");
    }
    
    /**
     * 主键重复异常
     */
    @ExceptionHandler({DuplicateKeyException.class})
    public ResponseResult handleException(DuplicateKeyException e) {
        log.error("主键重复异常:", e);
        return ResponseResult.error(e.getCause().toString());
    }
    
    /**
     * 数据完整性违规异常
     */
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseResult handleException(DataIntegrityViolationException e) {
        String msg = e.getCause().toString();
        if (msg.indexOf("doesn't have a default value") != -1) {
            String field = getValue(msg, "Field");
            return ResponseResult.error("字段" + field + "在数据库中为必填项，不能为空!");
        } else if (msg.indexOf("Data too long") != -1) {
            String column = getValue(msg, "column");
            return ResponseResult.error("字段" + column + "超过了规定的长度!");
        } else {
            return ResponseResult.error(e.getCause().toString());
        }
    }
    
    /**
     * 服务异常
     */
    @ExceptionHandler(value = ServiceException.class)
    public ResponseResult exceptionHandler(ServiceException e) {
        String errorMesssage = e.getMessage();
        return ResponseResult.error(errorMesssage);
    }
    
    /**
     * 校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult exceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + "！";
        }
        return ResponseResult.error(errorMesssage);
    }
    
    /**
     * 校验异常
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseResult validationExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + "！";
        }
        return ResponseResult.error(errorMesssage);
    }
    
    /**
     * 校验异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseResult ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        return ResponseResult.error(String.join("，", msgList));
    }
    
    /**
     * 业务异常
     */
    /*@ExceptionHandler(BusinessException.class)
    public ResponseResult businessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return ResponseResult.error(e.getMessage());
    }*/
    private String getValue(String msg, String key) {
        msg = msg.replace(" ", "");
        int startIndex = msg.indexOf(key) + key.length();
        String quotation = msg.substring(startIndex).substring(0, 1);
        String value = msg.substring(startIndex + 1).substring(0, msg.substring(startIndex + 1).indexOf(quotation));
        return value;
    }
    
}


