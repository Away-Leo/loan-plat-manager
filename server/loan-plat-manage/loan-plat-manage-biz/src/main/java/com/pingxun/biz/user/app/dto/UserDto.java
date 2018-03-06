package com.pingxun.biz.user.app.dto;

import com.pingxun.biz.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* @Title: User.java
* @Description: 用户
* @author Away
* @date 2018/2/7 14:04
* @copyright 重庆平讯数据
* @version V1.0
*/
@Getter
@Setter
public class UserDto extends BaseDto {

    private long id;

    /**用户帐号**/
    private String username;

    /**用户显示名称**/
    private String displayName;

    /**密码**/
    private String password;

    /**加密盐**/
    private String salt;

    /**角色ID**/
    private String roleIds;

    /**是否锁定**/
    private Boolean locked=false;

    /**用户类型**/
    private String type;

    /**微信ID**/
    private String wechatId;

    /**/
    private long rid;

    /**/
    private String phone;

    /**/
    private Date registerDate;

}