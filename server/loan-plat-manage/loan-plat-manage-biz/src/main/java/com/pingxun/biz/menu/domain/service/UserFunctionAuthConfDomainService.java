package com.pingxun.biz.menu.domain.service;


import com.pingxun.biz.CPContext;
import com.pingxun.biz.menu.app.dto.UserFunctionAuthConfDto;
import com.pingxun.biz.menu.domain.entity.UserFunctionAuthConf;
import com.pingxun.biz.menu.domain.repositry.UserFunctionAuthConfRepository;
import com.pingxun.core.common.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
public class UserFunctionAuthConfDomainService {

    @Autowired
    private UserFunctionAuthConfRepository userFunctionAuthConfRepository;

    private static final Logger logger= LoggerFactory.getLogger(UserFunctionAuthConfDomainService.class);

    /**
     * @Author: Away
     * @Description: 保存用户功能权限（先删除旧权限）
     * @Param: toSaveData
     * @Return int
     * @Date 2018/2/8 12:09
     * @Copyright 重庆平讯数据
     */
    public int saveFunctionWithDelete(String[] toSaveData) throws Exception{
        if(ObjectHelper.isNotEmpty(toSaveData)&&toSaveData.length>0){
            List<UserFunctionAuthConf> sourceData=new ArrayList<>();
            for(int i=0;i<toSaveData.length;i++){
                sourceData.add(new UserFunctionAuthConf(CPContext.getContext().getSeUserInfo().getId(),toSaveData[i]));
            }
            userFunctionAuthConfRepository.deleteByUserId(CPContext.getContext().getSeUserInfo().getId());
            return this.userFunctionAuthConfRepository.batchSave(sourceData).size();
        }else{
            throw new BusinessException("PX001","保存用户功能权限出错，参数为空");
        }
    }

    /**
     * @Author: Away
     * @Description: 获得功能权限
     * @Param:
     * @Return java.lang.String
     * @Date 2018/2/8 17:01
     * @Copyright 重庆平讯数据
     */
    public String authoriedUrls(Long userId){
        String returnData="";
        try{
            List<UserFunctionAuthConf> sourceData=this.userFunctionAuthConfRepository.findByUserId(userId);
            if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
                for(UserFunctionAuthConf temp:sourceData){
                    returnData+=temp.getUrl()+";";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取用户功能权限出错",e);
        }
        return returnData;
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
    public Page<UserFunctionAuthConfDto> findByConditions(Pageable pageable,UserFunctionAuthConfDto params) throws Exception{
        Page<UserFunctionAuthConf> sourceData=this.userFunctionAuthConfRepository.findByConditions(pageable, params);
        List<UserFunctionAuthConfDto> returnListData=new ArrayList<>();
        if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.getContent().size()>0){
            for(UserFunctionAuthConf temp:sourceData.getContent()){
                returnListData.add(temp.to(UserFunctionAuthConfDto.class));
            }
        }
        return new PageImpl<UserFunctionAuthConfDto>(returnListData,pageable,sourceData.getTotalElements());
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
        if(ObjectHelper.isNotEmpty(toSaveData)){
            for(UserFunctionAuthConfDto temp:toSaveData){
                this.userFunctionAuthConfRepository.saveEntity(temp.toEntity(UserFunctionAuthConf.class));
            }
        }else{
            throw new BusinessException("PX001","保存用户功能权限出错，参数为空");
        }
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
        if(ObjectHelper.isNotEmpty(ids)){
            String[] idArray=ids.split(",");
            for(String temp:idArray){
                this.userFunctionAuthConfRepository.delete(Long.valueOf(temp));
            }
        }
    }

}
