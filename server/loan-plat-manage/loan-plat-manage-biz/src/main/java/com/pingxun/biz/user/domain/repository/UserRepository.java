package com.pingxun.biz.user.domain.repository;

import com.pingxun.biz.user.app.dto.CwUserInfoDto;
import com.pingxun.biz.user.app.dto.UserDto;
import com.pingxun.biz.user.domain.entity.User;
import com.pingxun.core.common.base.BaseRepository;
import com.pingxun.core.common.util.ObjectHelper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Away
 * @version V1.0
 * @Title: SeUserRepository.java
 * @Description: 用户操作库
 * @date 2018/2/7 13:52
 * @copyright 重庆平讯数据
 */
public interface UserRepository extends BaseRepository<User, Long> {

    /**
     * @Author: Away
     * @Description: 按照用户名、密码、用户类型排除查找
     * @Param: userName
     * @Param: passWord
     * @Param: type
     * @Return com.pingxun.biz.user.domain.entity.User
     * @Date 2018/2/7 14:26
     * @Copyright 重庆平讯数据
     */
    public User findByUsernameAndPasswordAndTypeNot(String userName,String passWord,String type);

    /**
     * @Author: Away
     * @Description: 按照条件查找用户
     * @Param: pageable
     * @Param: condition
     * @Return org.springframework.data.domain.Page<com.pingxun.biz.user.domain.entity.User>
     * @Date 2018/3/1 14:42
     * @Copyright 重庆平讯数据
     */
    public default Page<User> findManageUserPageByCondition(Pageable pageable, UserDto condition){
        StringBuffer hql=new StringBuffer(" from User u where u.type != :defaultType ");
        Map<String,Object> qry=new HashMap<>();
        qry.put("defaultType","user");
        if(ObjectHelper.isNotEmpty(condition)){
            if(ObjectHelper.isNotEmpty(condition.getUsername())){
                hql.append(" and u.username like :username ");
                qry.put("username","%"+ StringEscapeUtils.escapeSql(condition.getUsername())+"%");
            }
            if(ObjectHelper.isNotEmpty(condition.getType())){
                hql.append(" and u.type = :type ");
                qry.put("type",condition.getType());
            }
        }
        return this.findByHqlPage(pageable,hql.toString(),qry);
    }
}
