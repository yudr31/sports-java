package com.spring.boot.sports.service;

import com.spring.boot.sports.base.BaseService;
import com.spring.boot.sports.bean.NotifyAccount;
import com.spring.boot.sports.mapper.NotifyAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author yuderen
 * version 2018/11/22 16:26
 */
@Service
public class NotifyAccountService extends BaseService<NotifyAccountMapper, NotifyAccount> {

    @Autowired
    private NotifyAccountMapper notifyAccountMapper;

    public Integer removeRecord(String gid){
        NotifyAccount notifyAccount = new NotifyAccount();
        notifyAccount.setGid(gid);
        notifyAccount.setStatus(REMOVE_STATUS);
        return updateSelectiveByKey(notifyAccount);
    }

}