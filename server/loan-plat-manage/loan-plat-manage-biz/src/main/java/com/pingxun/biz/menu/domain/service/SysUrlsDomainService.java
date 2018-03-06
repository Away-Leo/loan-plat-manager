package com.pingxun.biz.menu.domain.service;


import com.pingxun.biz.menu.app.dto.SysUrlsDto;
import com.pingxun.biz.menu.domain.entity.SysUrls;
import com.pingxun.biz.menu.domain.repositry.SysUrlsRepository;
import com.pingxun.core.common.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @Title: SysUrlsDomainService.java
* @Description:  系统链接服务
* @author Away
* @date 2018/2/7 18:26
* @copyright 重庆平讯数据
* @version V1.0
*/
@Service
public class SysUrlsDomainService {

    @Autowired
    private SysUrlsRepository sysUrlsRepository;

    /**
     * @Author: Away
     * @Description: 批量保存链接
     * @Param: datas
     * @Return int
     * @Date 2018/2/7 18:34
     * @Copyright 重庆平讯数据
     */
    public int batchSaveSysUrlsBeforeDeleteAll(List<SysUrlsDto> datas) throws Exception{
        if(ObjectHelper.isNotEmpty(datas)){
            List<SysUrls> saveData=new ArrayList<>();
            for(SysUrlsDto temp:datas){
                saveData.add(temp.toEntity(SysUrls.class));
            }
            sysUrlsRepository.deleteAll();
            return this.sysUrlsRepository.batchSave(saveData).size();
        }else{
            throw new BusinessException("PX001","批量保存链接出错,参数为空");
        }
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
    public Page<SysUrlsDto> findByConditions(Pageable pageable,SysUrlsDto params) throws Exception{
        return this.sysUrlsRepository.findByConditions(pageable, params);
    }

}
