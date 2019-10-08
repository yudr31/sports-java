package com.spring.boot.sports.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.boot.sports.annotation.ValidationExecutor;
import com.spring.boot.sports.service.UserManagerService;
import com.spring.boot.sports.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * author yuderen
 * version 2018/11/20 13:54
 */
@Slf4j
public class BaseService<T1 extends BaseBeanMapper<T2>, T2 extends BaseBean> {

    protected static final Integer AVAILABLE_STATUS = 101;  // 有效状态
    protected static final Integer REMOVE_STATUS = 103;     // 删除状态

    @Autowired
    private T1 baseMapper;
    @Autowired
    protected UserManagerService userManagerService;

    public PageInfo<T2> selectRecordList(T2 t2){
        Page page = this.getPage(SpringUtil.getRequest());
        PageHelper.startPage(page.getCurrentPage(),page.getNumPerPage());
        return new PageInfo(fetchRecordList(t2));
    }

    public List<T2> fetchRecordList(T2 t2){
        t2.setRecordStatus(AVAILABLE_STATUS);
        return baseMapper.fetchRecordList(t2);
    }

    public T2 fetchRecordByGid(String gid){
        return baseMapper.selectByPrimaryKey(gid);
    }

    public Integer insertSelective(T2 t2){
        String beanName = t2.getClass().getSimpleName();
        log.info("开始添加{}记录信息",beanName);
        int count = 0;
        try {
            t2.initCreateInfo(null);
            count += baseMapper.insertSelective(t2);
        } catch (Exception e){
            log.error("添加{}记录信息失败！{}:{}",beanName,beanName,t2);
            log.error("添加信息出现错误", e);
        }
        log.info("结束添加{}记录信息",beanName);
        return count;
    }

    public Integer updateSelectiveByKey(T2 t2){
        String beanName = t2.getClass().getSimpleName();
        log.info("开始更新{}记录信息",beanName);
        int count = 0;
        try {
            t2.initUpdateInfo(null);
            count += baseMapper.updateByPrimaryKeySelective(t2);
        } catch (Exception e){
            log.error("更新{}记录信息失败！{}:{}",beanName,beanName,t2);
            log.error("更新信息出现错误", e);
        }
        log.info("结束更新{}记录信息",beanName);
        return count;
    }

    public Page getPage(HttpServletRequest request){
        Page page = new Page();
        page.setCurrentPage(1);
        page.setNumPerPage(20);
        String pageNo = request.getParameter("pageNum");
        String pageSize = request.getParameter("numPerPage");
        if(null != pageNo && !"".equals(pageNo)){
            page.setCurrentPage(Integer.valueOf(pageNo));
        }
        if (null != pageSize && !"".equals(pageSize)){
            page.setNumPerPage(Integer.valueOf(pageSize));
        }
        return page;
    }

}
