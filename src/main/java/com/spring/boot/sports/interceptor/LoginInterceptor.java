package com.spring.boot.sports.interceptor;

import com.spring.boot.sports.base.BaseException;
import com.spring.boot.sports.service.UserManagerService;
import com.spring.boot.sports.util.SpringUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * author yuderen
 * version 2018/11/23 14:30
 */
@Aspect
@Component
public class LoginInterceptor {

    @Autowired
    private UserManagerService userManagerService;

    @Before("execution(public * com.spring.boot.sports.controller.*.*(..))")
    public void userOnline(){
        loginCheck();
    }

    private void loginCheck(){
        HttpServletRequest request = SpringUtil.getRequest();
        String uri = request.getRequestURI();
        System.out.println("-->" + uri);
        if ("/sports/login".equals(uri)){
            return;
        }
        Boolean login = userManagerService.checkUserLogin();
        if (login){
            return;
        }
        throw new BaseException("1","用户未登录");
    }

}
