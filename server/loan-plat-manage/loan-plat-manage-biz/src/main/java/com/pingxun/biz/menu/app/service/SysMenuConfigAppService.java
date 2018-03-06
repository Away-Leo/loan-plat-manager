package com.pingxun.biz.menu.app.service;


import com.pingxun.biz.menu.app.dto.SysMenuConfigDto;
import com.pingxun.biz.menu.domain.service.SysMenuConfigDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
* @Title: SysUrlsDomainService.java
* @Description:  系统菜单配置服务
* @author Away
* @date 2018/2/7 18:26
* @copyright 重庆平讯数据
* @version V1.0
*/
@Service
@Transactional
public class SysMenuConfigAppService {

    @Autowired
    private SysMenuConfigDomainService sysMenuConfigDomainService;


    /**
     * @Author: Away
     * @Description: 按照ID查找
     * @Param: id
     * @Return com.pingxun.biz.menu.app.dto.SysMenuConfigDto
     * @Date 2018/2/8 11:35
     * @Copyright 重庆平讯数据
     */
    public SysMenuConfigDto findById(Long id) throws Exception{
        return this.sysMenuConfigDomainService.findById(id);
    }

    /**
     * @Author: Away
     * @Description: 更新或保存系统菜单配置
     * @Param: sourceData
     * @Return com.pingxun.biz.menu.app.dto.SysMenuConfigDto
     * @Date 2018/2/8 11:30
     * @Copyright 重庆平讯数据
     */
    public SysMenuConfigDto saveOrUpdate(SysMenuConfigDto sourceData) throws Exception{
        return this.sysMenuConfigDomainService.saveOrUpdate(sourceData);
    }

    /**
     * @Author: Away
     * @Description: 获得超级管理员的树形菜单
     * @Param:
     * @Return java.util.List<com.pingxun.biz.menu.app.dto.SysMenuConfigDto>
     * @Date 2018/2/8 16:44
     * @Copyright 重庆平讯数据
     */
    public List<SysMenuConfigDto> getAdminTree() throws Exception{
        return this.sysMenuConfigDomainService.getAdminTree();
    }

    /**
     * @Author: Away
     * @Description: 根据参数查找分页数据
     * @Param: pageable
     * @Param: condition
     * @Return org.springframework.data.domain.Page<com.pingxun.biz.menu.app.dto.SysMenuConfigDto>
     * @Date 2018/2/23 14:02
     * @Copyright 重庆平讯数据
     */
    public Page<SysMenuConfigDto> findByConditions(Pageable pageable,SysMenuConfigDto condition) throws Exception{
        return this.sysMenuConfigDomainService.findByConditions(pageable, condition);
    }

    /**
     * @Author: Away
     * @Description: 根据ID删除
     * @Param: id
     * @Return void
     * @Date 2018/2/23 14:17
     * @Copyright 重庆平讯数据
     */
    public void deleteMenu(Long id) throws Exception{
        this.sysMenuConfigDomainService.deleteSysMenuConf(id);
    }
}
