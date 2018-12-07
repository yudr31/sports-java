package com.spring.boot.sports.mapper;

import com.spring.boot.sports.base.BaseBeanMapper;
import com.spring.boot.sports.bean.LoginUser;

/**
 * author yuderen
 * version 2018/11/20 13:47
 */
public interface LoginUserMapper extends BaseBeanMapper<LoginUser> {

    LoginUser fetchLoginUserByUserName(String userName);

}
