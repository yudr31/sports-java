package com.spring.boot.sports.base;

/**
 * author yuderen
 * version 2018/11/20 14:05
 */
public class BaseException extends RuntimeException{

    private String code;
    private String message;

    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
