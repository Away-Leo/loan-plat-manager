package com.pingxun.biz.menu.app.dto;

import com.pingxun.biz.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
* @Title: UserMenuAuthConf.java
* @Description: 用户菜单权限配置
* @author Away
* @date 2018/2/8 10:42
* @copyright 重庆平讯数据
* @version V1.0
*/
@Getter
@Setter
public class UserMenuAuthConfDto extends BaseDto{

    private Long id;

    /**用户ID**/
    private Long userId;

    /**菜单ID**/
    private Long menuId;

    private Long menuPid;

    /**菜单名称**/
    private String menuName;

    /**菜单链接地址**/
    private String menuUrl;

    List<UserMenuAuthConfDto> children=new ArrayList<>();
}
