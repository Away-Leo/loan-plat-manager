package com.pingxun.web.front.controller.menu;

import com.pingxun.biz.CPContext;
import com.pingxun.biz.menu.app.dto.SysMenuConfigDto;
import com.pingxun.biz.menu.app.dto.SysUrlsDto;
import com.pingxun.biz.menu.app.dto.UserFunctionAuthConfDto;
import com.pingxun.biz.menu.app.dto.UserMenuAuthConfDto;
import com.pingxun.biz.menu.app.service.SysMenuConfigAppService;
import com.pingxun.biz.menu.app.service.SysUrlsAppService;
import com.pingxun.biz.menu.app.service.UserFunctionAuthConfAppService;
import com.pingxun.biz.menu.app.service.UserMenuAuthConfAppService;
import com.pingxun.core.common.dtos.DataTablesPage;
import com.pingxun.core.common.page.DataTablesPageRequest;
import com.pingxun.core.common.util.ObjectHelper;
import com.pingxun.web.common.dto.CPViewResultInfo;
import com.pingxun.web.front.controller.AbstractFrontController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**   
* @Title: MenuController.java
* @Description:  菜单控制器
* @author Away
* @date 2018/2/8 16:48 
* @copyright 重庆平讯数据
* @version V1.0   
*/
@RestController
public class MenuController extends AbstractFrontController {

    @Autowired
    private UserMenuAuthConfAppService userMenuAuthConfAppService;

    @Autowired
    private UserFunctionAuthConfAppService userFunctionAuthConfAppService;

    @Autowired
    private SysMenuConfigAppService sysMenuConfigAppService;

    @Autowired
    private SysUrlsAppService sysUrlsAppService;

    private static Logger logger= LoggerFactory.getLogger(MenuController.class);

    @GetMapping(value = "/menu/getMenu.json",name = "菜单-根据用户获得菜单")
    public CPViewResultInfo getMenu(CPViewResultInfo info){
        try{
            List<SysMenuConfigDto> menus=new ArrayList<>();
            if(CPContext.getContext().getSeUserInfo().getType().equalsIgnoreCase("admin")){
                SysMenuConfigDto dto=new SysMenuConfigDto();
                dto.setMenuName("菜单");
                dto.setText("菜单");
                SysMenuConfigDto chile=new SysMenuConfigDto();
                chile.setMenuUrl("SysMenuConf.html");
                chile.setMenuName("菜单权限分配");
                chile.setText("菜单权限分配");
                dto.getNodes().add(chile);
                menus.add(dto);
                List<SysMenuConfigDto> menuConfigDtoList=sysMenuConfigAppService.getAdminTree();
                if(ObjectHelper.isNotEmpty(menuConfigDtoList)&&menuConfigDtoList.size()>0)menus.addAll(menuConfigDtoList);
                info.setData(menus);
            }else{
                info.setData(userMenuAuthConfAppService.findAndPackByUser());
            }
            info.setSuccess(true);
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            logger.error("菜单-根据用户获得菜单",e);
        }
        return info;
    }

    @GetMapping(value = "/menu/getAllMenu.json",name = "菜单-获取所有菜单")
    public CPViewResultInfo getAllMenu(CPViewResultInfo info){
        try{
            info.setData(sysMenuConfigAppService.getAdminTree());
            info.setSuccess(true);
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            logger.error("菜单-获取所有菜单",e);
        }
        return info;
    }

    @GetMapping(value = "/menu/getMenuWithUserId.json",name = "菜单-根据给定用户ID获得菜单配置")
    public CPViewResultInfo getMenu(CPViewResultInfo info,Long id){
        try{
            info.setData(userMenuAuthConfAppService.findAndPackByUserWithId(id));
            info.setSuccess(true);
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            logger.error("菜单-根据给定用户ID获得菜单配置",e);
        }
        return info;
    }


    /**
     * @Author: Away
     * @Description: 编辑系统菜单
     * @Param: info
     * @Param: sysMenuConfigDto
     * @Return com.pingxun.web.common.dto.CPViewResultInfo
     * @Date 2018/2/23 10:52
     * @Copyright 重庆平讯数据
     */
    @PostMapping(value = "/menu/editSysMenu.json",name = "菜单-编辑系统配置菜单")
    public CPViewResultInfo editSysMenu(CPViewResultInfo info,@RequestBody SysMenuConfigDto sysMenuConfigDto){
        try{
            info.setData(sysMenuConfigAppService.saveOrUpdate(sysMenuConfigDto));
            info.setSuccess(true);
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            logger.error("编辑系统菜单出错",e);
        }
        return info;
    }

    /**
     * @Author: Away
     * @Description: 获得已经配置系统菜单列表
     * @Param: info
     * @Return com.pingxun.web.common.dto.CPViewResultInfo
     * @Date 2018/2/23 11:02
     * @Copyright 重庆平讯数据
     */
    @GetMapping(value = "/menu/getSysMenuList.json",name = "菜单-获得系统菜单列表")
    public CPViewResultInfo getSysMenuList(DataTablesPage dataTablesPage, CPViewResultInfo info, SysMenuConfigDto condition){
        try{
            Page<SysMenuConfigDto> sourceData=this.sysMenuConfigAppService.findByConditions(new DataTablesPageRequest(dataTablesPage),condition);
            info.setData(sourceData);
            info.setSuccess(true);
            info.setDraw(dataTablesPage.getDraw());
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            logger.error("获得已经配置系统菜单列表出错",e);
        }
        return info;
    }

