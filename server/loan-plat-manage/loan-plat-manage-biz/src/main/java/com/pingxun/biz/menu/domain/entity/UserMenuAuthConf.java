package com.pingxun.biz.menu.domain.entity;

import com.pingxun.biz.common.entity.AggEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* @Title: UserMenuAuthConf.java
* @Description: 用户菜单权限配置
* @author Away
* @date 2018/2/8 10:42
* @copyright 重庆平讯数据
* @version V1.0
*/
@Entity
@Table(name="sys_user_menu_conf")
@org.hibernate.annotations.Table(appliesTo = "sys_user_menu_conf",comment = "用户菜单权限配置")
@Getter
@Setter
public class UserMenuAuthConf extends AggEntity{

    @Column(name="user_id",columnDefinition="int(11) comment '用户ID'")
    private Long userId;

    @Column(name="menu_id",columnDefinition="int(11) comment '菜单ID'")
    private Long menuId;

    @Column(name="menu_pid",columnDefinition="int(11) comment '菜单父ID'")
    private Long menuPid;

    @Column(name="menu_name",columnDefinition="varchar(100)  comment '菜单名称'")
    private String menuName;

    @Column(name="menu_url",columnDefinition="varchar(100)  comment '菜单链接地址'")
    private String menuUrl;




}
