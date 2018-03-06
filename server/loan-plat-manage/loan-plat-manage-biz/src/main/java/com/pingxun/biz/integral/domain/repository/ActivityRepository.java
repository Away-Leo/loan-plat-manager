package com.pingxun.biz.integral.domain.repository;

import com.pingxun.biz.integral.domain.entity.Activity;
import com.pingxun.core.common.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
* @Title: ActivityRepository.java
* @Description: 活动操作库
* @author Away
* @date 2017/12/6 16:33
* @copyright 重庆平讯数据
* @version V1.0
*/
public interface ActivityRepository extends BaseRepository<Activity,Long> {

    /**
     * @Author: Away
     * @Description: 按照是否有效和创建时间查找第一条
     * @Param: isValid
     * @Return com.cw.biz.integral.domain.entity.Activity
     * @Date 2017/12/6 17:44
     * @Copyright 重庆平讯数据
     */
    public Activity findFirst1ByIsValidOrderByRawAddTimeDesc(boolean isValid);

    public Activity findByIsValidAndCode(boolean isValid,String code);

}
