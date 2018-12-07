package com.spring.boot.sports.bean;

import com.spring.boot.sports.annotation.Validation;
import com.spring.boot.sports.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * author yuderen
 * version 2018/11/21 16:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BookingInfo extends BaseBean {

    @Validation(max = 20)
    @ApiModelProperty(value = "开始时间。")
    private String bookingTime;

    @Validation(max = 3)
    @ApiModelProperty(value = "时长。单位：小时")
    private Integer duration;

    @Validation(max = 20)
    @ApiModelProperty(value = "预订日期。按周计算（周一，周二...周日。）")
    private String bookingDate;

    @Validation(max = 20)
    @ApiModelProperty(value = "球场类型。")
    private String courtType;

    @Validation(max = 1)
    @ApiModelProperty(value = "是否立即下单。0-否，1-是", example = "1")
    private Integer orderNow;

    @Validation(max = 32)
    @ApiModelProperty(value = "下单用户账号ID。")
    private String orderUser;

    @Validation(max = 2)
    @ApiModelProperty(value = "通知类型。1-短信，2-电话", example = "1")
    private Integer notifyType;

    @Validation(max = 20)
    @ApiModelProperty(value = "通知手机号。")
    private String phone;

    @Validation(max = 11)
    @ApiModelProperty(value = "最大通知次数。", example = "1")
    private Integer maxNotify;

    @Validation(max = 11)
    @ApiModelProperty(value = "通知时间间隔。单位：秒", example = "1")
    private Integer timeInterval;

    @Validation
    @ApiModelProperty(value = "上次通知时间。", example = "1")
    private LocalDateTime notifyTime;

    @Validation(max = 2)
    @ApiModelProperty(value = "预订状态。0-停止预订，1-开启预订", example = "1")
    private Integer bookingState;

    @Validation(max = 11)
    @ApiModelProperty(value = "抓取次数。", example = "1")
    private Integer grabCount;

    @Validation(max = 11)
    @ApiModelProperty(value = "通知次数。", example = "1")
    private Integer notifyCount;

}
