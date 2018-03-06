package com.pingxun.web.front.controller.activit;

import com.pingxun.biz.integral.app.dto.ActivityDto;
import com.pingxun.biz.integral.app.service.ActivityAppService;
import com.pingxun.web.common.dto.CPViewResultInfo;
import com.pingxun.web.front.controller.AbstractFrontController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
* @Title: ActivityController.java
* @Description: 活动controller
* @author Away
* @date 2017/12/6 16:52
* @copyright 重庆平讯数据
* @version V1.0
*/
@RestController
public class ActivityController extends AbstractFrontController {

    private static final Logger logger= LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityAppService activityAppService;

    @GetMapping("/activity/findRecentActivity.json")
    public CPViewResultInfo getActivity(CPViewResultInfo info){
        try{
            ActivityDto dto=activityAppService.findLastActivity();
            info.setData(dto);
            info.setSuccess(true);
            info.setMessage("成功");
        }catch (Exception e){
            info.setMessage(e.getMessage());
            info.setSuccess(false);
            logger.error("获取最新活动出错",e);
            e.printStackTrace();
        }
        return info;
    }

    @GetMapping("/activity/findActivityWithChanel.json")
    public CPViewResultInfo findActivityWithChanel(CPViewResultInfo info,String chanelNo){
        try{
            ActivityDto dto=activityAppService.findByCode(chanelNo);
            info.setData(dto);
            info.setSuccess(true);
            info.setMessage("成功");
        }catch (Exception e){
            info.setMessage(e.getMessage());
            info.setSuccess(false);
            logger.error("按照编号获取活动出错",e);
            e.printStackTrace();
        }
        return info;
    }

}
