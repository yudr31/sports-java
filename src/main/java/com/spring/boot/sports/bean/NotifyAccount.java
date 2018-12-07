package com.spring.boot.sports.bean;

import com.spring.boot.sports.annotation.RegexType;
import com.spring.boot.sports.annotation.Validation;
import com.spring.boot.sports.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * author yuderen
 * version 2018/11/22 16:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NotifyAccount extends BaseBean {

    @Validation(max = 20)
    @ApiModelProperty(value = "登录账号名称。")
    private String userName;

    @Validation(max = 20, regexType= RegexType.PHONE)
    @ApiModelProperty(value = "手机号。")
    private String phone;

    @Validation(max = 1)
    @ApiModelProperty(value = "验证标识。0-未验证，1-已验证", example = "0")
    private Integer verifyFlag;

    @Validation(max = 10)
    @ApiModelProperty(value = "验证标识。0-未验证，1-已验证")
    private String verifyCode;

}
