package com.spring.boot.sports.base;

import com.spring.boot.sports.annotation.Validation;
import com.spring.boot.sports.util.IdGenerator;
import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * author yuderen
 * version 2018/11/20 13:47
 */
@Data
public class BaseBean {

    @Id
    @Validation(max = 32)
    private String gid;
    private Integer status;
    private String createUser;
    private String updateUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public void initCreateInfo(String userId){
        this.gid = IdGenerator.generateId();
        this.status = 101;
        if (null != userId){
            this.createUser = userId;
        }
        this.createTime = LocalDateTime.now();
        initUpdateInfo(userId);
    }

    public void initUpdateInfo(String userId){
        if (null != userId){
            this.updateUser = userId;
        }
        this.updateTime = LocalDateTime.now();
    }

}
