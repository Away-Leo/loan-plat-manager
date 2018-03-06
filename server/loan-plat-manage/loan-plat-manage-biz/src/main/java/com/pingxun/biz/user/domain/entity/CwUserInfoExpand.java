package com.pingxun.biz.user.domain.entity;

import com.pingxun.biz.common.entity.AggEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* @Title: CwUserInfoExpand.java
* @Description:  用户拓展信息表
* @author Away
* @date 2018/2/2 10:09
* @copyright 重庆平讯数据
* @version V1.0
*/
@Entity
@Table(name = "loan_user_info_expand")
@org.hibernate.annotations.Table(appliesTo = "loan_user_info_expand",comment = "用户拓展信息")
@Getter
@Setter
public class CwUserInfoExpand extends AggEntity{

    @Column(name="user_id",columnDefinition="int(11)  comment '用户ID'")
    private Long userId;

    @Column(name="chanel_no",columnDefinition="varchar(50)  comment '渠道号'")
    private String chanelNo;

    @Column(name="share_url",columnDefinition="varchar(200)  comment '分享链接'")
    private String shareUrl;

}
