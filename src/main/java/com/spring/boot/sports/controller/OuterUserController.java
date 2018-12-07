package com.spring.boot.sports.controller;

import com.github.pagehelper.PageInfo;
import com.spring.boot.sports.annotation.ValidationExecutor;
import com.spring.boot.sports.base.BaseController;
import com.spring.boot.sports.base.ResponseData;
import com.spring.boot.sports.bean.OuterUser;
import com.spring.boot.sports.service.OuterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * author yuderen
 * version 2018/11/20 19:57
 */
@RestController
@RequestMapping("/outerUser")
public class OuterUserController extends BaseController {

    @Autowired
    private OuterUserService outerUserService;

    @RequestMapping("/outerUserList")
    public ResponseData<PageInfo<OuterUser>> outerUserList(OuterUser outerUser){
        return successResponse(outerUserService.selectRecordList(outerUser));
    }

    @RequestMapping("/addOuterUser")
    public ResponseData<Integer> addOuterUser(OuterUser outerUser){
        String[] fields = {"nickname","userName","password"};
        ValidationExecutor.notNullValidate(fields,outerUser);
        Integer count = outerUserService.insertSelective(outerUser);
        return count == 0 ? errorResponse() : successResponse();
    }

    @RequestMapping("/editOuterUser")
    public ResponseData<Integer> editOuterUser(OuterUser outerUser){
        String[] fields = {"gid","nickname","userName","password"};
        ValidationExecutor.notNullValidate(fields,outerUser);
        Integer count = outerUserService.updateSelectiveByKey(outerUser);
        return count == 0 ? errorResponse() : successResponse();
    }

    @RequestMapping("/outerUserDetail")
    public ResponseData<Integer> outerUserDetail(OuterUser outerUser){
        String[] fields = {"gid"};
        ValidationExecutor.notNullValidate(fields,outerUser);
        OuterUser result = outerUserService.fetchRecordByGid(outerUser.getGid());
        return successResponse(result);
    }

    @RequestMapping("/removeOuterUser")
    public ResponseData<Integer> removeOuterUser(OuterUser outerUser){
        String[] fields = {"gid"};
        ValidationExecutor.notNullValidate(fields,outerUser);
        Integer count = outerUserService.removeRecord(outerUser.getGid());
        return count == 0 ? errorResponse() : successResponse();
    }

}
