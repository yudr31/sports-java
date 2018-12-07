package com.spring.boot.sports.base;

import lombok.Data;

/**
 * author yuderen
 * version 2018/11/20 11:27
 */
@Data
public class ResponseData<E> {

    private static final String OK_CODE = "0";
    private static final String OK_MSG = "请求成功!";

    private static final String ERROR_CODE = "-9";
    private static final String ERROR_MSG = "系统错误!";

    private String code;
    private String msg;
    private E data;

    private static class Success{
        private static final ResponseData instance = new ResponseData();
    }

    public static ResponseData getSuccessInstance(){
        ResponseData responseData = Success.instance;
        responseData.setCode(OK_CODE);
        responseData.setMsg(OK_MSG);
        return responseData;
    }

    public static ResponseData getErrorInstance(String code, String message){
        ResponseData responseData = ResponseData.getSuccessInstance();
        System.out.println(responseData.toString());
        responseData.setCode(code);
        responseData.setMsg(message);
        return responseData;
    }

    public static ResponseData getErrorInstance(){
        ResponseData responseData = ResponseData.getSuccessInstance();
        System.out.println(responseData);
        responseData.setCode(ERROR_CODE);
        responseData.setMsg(ERROR_MSG);
        return responseData;
    }

    private ResponseData() {
    }

}
