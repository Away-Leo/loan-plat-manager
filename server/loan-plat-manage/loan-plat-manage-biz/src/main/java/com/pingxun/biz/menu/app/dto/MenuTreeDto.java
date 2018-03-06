package com.pingxun.biz.menu.app.dto;

import com.pingxun.biz.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
* @Title: SysMenuConfig.java
* @Description:  树形菜单数据载体
* @author Away
* @date 2018/2/8 10:29
* @copyright 重庆平讯数据
* @version V1.0
*/
@Getter
@Setter
public class MenuTreeDto extends BaseDto{

    private Long id;

    /**上级菜单ID**/
    private Long menuPid;

    /**菜单名称**/
    private String menuName;

    /**菜单链接地址**/
    private String menuUrl;

    /**前端网页树形菜单显示**/
    private String text;

    List<MenuTreeDto> nodes=new ArrayList<>();

}
