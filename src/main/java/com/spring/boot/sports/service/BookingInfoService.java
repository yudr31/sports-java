package com.spring.boot.sports.service;

import com.spring.boot.sports.base.BaseException;
import com.spring.boot.sports.base.BaseService;
import com.spring.boot.sports.bean.BookingInfo;
import com.spring.boot.sports.mapper.BookingInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author yuderen
 * version 2018/11/21 16:32
 */
@Service
public class BookingInfoService extends BaseService<BookingInfoMapper, BookingInfo> {

    @Autowired
    private BookingInfoMapper bookingInfoMapper;

    @Override
    public Integer insertSelective(BookingInfo bookingInfo) {
        if (1 == bookingInfo.getOrderNow()){
            if (StringUtils.isEmpty(bookingInfo.getOrderUser())){
                throw new BaseException("orderUser","下单用户不能为空");
            }
        }
        return super.insertSelective(bookingInfo);
    }

    public Integer removeRecord(String gid){
        BookingInfo bookingInfo = new BookingInfo();
        bookingInfo.setGid(gid);
        bookingInfo.setStatus(REMOVE_STATUS);
        return updateSelectiveByKey(bookingInfo);
    }

    public Integer updateBookingState(BookingInfo bookingInfo){
        if (bookingInfo.getBookingState() == 1){
            bookingInfo.setGrabCount(0);
            bookingInfo.setNotifyCount(0);
        }
        return super.updateSelectiveByKey(bookingInfo);
    }

}