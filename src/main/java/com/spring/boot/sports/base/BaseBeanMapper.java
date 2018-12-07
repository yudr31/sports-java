package com.spring.boot.sports.base;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * author yuderen
 * version 2018/11/20 13:44
 */
public interface BaseBeanMapper<T> extends Mapper<T> {

    List<T> fetchRecordList(T t);

    List<T> fetchRecordSortList(@Param("param") T t,@Param("sortStr") String sortStr);

}
