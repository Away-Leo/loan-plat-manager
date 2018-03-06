package com.pingxun.biz.menu.domain.repositry;


import com.pingxun.biz.menu.domain.entity.UserMenuAuthConf;
import com.pingxun.core.common.base.BaseRepository;

import java.util.List;

/**
* @Title: SysUrlsRepository.java
* @Description: 用户菜单权限配置操作库
* @author Away
* @date 2018/2/7 18:25
* @copyright 重庆平讯数据
* @version V1.0
*/
public interface UserMenuAuthConfRepository extends BaseRepository<UserMenuAuthConf,Long> {

    /**
     * @Author: Away
     * @Description: 按照用户删除
     * @Param: userId
     * @Return void
     * @Date 2018/2/8 14:04
     * @Copyright 重庆平讯数据
     */
    public void deleteByUserId(long userId);

    /**
     * @Author: Away
     * @Description: 按照用户查找数据
     * @Param: userId
     * @Return java.util.List<com.pingxun.biz.menu.domain.entity.UserMenuAuthConf>
     * @Date 2018/2/8 15:12
     * @Copyright 重庆平讯数据
     */
    public List<UserMenuAuthConf> findByUserId(long userId);


    /**
     * @Title: UserMenuAuthConfRepository.java
     * @Description:  按照用户和菜单ID查找
     * @author Away
     * @date 2018/3/2 18:04
     * @copyright 重庆平讯数据
     * @version V1.0
     */
    public UserMenuAuthConf findByUserIdAndMenuId(Long userId,Long menuId);

    /**
     * @Author: Away
     * @Description: 根据用户和父菜单ID查找子菜单
     * @Param: userId
     * @Param: menuPid
     * @Return java.util.List<com.pingxun.biz.menu.domain.entity.UserMenuAuthConf>
     * @Date 2018/3/5 10:56
     * @Copyright 重庆平讯数据
     */
    public List<UserMenuAuthConf> findByUserIdAndMenuPid(Long userId,Long menuPid);

}
