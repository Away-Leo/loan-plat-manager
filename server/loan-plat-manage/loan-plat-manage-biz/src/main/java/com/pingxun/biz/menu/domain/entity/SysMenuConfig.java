package com.pingxun.biz.menu.domain.entity;

import com.pingxun.biz.common.entity.AggEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* @Title: SysMenuConfig.java
* @Description:  系统菜单配置
* @author Away
* @date 2018/2/8 10:29
* @copyright 重庆平讯数据
* @version V1.0
*/
@Entity
@Table(name="sys_menu_conf")
@org.hibernate.annotations.Table(appliesTo = "sys_menu_conf",comment = "整个系统的全部菜单配置")
@Getter
@Setter
public class SysMenuConfig extends AggEntity{

    @Column(name="menu_pid",columnDefinition="int(11) comment '上级菜单ID'")
    private Long menuPid;

    @Column(name="menu_pid_name",columnDefinition="varchar(100) comment '上级菜单名称'")
    private String menuPidName;

    @Column(name="menu_name",columnDefinition="varchar(100)  comment '菜单名称'")
    private String menuName;

    @Column(name="menu_url",columnDefinition="varchar(100)  comment '菜单链接地址'")
    private String menuUrl;

    @Column(name="menu_ordby",columnDefinition="int(11) comment '菜单顺序'")
    private Integer ordby;

    @Column(name="menu_state",columnDefinition="int(1) comment '菜单状态'")
    private Integer state;

}
