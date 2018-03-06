package com.pingxun.biz.menu.app.service;


import com.pingxun.biz.menu.app.dto.SysMenuConfigDto;
import com.pingxun.biz.menu.app.dto.UserMenuAuthConfDto;
import com.pingxun.biz.menu.domain.entity.UserMenuAuthConf;
import com.pingxun.biz.menu.domain.service.UserMenuAuthConfDomainService;
import com.pingxun.core.common.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
* @Title: SysUrlsDomainService.java
* @Description:  用户菜单权限配置服务
* @author Away
* @date 2018/2/7 18:26
* @copyright 重庆平讯数据
* @version V1.0
*/
@Service
@Transactional
public class UserMenuAuthConfAppService {

    @Autowired
    private UserMenuAuthConfDomainService userMenuAuthConfDomainService;

    /**
     * @Author: Away
     * @Description: 保存（先删除）
     * @Param: datas
     * @Return int
     * @Date 2018/2/8 14:38
     * @Copyright 重庆平讯数据
     */
    public int saveWithDelete(List<UserMenuAuthConfDto> datas) throws Exception{
        return this.userMenuAuthConfDomainService.saveWithDelete(datas);
    }

    /**
     * @Author: Away
     * @Description: 按照用户查找菜单，并组装数据
     * @Param:
     * @Return java.util.List<com.pingxun.biz.menu.app.dto.UserMenuAuthConfDto>
     * @Date 2018/2/8 16:20
     * @Copyright 重庆平讯数据
     */
    public List<SysMenuConfigDto> findAndPackByUser() throws Exception{
        return this.userMenuAuthConfDomainService.findAndPackByUser();
    }

    /**
     * @Author: Away
     * @Description: 按照给定用户查找权限
     * @Param: id
     * @Return java.util.List<com.pingxun.biz.menu.app.dto.SysMenuConfigDto>
     * @Date 2018/3/1 16:55
     * @Copyright 重庆平讯数据
     */
    public List<SysMenuConfigDto> findAndPackByUserWithId(long id) throws Exception{
        return this.userMenuAuthConfDomainService.findAndPackByUserWithId(id);
    }

    /**
     * @Author: Away
     * @Description: 保存用户菜单权限数据
     * @Param: toSaveDatas
     * @Return int
     * @Date 2018/3/2 16:43
     * @Copyright 重庆平讯数据
     */
    public int saveUserMenuAuth(List<UserMenuAuthConfDto> toSaveDatas) throws Exception{
        return this.userMenuAuthConfDomainService.saveUserMenuAuth(toSaveDatas);
    }

    /**
     * @Author: Away
     * @Description: 根据ID删除菜单授权
     * @Param: ids
     * @Return void
     * @Date 2018/3/2 17:41
     * @Copyright 重庆平讯数据
     */
    public void deleteMenuAuth(Long userId,String ids) throws Exception{
        this.userMenuAuthConfDomainService.deleteMenuAuth(userId,ids);
    }

    /**
     * @Author: Away
     * @Description: 按照用户ID和菜单ID查找
     * @Param: userId
     * @Param: menuId
     * @Return com.pingxun.biz.menu.domain.entity.UserMenuAuthConf
     * @Date 2018/3/2 18:07
     * @Copyright 重庆平讯数据
     */
    public UserMenuAuthConfDto findByUserIdAndMenuId(Long userId,Long menuId) throws Exception{
        return this.userMenuAuthConfDomainService.findByUserIdAndMenuId(userId, menuId);
    }
}
