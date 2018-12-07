package com.spring.boot.sports.base;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author yuderen
 * version 2018/11/20 11:38
 */
public abstract class BaseController {

    public ResponseData successResponse(){
        return ResponseData.getSuccessInstance();
    }

    public ResponseData successResponse(Object data){
        ResponseData responseData = ResponseData.getSuccessInstance();
        responseData.setData(data);
        return responseData;
    }

    public ResponseData errorResponse(){
        return ResponseData.getErrorInstance();
    }

    public ResponseData errorResponse(String code, String msg){
        ResponseData responseData = ResponseData.getErrorInstance();
        responseData.setCode(code);
        responseData.setMsg(msg);
        return responseData;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(LocalDateTime.class, new LocalDateTimeEditor());
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    private class DateEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (StringUtils.isEmpty(text)){
                return;
            }
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher result = pattern.matcher(text);
            LocalDateTime localDateTime = null;
            if (result.matches()){
                Long dateTime = Long.parseLong(text);
                setValue(new Date(dateTime));
            }
        }

        @Override
        public String getAsText() {
            return getValue().toString();
        }
    }

    private class LocalDateTimeEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (StringUtils.isEmpty(text)){
                return;
            }
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher result = pattern.matcher(text);
            LocalDateTime localDateTime = null;
            if (result.matches()){
                Long dateTime = Long.parseLong(text);
                Instant instant = Instant.ofEpochMilli(dateTime);
                localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                setValue(localDateTime);
            } else {
                localDateTime = LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                if (null != localDateTime){
                    setValue(localDateTime);
                }
            }
        }

        @Override
        public String getAsText() {
            return getValue().toString();
        }
    }

    protected ResponseData execute(Supplier<ResponseData> fun){
        try {
            return fun.get();
        } catch (Exception e){
            if (e instanceof BaseException){
                BaseException ex = (BaseException) e;
                return errorResponse(ex.getCode(),ex.getMessage());
            }
        }
        return errorResponse();
    }

}
