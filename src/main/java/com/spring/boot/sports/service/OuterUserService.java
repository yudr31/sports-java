package com.spring.boot.sports.service;

import com.spring.boot.sports.base.BaseService;
import com.spring.boot.sports.bean.OuterUser;
import com.spring.boot.sports.mapper.OuterUserMapper;
import org.springframework.stereotype.Service;

/**
 * author yuderen
 * version 2018/11/20 19:54
 */
@Service
public class OuterUserService extends BaseService<OuterUserMapper, OuterUser> {

    public Integer removeRecord(String gid){
        OuterUser outerUser = new OuterUser();
        outerUser.setGid(gid);
        outerUser.setStatus(REMOVE_STATUS);
        return updateSelectiveByKey(outerUser);
    }

}
