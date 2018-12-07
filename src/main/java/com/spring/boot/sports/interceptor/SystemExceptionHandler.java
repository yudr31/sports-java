package com.spring.boot.sports.interceptor;

import com.spring.boot.sports.base.BaseException;
import com.spring.boot.sports.base.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * author yuderen
 * version 2018/11/21 10:24
 */
@ControllerAdvice
public class SystemExceptionHandler extends SimpleMappingExceptionResolver {

    private Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object doResolveException(HttpServletRequest request, Exception ex){
        String servletPath = request.getServletPath();
        logger.error(servletPath,ex);
        ResponseData responseData = ResponseData.getErrorInstance();
        if (ex instanceof BaseException){
            responseData.setCode(((BaseException) ex).getCode());
            responseData.setMsg(ex.getMessage());
        }
        return responseData;
    }

}
