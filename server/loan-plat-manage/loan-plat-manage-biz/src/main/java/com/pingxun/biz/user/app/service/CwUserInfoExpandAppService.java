package com.pingxun.biz.user.app.service;

import com.pingxun.biz.user.app.dto.CwUserInfoExpandDto;
import com.pingxun.biz.user.domain.entity.CwUserInfoExpand;
import com.pingxun.biz.user.domain.service.CwUserInfoExpandDomainService;
import com.pingxun.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**   
* @Title: CwUserInfoExpandAppService.java
* @Description:
* @author Away
* @date 2018/2/2 11:12 
* @copyright 重庆平讯数据
* @version V1.0   
*/
@Service
public class CwUserInfoExpandAppService {

    @Autowired
    private CwUserInfoExpandDomainService cwUserInfoExpandDomainService;

    /**
     * @Author: Away
     * @Description:
     * @Param: userId
     * @Param: chanelNo
     * @Return com.pingxun.biz.user.app.dto.CwUserInfoExpandDto
     * @Date 2018/2/2 11:24
     * @Copyright 重庆平讯数据
     */
    public CwUserInfoExpandDto findByUserIdAndChanelNo(long userId,String chanelNo) throws Exception{
        CwUserInfoExpand sourceData=this.cwUserInfoExpandDomainService.findByUserIdAndChanelNo(userId, chanelNo);
        if(ObjectHelper.isNotEmpty(sourceData)&&ObjectHelper.isNotEmpty(sourceData.getId())){
            return sourceData.to(CwUserInfoExpandDto.class);
        }else{
            return null;
        }
    }
}