    /**
     * @Author: Away
     * @Description: 根据ID删除菜单
     * @Param: info
     * @Param: id
     * @Return com.pingxun.web.common.dto.CPViewResultInfo
     * @Date 2018/2/23 14:23
     * @Copyright 重庆平讯数据
     */
    @GetMapping(value = "/menu/deleteSysMenu.json",name = "菜单-根据ID删除菜单")
    public CPViewResultInfo deleteSysMenu(CPViewResultInfo info,Long id){
        try{
            this.sysMenuConfigAppService.deleteMenu(id);
            info.setSuccess(true);
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            logger.error("根据ID删除菜单出错",e);
        }
        return info;
    }

    /**
     * @Author: Away
     * @Description: 新增用户菜单权限
     * @Param: info
     * @Param: sourceDatas
     * @Return com.pingxun.web.common.dto.CPViewResultInfo
     * @Date 2018/3/2 17:50
     * @Copyright 重庆平讯数据
     */
    @PostMapping(value = "/menu/saveUserMenuAuth.json",name = "菜单-新增用户菜单权限")
    public CPViewResultInfo saveUserMenuAuth(CPViewResultInfo info, @RequestBody List<UserMenuAuthConfDto> sourceDatas){
        try{
            info.setData(userMenuAuthConfAppService.saveUserMenuAuth(sourceDatas));
            info.setSuccess(true);
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            logger.error("菜单-新增用户菜单权限",e);
        }
        return info;
    }

    /**
     * @Author: Away
     * @Description: 菜单-根据ID删除用户菜单权限
     * @Param: info
     * @Param: ids
     * @Return com.pingxun.web.common.dto.CPViewResultInfo
     * @Date 2018/3/2 17:50
     * @Copyright 重庆平讯数据
     */
    @GetMapping(value = "/menu/deleteUserMenuAuthByIds.json",name = "菜单-根据ID删除用户菜单权限")
    public CPViewResultInfo deleteUserMenuAuthByIds(CPViewResultInfo info,Long userId,String ids){
        try{
            this.userMenuAuthConfAppService.deleteMenuAuth(userId,ids);
            info.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            logger.error("菜单-根据ID删除用户菜单权限",e);
        }
        return info;
    }

    /**
     * @Author: Away
     * @Description: 根据用户获得功能列表
     * @Param: dataTablesPage
     * @Param: info
     * @Param: condition
     * @Return com.pingxun.web.common.dto.CPViewResultInfo
     * @Date 2018/3/5 14:39
     * @Copyright 重庆平讯数据
     */
    @GetMapping(value = "/menu/getUserFunAuthPage.json",name = "菜单-根据用户获得已授权列表")
    public CPViewResultInfo getUserFunAuthPage(DataTablesPage dataTablesPage, CPViewResultInfo info, UserFunctionAuthConfDto condition){
        try{
            Page<UserFunctionAuthConfDto> sourceData=this.userFunctionAuthConfAppService.findByConditions(new DataTablesPageRequest(dataTablesPage),condition);
            info.setData(sourceData);
            info.setSuccess(true);
            info.setDraw(dataTablesPage.getDraw());
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            logger.error("根据用户获得已授权列表",e);
        }
        return info;
    }

    /**
     * @Author: Away
     * @Description: 获得所有功能地址
     * @Param: dataTablesPage
     * @Param: info
     * @Param: condition
     * @Return com.pingxun.web.common.dto.CPViewResultInfo
     * @Date 2018/3/5 14:39
     * @Copyright 重庆平讯数据
     */
    @GetMapping(value = "/menu/getAllFunctionUrl.json",name = "菜单-获得所有功能地址")
    public CPViewResultInfo getAllFunctionUrl(DataTablesPage dataTablesPage, CPViewResultInfo info, SysUrlsDto condition){
        try{
            Page<SysUrlsDto> sourceData=this.sysUrlsAppService.findByConditions(new DataTablesPageRequest(dataTablesPage),condition);
            info.setData(sourceData);
            info.setSuccess(true);
            info.setDraw(dataTablesPage.getDraw());
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            logger.error("根据用户获得已授权列表",e);
        }
        return info;
    }

    /**
     * @Author: Away
     * @Description: 保存用户功能权限
     * @Param: dataTablesPage
     * @Param: info
     * @Param: condition
     * @Return com.pingxun.web.common.dto.CPViewResultInfo
     * @Date 2018/3/5 14:39
     * @Copyright 重庆平讯数据
     */
    @PostMapping(value = "/menu/saveUserFunAuth.json",name = "菜单-保存用户功能权限")
    public CPViewResultInfo saveUserFunAuth(CPViewResultInfo info, @RequestBody List<UserFunctionAuthConfDto> toSaveDatas){
        try{
            this.userFunctionAuthConfAppService.saveFunctionAuth(toSaveDatas);
            info.setSuccess(true);
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            logger.error("保存用户功能权限",e);
        }
        return info;
    }

    /**
     * @Author: Away
     * @Description; 通过ids删除用户功能权限设置
     * @Param: info
     * @Param: ids
     * @Return com.pingxun.web.common.dto.CPViewResultInfo
     * @Date 2018/3/5 19:40
     * @Copyright 重庆平讯数据
     */
    @GetMapping(value = "/menu/deleteByIds.json",name = "菜单-通过ids删除用户功能权限")
    public CPViewResultInfo deleteByIds(CPViewResultInfo info,String ids){
        try{
            this.userFunctionAuthConfAppService.deleteByIds(ids);
            info.setSuccess(true);
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            logger.error("通过ids删除用户功能权限",e);
        }
        return info;
    }



}
