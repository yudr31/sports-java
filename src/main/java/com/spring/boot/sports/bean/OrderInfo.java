package com.spring.boot.sports.bean;

import com.spring.boot.sports.annotation.Validation;
import com.spring.boot.sports.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * author yuderen
 * version 2018/11/23 11:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderInfo extends BaseBean {

    @Validation(max = 32)
    @ApiModelProperty(value = "商户ID。")
    private String shopId;

    @Validation(max = 120)
    @ApiModelProperty(value = "球馆。")
    private String arena;

    @Validation(max = 120)
    @ApiModelProperty(value = "运动项目。")
    private String item;

    @Validation(max = 64)
    @ApiModelProperty(value = "订单号。")
    private String orderNo;

    @Validation
    @ApiModelProperty(value = "下单日期。")
    private LocalDate bookDate;

    @Validation
    @ApiModelProperty(value = "订单日期。")
    private LocalDate orderDate;

    @Validation(max = 20)
    @ApiModelProperty(value = "场地名称。")
    private String court;

    @Validation(max = 20)
    @ApiModelProperty(value = "场地信息。")
    private String startTime;

    @Validation(max = 120)
    @ApiModelProperty(value = "场地及时间信息。")
    private String spaceTime;

    @Validation(max = 200)
    @ApiModelProperty(value = "支付金额。")
    private BigDecimal amount;

    @Validation(max = 20)
    @ApiModelProperty(value = "验证码。")
    private String verifyCode;

    @Validation(max = 120)
    @ApiModelProperty(value = "支付状态。")
    private String payStatus;

    @Validation(max = 255)
    @ApiModelProperty(value = "球馆地址。")
    private String address;

}