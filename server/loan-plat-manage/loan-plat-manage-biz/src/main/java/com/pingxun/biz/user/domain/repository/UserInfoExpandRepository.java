package com.pingxun.biz.user.domain.repository;


import com.pingxun.biz.user.domain.entity.CwUserInfoExpand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
* @Title: UserInfoExpandRepository.java
* @Description:  用户拓展信息操作库
* @author Away
* @date 2018/2/2 10:26
* @copyright 重庆平讯数据
* @version V1.0
*/
public interface UserInfoExpandRepository extends JpaRepository<CwUserInfoExpand,Long> {

    /**
     * @Author: Away
     * @Description: 按照用户ID和渠道编号查找
     * @Param: userId
     * @Param: ChanelNo
     * @Return com.pingxun.biz.user.domain.entity.CwUserInfoExpand
     * @Date 2018/2/2 10:33
     * @Copyright 重庆平讯数据
     */
    public CwUserInfoExpand findByUserIdAndChanelNo(long userId,String ChanelNo);
}
