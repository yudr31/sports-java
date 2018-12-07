package com.spring.boot.sports.bean;

import com.spring.boot.sports.annotation.Validation;
import com.spring.boot.sports.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * author yuderen
 * version 2018/11/20 18:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OuterUser extends BaseBean {

    @Validation(max = 32)
    @ApiModelProperty(value = "登录账号名称。")
    private String userName;

    @Validation(max = 64)
    @ApiModelProperty(value = "账号密码。")
    private String password;

    @Validation(max = 120)
    @ApiModelProperty(value = "密码盐随机串。")
    private String saltRandom;

    @Validation(max = 20)
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @Validation(max = 120)
    @ApiModelProperty(value = "个性签名。")
    private String signature;

}
