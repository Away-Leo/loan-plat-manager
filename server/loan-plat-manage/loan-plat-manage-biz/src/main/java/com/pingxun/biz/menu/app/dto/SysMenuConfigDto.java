package com.pingxun.biz.menu.app.dto;

import com.pingxun.biz.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.*;


/**
 * @author Away
 * @version V1.0
 * @Title: SysMenuConfig.java
 * @Description: 系统菜单配置
 * @date 2018/2/8 10:29
 * @copyright 重庆平讯数据
 */
@Getter
@Setter
public class SysMenuConfigDto extends BaseDto {

    private Long id;

    /**用户ID**/
    private Long userId;

    /**菜单ID**/
    private Long menuId;
    /**
     * 上级菜单ID
     **/
    private Long menuPid;

    /**上级菜单名称**/
    private String menuPidName;

    /**
     * 菜单名称
     **/
    private String menuName;

    /**
     * 菜单链接地址
     **/
    private String menuUrl;

    /**
     * 菜单顺序
     **/
    private Integer ordby;

    /**
     * 菜单状态
     **/
    private Map state;

    private String text;

    private String tags;

    List<SysMenuConfigDto> nodes = new ArrayList<>();

    /**
     * @Author: Away
     * @Description 子节点排序
     * @Param:
     * @Return void
     * @Date 2018/2/27 19:19
     * @Copyright 重庆平讯数据
     */
    public void sortChildren() {
        Collections.sort(nodes, new Comparator<SysMenuConfigDto>() {
            @Override
            public int compare(SysMenuConfigDto menu1, SysMenuConfigDto menu2) {
                int result = 0;

                Integer ordby1 = menu1.getOrdby();
                Integer ordby2 = menu2.getOrdby();

                Long id1 = menu1.getMenuId();
                Long id2 = menu2.getMenuId();
                if (null != ordby1 && null != ordby2) {
                    result = (ordby1 < ordby2 ? -1 : (ordby1 == ordby2 ? 0 : 1));
                } else {
                    result = (id1 < id2 ? -1 : (id1 == id2 ? 0 : 1));
                }
                return result;
            }

        });
        // 对每个节点的下一层节点进行排序
        for (Iterator<SysMenuConfigDto> it = nodes.iterator(); it.hasNext(); ) {
            it.next().sortChildren();
        }
    }

}
