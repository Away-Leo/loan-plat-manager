package com.pingxun.biz.menu.domain.service;


import com.pingxun.biz.CPContext;
import com.pingxun.biz.menu.app.dto.SysMenuConfigDto;
import com.pingxun.biz.menu.app.dto.UserMenuAuthConfDto;
import com.pingxun.biz.menu.app.service.SysMenuConfigAppService;
import com.pingxun.biz.menu.domain.entity.UserMenuAuthConf;
import com.pingxun.biz.menu.domain.repositry.UserMenuAuthConfRepository;
import com.pingxun.core.common.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserMenuAuthConfDomainService {

    @Autowired
    private UserMenuAuthConfRepository userMenuAuthConfRepository;

    /**
     * @Author: Away
     * @Description: 保存（先删除）
     * @Param: datas
     * @Return int
     * @Date 2018/2/8 14:38
     * @Copyright 重庆平讯数据
     */
    public int saveWithDelete(List<UserMenuAuthConfDto> datas) throws Exception{
        if(ObjectHelper.isNotEmpty(datas)&&datas.size()>0){
            List<UserMenuAuthConf> saveData=new ArrayList<>();
            for(UserMenuAuthConfDto temp:datas){
                UserMenuAuthConf menuAuthConf=temp.toEntity(UserMenuAuthConf.class);
                menuAuthConf.setUserId(CPContext.getContext().getSeUserInfo().getId());
                menuAuthConf.setRawCreator(CPContext.getContext().getSeUserInfo().getUsername());
                saveData.add(menuAuthConf);
            }
            this.userMenuAuthConfRepository.deleteByUserId(CPContext.getContext().getSeUserInfo().getId());
            return this.userMenuAuthConfRepository.batchSave(saveData).size();
        }else{
            throw new BusinessException("PX001","保存用户菜单权限出错，参数为空");
        }
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
        List<UserMenuAuthConf> sourceData=this.userMenuAuthConfRepository.findByUserId(CPContext.getContext().getSeUserInfo().getId());
        if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
            return SysMenuConfigDomainService.createTreeMenus(userMenuAuthConfToSysMenuConfig(sourceData));
        }else{
            return null;
        }
    }

    /**
     * @Author: Away
     * @Description: 按照用户查找菜单，并组装数据
     * @Param:
     * @Return java.util.List<com.pingxun.biz.menu.app.dto.UserMenuAuthConfDto>
     * @Date 2018/2/8 16:20
     * @Copyright 重庆平讯数据
     */
    public List<SysMenuConfigDto> findAndPackByUserWithId(long id) throws Exception{
        if(ObjectHelper.isNotEmpty(id)){
            List<UserMenuAuthConf> sourceData=this.userMenuAuthConfRepository.findByUserId(id);
            if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
                return SysMenuConfigDomainService.createTreeMenus(userMenuAuthConfToSysMenuConfig(sourceData));
            }else{
                return null;
            }

        }else{
            throw new BusinessException("PX001","按照用户查找菜单授权配置出错，参数为空");
        }
    }

    private List<SysMenuConfigDto> userMenuAuthConfToSysMenuConfig(List<UserMenuAuthConf> sourceData){
        List<SysMenuConfigDto> dtos=new ArrayList<>();
        for(UserMenuAuthConf temp:sourceData){
            SysMenuConfigDto dto= new SysMenuConfigDto();
            BeanUtils.copyProperties(temp,dto);
            dto.setText(dto.getMenuName());
            dto.setTags(dto.getId()+"");
            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * @Author: Away
     * @Description: 回调组装菜单树形结构
     * @Param: sourceData
     * @Return java.util.List<com.pingxun.biz.menu.app.dto.UserMenuAuthConfDto>
     * @Date 2018/2/8 16:21
     * @Copyright 重庆平讯数据
     */
    private List<UserMenuAuthConfDto> packMenuTree(List<UserMenuAuthConfDto> sourceData){
        if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
            for(UserMenuAuthConfDto temp:sourceData){
                for(UserMenuAuthConfDto temp2:sourceData){
                    if(temp2.getMenuPid()==temp.getId()){
                        temp.getChildren().add(temp2);
                        sourceData.remove(temp2);
                    }
                }
            }
            packMenuTree(sourceData);
        }
        return sourceData;
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
        if(ObjectHelper.isNotEmpty(toSaveDatas)){
            List<UserMenuAuthConf> saveData=new ArrayList<>();
            for(UserMenuAuthConfDto temp:toSaveDatas){
                if(ObjectHelper.isNotEmpty(temp)){
                    UserMenuAuthConfDto userMenuAuthConfDto=this.findByUserIdAndMenuId(temp.getUserId(),temp.getMenuId());
                    if(ObjectHelper.isEmpty(userMenuAuthConfDto)||ObjectHelper.isEmpty(userMenuAuthConfDto.getId())){
                        saveData.add(temp.toEntity(UserMenuAuthConf.class));
                    }
                }
            }
            return this.userMenuAuthConfRepository.batchSave(saveData).size();
        }else{
            throw new BusinessException("PX001","保存用户菜单权限出错，数据为空");
        }
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
        if(ObjectHelper.isNotEmpty(ids)){
            String[] idArray=ids.split(",");
            for(String temp:idArray){
                UserMenuAuthConf lineData=this.userMenuAuthConfRepository.getOne(Long.valueOf(temp));
                if(ObjectHelper.isNotEmpty(lineData)){
                    List<UserMenuAuthConfDto> childData=this.findByUserIdAndParentMenuId(userId,lineData.getMenuId());
                    if(ObjectHelper.isNotEmpty(childData)&&childData.size()>0){
                        for(UserMenuAuthConfDto tempData:childData){
                            this.userMenuAuthConfRepository.deleteById(tempData.getId());
                        }
                    }
                    this.userMenuAuthConfRepository.delete(lineData);
                }
            }
        }else {
            throw new BusinessException("PX001","根据ID删除用户菜单权限出错，参数为空");
        }
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
        if(ObjectHelper.isNotEmpty(userId)&&ObjectHelper.isNotEmpty(menuId)){
             UserMenuAuthConf sourceData= this.userMenuAuthConfRepository.findByUserIdAndMenuId(userId, menuId);
            if(ObjectHelper.isNotEmpty(sourceData)){
                return sourceData.to(UserMenuAuthConfDto.class);
            }else{
                return null;
            }
        }else{
            throw new BusinessException("PX001","按照用户和菜单ID查找数据出错，参数为空");
        }
    }

    /**
     * @Author: Away
     * @Description: 根据用户和父菜单ID查找
     * @Param: userId
     * @Param: parentMenuId
     * @Return java.util.List<com.pingxun.biz.menu.app.dto.UserMenuAuthConfDto>
     * @Date 2018/3/5 10:55
     * @Copyright 重庆平讯数据
     */
    public List<UserMenuAuthConfDto> findByUserIdAndParentMenuId(Long userId,Long parentMenuId) throws Exception{
        if(ObjectHelper.isNotEmpty(userId)&&ObjectHelper.isNotEmpty(parentMenuId)){
            List<UserMenuAuthConf> sourceData=this.userMenuAuthConfRepository.findByUserIdAndMenuPid(userId, parentMenuId);
            List<UserMenuAuthConfDto> returnData=null;
            if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
                returnData=new ArrayList<>();
                for(UserMenuAuthConf temp:sourceData){
                    returnData.add(temp.to(UserMenuAuthConfDto.class));
                }
            }
            return returnData;
        }else{
            throw new BusinessException("PX001","按照用户和父菜单查找用户权限子菜单出错，参数为空");
        }
    }

}
