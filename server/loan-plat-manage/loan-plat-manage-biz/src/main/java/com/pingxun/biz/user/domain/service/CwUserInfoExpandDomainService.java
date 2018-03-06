package com.pingxun.biz.user.domain.service;

import com.google.common.collect.Lists;
import com.pingxun.biz.CwException;
import com.pingxun.biz.user.app.dto.CwUserInfoDto;
import com.pingxun.biz.user.domain.entity.CwUserInfo;
import com.pingxun.biz.user.domain.entity.CwUserInfoExpand;
import com.pingxun.biz.user.domain.repository.SeUserInfoRepository;
import com.pingxun.biz.user.domain.repository.UserInfoExpandRepository;
import com.pingxun.core.common.util.ObjectHelper;
import com.pingxun.core.common.util.Utils;
import com.zds.common.lang.exception.BusinessException;
import com.zds.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**   
* @Title: CwUserInfoExpandDomainService.java
* @Description:
* @author Away
* @date 2018/2/2 10:35 
* @copyright 重庆平讯数据
* @version V1.0   
*/
@Service
public class CwUserInfoExpandDomainService {

    @Autowired
    private UserInfoExpandRepository userInfoExpandRepository;

    /**
     * @Author: Away
     * @Description: 按照用户ID和渠道编号查找用户拓展数据
     * @Param: userId
     * @Param: chanelNo
     * @Return com.pingxun.biz.user.domain.entity.CwUserInfoExpand
     * @Date 2018/2/2 11:05
     * @Copyright 重庆平讯数据
     */
    public CwUserInfoExpand findByUserIdAndChanelNo(long userId,String chanelNo) throws Exception{
        if(ObjectHelper.isNotEmpty(userId)&&ObjectHelper.isNotEmpty(chanelNo)){
            return this.userInfoExpandRepository.findByUserIdAndChanelNo(userId, chanelNo);
        }else{
            throw new BusinessException("PX001","根据用户ID和渠道编号查找用户拓展信息出错，参数为空");
        }
    }
}
