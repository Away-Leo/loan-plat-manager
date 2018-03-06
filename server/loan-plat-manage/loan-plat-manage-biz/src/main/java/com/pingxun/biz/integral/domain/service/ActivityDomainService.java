package com.pingxun.biz.integral.domain.service;

import com.pingxun.biz.integral.domain.entity.Activity;
import com.pingxun.biz.integral.domain.repository.ActivityRepository;
import com.pingxun.core.common.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @Title: ActivityDomainService.java
* @Description: 活动数据服务service
* @author Away
* @date 2017/12/6 16:46
* @copyright 重庆平讯数据
* @version V1.0
*/
@Service
public class ActivityDomainService {

    @Autowired
    private ActivityRepository activityRepository;

    /**
     * @Author: Away
     * @Description: 查找最新一条活动
     * @Param:
     * @Return com.cw.biz.integral.domain.entity.Activity
     * @Date 2017/12/6 16:48
     * @Copyright 重庆平讯数据
     */
    public Activity findLastActivity(){
        return activityRepository.findFirst1ByIsValidOrderByRawAddTimeDesc(true);
    }

    public Activity findByCode(String code) throws Exception{
        if(ObjectHelper.isNotEmpty(code)){
            return activityRepository.findByIsValidAndCode(true,code);
        }else{
            throw new BusinessException("PX001","按照编号查找活动出错，参数为空");
        }
    }
}
