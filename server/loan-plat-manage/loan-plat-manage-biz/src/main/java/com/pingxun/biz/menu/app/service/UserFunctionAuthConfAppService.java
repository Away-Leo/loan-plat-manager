package com.pingxun.biz.menu.app.service;


import com.pingxun.biz.menu.app.dto.UserFunctionAuthConfDto;
import com.pingxun.biz.menu.domain.entity.UserFunctionAuthConf;
import com.pingxun.biz.menu.domain.service.UserFunctionAuthConfDomainService;
import com.pingxun.core.common.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
* @Title: SysUrlsDomainService.java
* @Description:  用户功能权限配置服务
* @author Away
* @date 2018/2/7 18:26
* @copyright 重庆平讯数据
* @version V1.0
*/
@Service
@Transactional
public class UserFunctionAuthConfAppService {

    @Autowired
    private UserFunctionAuthConfDomainService userFunctionAuthConfDomainService;

    /**
     * @Author: Away
     * @Description: 保存用户功能权限（先删除旧权限）
     * @Param: toSaveData
     * @Return int
     * @Date 2018/2/8 12:09
     * @Copyright 重庆平讯数据
     */
    public int saveFunctionWithDelete(String[] toSaveData) throws Exception{
        return this.userFunctionAuthConfDomainService.saveFunctionWithDelete(toSaveData);
    }

    public String authoriedUrls(Long userId){
        return this.userFunctionAuthConfDomainService.authoriedUrls(userId);
    }

    /**
     * @Author: Away
     * @Description: 按照条件查找分页数据
     * @Param: pageable
     * @Param: params
     * @Return org.springframework.data.domain.Page<com.pingxun.biz.menu.app.dto.UserFunctionAuthConfDto>
     * @Date 2018/3/5 14:31
     * @Copyright 重庆平讯数据
     */
    public Page<UserFunctionAuthConfDto> findByConditions(Pageable pageable, UserFunctionAuthConfDto params) throws Exception{
        return this.userFunctionAuthConfDomainService.findByConditions(pageable, params);
    }

    /**
     * @Author: Away
     * @Description: 保存
     * @Param: toSaveData
     * @Return void
     * @Date 2018/3/5 19:08
     * @Copyright 重庆平讯数据
     */
    public void saveFunctionAuth(List<UserFunctionAuthConfDto> toSaveData) throws Exception{
        this.userFunctionAuthConfDomainService.saveFunctionAuth(toSaveData);
    }
    /**
     * @Author: Away
     * @Description: 通过id删除
     * @Param: ids
     * @Return void
     * @Date 2018/3/5 19:37
     * @Copyright 重庆平讯数据
     */
    public void deleteByIds(String ids) throws Exception{
        this.userFunctionAuthConfDomainService.deleteByIds(ids);
    }

}
