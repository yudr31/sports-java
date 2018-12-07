package com.spring.boot.sports.util;

import java.util.UUID;

/**
 * author yuderen
 * version 2018/11/20 13:49
 */
public class IdGenerator {

    public static String generateId(){
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

}
