package com.spring.boot.sports.shiro;

/**
 * @author yuderen
 * @version 2018/9/13 18:17
 */
public class ShiroException extends RuntimeException {
    public ShiroException() {
    }

    public ShiroException(String message) {
        super(message);
    }

    public ShiroException(Throwable cause) {
        super(cause);
    }

    public ShiroException(String message, Throwable cause) {
        super(message, cause);
    }
}