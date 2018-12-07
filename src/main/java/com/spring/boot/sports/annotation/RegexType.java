package com.spring.boot.sports.annotation;

/**
 * @author yuderen
 * @version 2018/8/25 14:29
 */
public enum RegexType {

    NONE(""),
    EMAIL("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"),
    IP("^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])((\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])){3}|(\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])){5})$"),
    PHONE("^((0|((([+]*)?\\\\d*)?86|17951)(\\\\-| )?))?(1[1-9][0-9])[0-9]{8}$"),
    OTHER("");

    private String regexExpression;

    RegexType(String regexExpression) {
        this.regexExpression = regexExpression;
    }

    public String getRegexExpression() {
        return regexExpression;
    }

    public void setRegexExpression(String regexExpression) {
        this.regexExpression = regexExpression;
    }
}
