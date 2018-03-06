package com.pingxun.biz.menu.domain.entity;

import com.pingxun.biz.common.entity.AggEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* @Title: UserMenuAuthConf.java
* @Description: 用户功能权限配置
* @author Away
* @date 2018/2/8 10:42
* @copyright 重庆平讯数据
* @version V1.0
*/
@Entity
@Table(name="sys_user_function_conf")
@org.hibernate.annotations.Table(appliesTo = "sys_user_function_conf",comment = "用户功能权限配置")
@Getter
@Setter
public class UserFunctionAuthConf extends AggEntity{

    @Column(name="user_id",columnDefinition="int(11) comment '用户ID'")
    private Long userId;

    @Column(name="url",columnDefinition="varchar(100)  comment '功能地址'")
    private String url;

    @Column(name="function_name",columnDefinition="varchar(100)  comment '功能名称'")
    private String functionName;

    public UserFunctionAuthConf(Long userId, String url) {
        this.userId = userId;
        this.url = url;
    }

    public UserFunctionAuthConf() {
    }
}
