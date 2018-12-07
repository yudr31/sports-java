package com.spring.boot.sports.base;

import lombok.Data;

/**
 * author yuderen
 * version 2018/11/20 13:56
 */
@Data
public class Page {

    private int currentPage = 1;
    private int numPerPage = 20;

}
