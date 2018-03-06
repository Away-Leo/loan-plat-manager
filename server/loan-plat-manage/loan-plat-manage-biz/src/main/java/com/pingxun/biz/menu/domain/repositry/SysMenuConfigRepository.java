package com.pingxun.biz.menu.domain.repositry;


import com.pingxun.biz.menu.app.dto.SysMenuConfigDto;
import com.pingxun.biz.menu.domain.entity.SysMenuConfig;
import com.pingxun.core.common.base.BaseRepository;
import com.pingxun.core.common.util.ObjectHelper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

/**
* @Title: SysUrlsRepository.java
* @Description: 系统菜单配置操作库
* @author Away
* @date 2018/2/7 18:25
* @copyright 重庆平讯数据
* @version V1.0
*/
public interface SysMenuConfigRepository extends BaseRepository<SysMenuConfig,Long> {

    /**
     * @Author: Away
     * @Description: 按照条件查找
     * @Param: pageable
     * @Param: condition
     * @Return org.springframework.data.domain.Page<com.pingxun.biz.menu.domain.entity.SysMenuConfig>
     * @Date 2018/2/23 11:34
     * @Copyright 重庆平讯数据
     */
    public default Page<SysMenuConfig> findByConditions(Pageable pageable,SysMenuConfigDto condition){
        Map<String,Object> param=new HashMap<>();
        StringBuffer hql=new StringBuffer(" from SysMenuConfig s where 1=1 ");
        //菜单名称
        if(ObjectHelper.isNotEmpty(condition.getMenuName())){
            hql.append(" and s.menuName like :menuName ");
            param.put("menuName","%"+ StringEscapeUtils.escapeSql(condition.getMenuName())+"%");
        }
        //菜单链接
        if(ObjectHelper.isNotEmpty(condition.getMenuUrl())){
            hql.append(" and s.menuUrl like :menuUrl ");
            param.put("menuUrl","%"+StringEscapeUtils.escapeSql(condition.getMenuUrl())+"%");
        }
        hql.append(" order by s.rawAddTime desc ");
        return this.findByHqlPage(pageable,hql.toString(),param);
    }
}
