package com.pingxun.biz.menu.domain.repositry;


import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.pingxun.biz.CPContext;
import com.pingxun.biz.menu.app.dto.UserFunctionAuthConfDto;
import com.pingxun.biz.menu.domain.entity.UserFunctionAuthConf;
import com.pingxun.core.common.base.BaseRepository;
import com.pingxun.core.common.util.ObjectHelper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Title: SysUrlsRepository.java
* @Description: 用户功能权限配置操作库
* @author Away
* @date 2018/2/7 18:25
* @copyright 重庆平讯数据
* @version V1.0
*/
public interface UserFunctionAuthConfRepository extends BaseRepository<UserFunctionAuthConf,Long> {

    /**
     * @Author: Away
     * @Description: 按照用户查找
     * @Param: userId
     * @Return java.util.List<com.pingxun.biz.menu.domain.entity.UserFunctionAuthConf>
     * @Date 2018/2/8 11:53
     * @Copyright 重庆平讯数据
     */
    public List<UserFunctionAuthConf> findByUserId(Long userId);

    /**
     * @Author: Away
     * @Description: 按照用户和功能查找
     * @Param: userId
     * @Param: url
     * @Return com.pingxun.biz.menu.domain.entity.UserFunctionAuthConf
     * @Date 2018/2/8 11:55
     * @Copyright 重庆平讯数据
     */
    public UserFunctionAuthConf findByUserIdAndUrl(Long userId,String url);

    /**
     * @Author: Away
     * @Description: 按照用户删除
     * @Param: id
     * @Return void
     * @Date 2018/2/8 12:05
     * @Copyright 重庆平讯数据
     */
    public void deleteByUserId(Long id);

    /**
     * @Author: Away
     * @Description: 按照条件查找分页数据
     * @Param: pageable
     * @Param: params
     * @Return org.springframework.data.domain.Page<com.pingxun.biz.menu.domain.entity.UserFunctionAuthConf>
     * @Date 2018/3/5 14:27
     * @Copyright 重庆平讯数据
     */
    public default Page<UserFunctionAuthConf> findByConditions(Pageable pageable, UserFunctionAuthConfDto params){
        StringBuffer hql=new StringBuffer(" from UserFunctionAuthConf a where 1=1 ");
        Map<String,Object> conditions=new HashMap<>();
        if(ObjectHelper.isNotEmpty(params)){
            //用户ID
            hql.append(" and a.userId = :userId ");
            if(ObjectHelper.isNotEmpty(params.getUserId())){
                conditions.put("userId",params.getUserId());
            }else{
                conditions.put("userId", CPContext.getContext().getSeUserInfo().getId());
            }
            //功能地址
            if(ObjectHelper.isNotEmpty(params.getUrl())){
                hql.append(" and a.url like :url ");
                conditions.put("userId","%"+ StringEscapeUtils.escapeSql(params.getUrl())+"%");
            }
        }
        hql.append(" order by a.rawAddTime desc ");
        return this.findByHqlPage(pageable,hql.toString(),conditions);
    }
}
