package com.pingxun.biz.menu.app.service;


import com.pingxun.biz.menu.app.dto.SysUrlsDto;
import com.pingxun.biz.menu.domain.service.SysUrlsDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**   
* @Title: SysUrlsAppService.java
* @Description:  
* @author Away
* @date 2018/2/7 18:27 
* @copyright 重庆平讯数据
* @version V1.0   
*/
@Transactional
@Service
public class SysUrlsAppService {

    @Autowired
    private SysUrlsDomainService sysUrlsDomainService;

    /**
     * @Author: Away
     * @Description: 批量保存（先清空数据）
     * @Param: sysUrlsDtos
     * @Return int
     * @Date 2018/2/7 18:56
     * @Copyright 重庆平讯数据
     */
    public int batchSaveSysUrlsBeforeDeleteAll(List<SysUrlsDto> sysUrlsDtos) throws Exception{
        return this.sysUrlsDomainService.batchSaveSysUrlsBeforeDeleteAll(sysUrlsDtos);
    }

    /**
     * @Author: Away
     * @Description: 根据参数查询分页
     * @Param: pageable
     * @Param: params
     * @Return org.springframework.data.domain.Page<com.pingxun.biz.menu.app.dto.SysUrlsDto>
     * @Date 2018/3/5 15:50
     * @Copyright 重庆平讯数据
     */
    public Page<SysUrlsDto> findByConditions(Pageable pageable, SysUrlsDto params) throws Exception{
        return this.sysUrlsDomainService.findByConditions(pageable, params);
    }
}
