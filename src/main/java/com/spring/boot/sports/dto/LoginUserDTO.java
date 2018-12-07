package com.spring.boot.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * author yuderen
 * version 2018/11/24 17:05
 */
@Data
public class LoginUserDTO {

    @ApiModelProperty(value = "旧密码。")
    private String oldPassword;

    @ApiModelProperty(value = "新密码。")
    private String password;

}
