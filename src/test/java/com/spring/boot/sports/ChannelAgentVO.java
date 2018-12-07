package com.spring.boot.sports;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * author yuderen
 * version 2018/12/6 10:17
 */
@Data
public class ChannelAgentVO {

    private String gid;
    private String channelNo;
    private Integer count;
    private List<ChannelAgentVO> childList = new ArrayList();

}
