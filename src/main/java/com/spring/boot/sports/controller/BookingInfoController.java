package com.spring.boot.sports.controller;

import com.github.pagehelper.PageInfo;
import com.spring.boot.sports.annotation.ValidationExecutor;
import com.spring.boot.sports.base.BaseController;
import com.spring.boot.sports.base.ResponseData;
import com.spring.boot.sports.bean.BookingInfo;
import com.spring.boot.sports.bean.OuterUser;
import com.spring.boot.sports.service.BookingInfoService;
import com.spring.boot.sports.service.OuterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * author yuderen
 * version 2018/11/20 19:57
 */
@RestController
@RequestMapping("/bookingInfo")
public class BookingInfoController extends BaseController {

    @Autowired
    private BookingInfoService bookingInfoService;

    @RequestMapping("/bookingInfoList")
    public ResponseData<PageInfo<OuterUser>> bookingInfoList(BookingInfo bookingInfo){
        return successResponse(bookingInfoService.selectRecordList(bookingInfo));
    }

    @RequestMapping("/addBookingInfo")
    public ResponseData<Integer> addBookingInfo(BookingInfo bookingInfo){
        String[] fields = {"bookingTime","duration","bookingDate","courtType","orderNow","notifyType","phone"};
        ValidationExecutor.notNullValidate(fields,bookingInfo);
        Integer count = bookingInfoService.insertSelective(bookingInfo);
        return count == 0 ? errorResponse() : successResponse(count);
    }

    @RequestMapping("/editBookingInfo")
    public ResponseData<Integer> editBookingInfo(BookingInfo bookingInfo){
        String[] fields = {"bookingTime","duration","bookingDate","courtType","orderNow","notifyType","phone"};
        ValidationExecutor.notNullValidate(fields,bookingInfo);
        Integer count = bookingInfoService.updateSelectiveByKey(bookingInfo);
        return count == 0 ? errorResponse() : successResponse(count);
    }

    @RequestMapping("/updateBookingState")
    public ResponseData<Integer> updateBookingState(BookingInfo bookingInfo){
        String[] fields = {"gid","bookingState"};
        ValidationExecutor.notNullValidate(fields,bookingInfo);
        Integer count = bookingInfoService.updateBookingState(bookingInfo);
        return count == 0 ? errorResponse() : successResponse(count);
    }

    @RequestMapping("/bookingInfoDetail")
    public ResponseData<BookingInfo> bookingInfoDetail(BookingInfo bookingInfo){
        String[] fields = {"gid"};
        ValidationExecutor.notNullValidate(fields,bookingInfo);
        return successResponse(bookingInfoService.fetchRecordByGid(bookingInfo.getGid()));
    }

    @RequestMapping("/removeBookingInfo")
    public ResponseData<Integer> removeBookingInfo(BookingInfo bookingInfo){
        String[] fields = {"gid"};
        ValidationExecutor.notNullValidate(fields,bookingInfo);
        Integer count = bookingInfoService.removeRecord(bookingInfo.getGid());
        return count == 0 ? errorResponse() : successResponse(count);
    }

}
