package com.pingxun.biz.integral.app.service;

import com.pingxun.biz.CPContext;
import com.pingxun.biz.integral.app.dto.ActivityDto;
import com.pingxun.biz.integral.domain.entity.Activity;
import com.pingxun.biz.integral.domain.service.ActivityDomainService;
import com.pingxun.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
* @Title: ActivityAppService.java
* @Description: 活动服务
* @author Away
* @date 2017/12/6 16:49
* @copyright 重庆平讯数据
* @version V1.0
*/
@Transactional
@Service
public class ActivityAppService {

    @Autowired
    private ActivityDomainService activityDomainService;

    /**
     * @Author: Away
     * @Description: 查找最新一次活动
     * @Param:
     * @Return com.cw.biz.integral.domain.entity.Activity
     * @Date 2017/12/6 16:50
     * @Copyright 重庆平讯数据
     */
    public ActivityDto findLastActivity(){
        Activity activity=activityDomainService.findLastActivity();
        ActivityDto returnData=null;
        if(ObjectHelper.isNotEmpty(activity))returnData=activity.to(ActivityDto.class);
        if(returnData!=null&&returnData.getActivityUrl()!=null&&!returnData.getActivityUrl().equalsIgnoreCase("")){
            returnData.setActivityUrl(returnData.getActivityUrl()+"?refereeUser="+ CPContext.getContext().getSeUserInfo().getId());
        }
        return returnData;
    }

    public ActivityDto findByCode(String code) throws Exception{
        Activity sourceData=this.activityDomainService.findByCode(code);
        if(ObjectHelper.isNotEmpty(sourceData)&&ObjectHelper.isNotEmpty(sourceData.getId())){
            return sourceData.to(ActivityDto.class);
        }else{
            return null;
        }
    }

}
