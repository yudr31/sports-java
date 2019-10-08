package com.spring.boot.sports.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.boot.sports.base.BaseService;
import com.spring.boot.sports.base.Page;
import com.spring.boot.sports.bean.OrderInfo;
import com.spring.boot.sports.mapper.OrderInfoMapper;
import com.spring.boot.sports.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author yuderen
 * version 2018/11/23 11:46
 */
@Service
public class OrderInfoService extends BaseService<OrderInfoMapper, OrderInfo> {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public Integer removeRecord(String gid){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setGid(gid);
        orderInfo.setRecordStatus(REMOVE_STATUS);
        return updateSelectiveByKey(orderInfo);
    }

    public PageInfo<OrderInfo> selectRecordList(OrderInfo orderInfo){
        Page page = this.getPage(SpringUtil.getRequest());
        PageHelper.startPage(page.getCurrentPage(),page.getNumPerPage());
        return new PageInfo(fetchRecordSortList(orderInfo));
    }

    public List<OrderInfo> fetchRecordSortList(OrderInfo orderInfo){
        String sortStr = "book_date desc";
        orderInfo.setRecordStatus(AVAILABLE_STATUS);
        return orderInfoMapper.fetchRecordSortList(orderInfo,sortStr);
    }

}
