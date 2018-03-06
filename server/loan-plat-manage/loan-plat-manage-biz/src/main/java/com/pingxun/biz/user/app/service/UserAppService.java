package com.pingxun.biz.user.app.service;

import com.pingxun.biz.user.app.dto.UserDto;
import com.pingxun.biz.user.domain.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
* @Title: UserAppService.java
* @Description:  用户服务
* @author Away
* @date 2018/3/1 14:51
* @copyright 重庆平讯数据
* @version V1.0
*/
@Service
@Transactional
public class UserAppService {

    @Autowired
    private UserDomainService userDomainService;


    /**
     * @Author: Away
     * @Description: 按照条件查找
     * @Param: pageable
     * @Param: conditions
     * @Return org.springframework.data.domain.Page<com.pingxun.biz.user.app.dto.UserDto>
     * @Date 2018/3/1 14:54
     * @Copyright 重庆平讯数据
     */
    public Page<UserDto> findManageUserPage(Pageable pageable,UserDto conditions){
        return this.userDomainService.findManageUserPageByCondition(pageable, conditions);
    }

}
