package com.pingxun.biz.menu.domain.service;


import com.pingxun.biz.CPContext;
import com.pingxun.biz.menu.app.dto.SysMenuConfigDto;
import com.pingxun.biz.menu.domain.entity.SysMenuConfig;
import com.pingxun.biz.menu.domain.repositry.SysMenuConfigRepository;
import com.pingxun.core.common.util.ObjectHelper;
import com.pingxun.core.common.util.ObjectProperUtil;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Away
 * @version V1.0
 * @Title: SysUrlsDomainService.java
 * @Description: 系统菜单配置服务
 * @date 2018/2/7 18:26
 * @copyright 重庆平讯数据
 */
@Service
public class SysMenuConfigDomainService {

    @Autowired
    private SysMenuConfigRepository sysMenuConfigRepository;


    /**
     * @Author: Away
     * @Description: 按照ID查找
     * @Param: id
     * @Return com.pingxun.biz.menu.app.dto.SysMenuConfigDto
     * @Date 2018/2/8 11:35
     * @Copyright 重庆平讯数据
     */
    public SysMenuConfigDto findById(Long id) throws Exception {
        if (ObjectHelper.isNotEmpty(id)) {
            SysMenuConfig sourceData = this.sysMenuConfigRepository.findOne(id);
            if (ObjectHelper.isNotEmpty(sourceData)) {
                return sourceData.to(SysMenuConfigDto.class);
            } else {
                throw new BusinessException("PX002", "按照id查找系统菜单配置出错，无此数据");
            }
        } else {
            throw new BusinessException("PX001", "按照ID查找系统菜单配置出错，参数为空");
        }
    }

    /**
     * @Author: Away
     * @Description: 更新或保存系统菜单配置
     * @Param: sourceData
     * @Return com.pingxun.biz.menu.app.dto.SysMenuConfigDto
     * @Date 2018/2/8 11:30
     * @Copyright 重庆平讯数据
     */
    public SysMenuConfigDto saveOrUpdate(SysMenuConfigDto sourceData) throws Exception {
        if (ObjectHelper.isNotEmpty(sourceData)) {
            //更新
            if (ObjectHelper.isNotEmpty(sourceData.getId())) {
                SysMenuConfigDto oldData = this.findById(sourceData.getId());
                oldData = ObjectProperUtil.compareAndValue(sourceData, oldData, false, new String[]{"menuUrl"});
                oldData.setRawUpdateTime(new Date());
                oldData.setRawModifier(CPContext.getContext().getSeUserInfo().getUsername());
                return this.sysMenuConfigRepository.updateEntity(oldData.toEntity(SysMenuConfig.class)).to(SysMenuConfigDto.class);
            } else {
                return this.sysMenuConfigRepository.saveEntity(sourceData.toEntity(SysMenuConfig.class)).to(SysMenuConfigDto.class);
            }
        } else {
            throw new BusinessException("PX001", "修改或保存系统菜单配置出错，参数为空");
        }
    }

    public List<SysMenuConfigDto> getAdminTree() throws Exception {
        List<SysMenuConfig> allData = this.sysMenuConfigRepository.findAll();
        if (ObjectHelper.isNotEmpty(allData) && allData.size() > 0) {
            List<SysMenuConfigDto> dtos = new ArrayList<>();
            for (SysMenuConfig temp : allData) {
                SysMenuConfigDto dto = temp.to(SysMenuConfigDto.class);
                dto.setMenuId(temp.getId());
                dto.setText(dto.getMenuName());
                dto.setTags(dto.getId()+"");
                dtos.add(dto);
            }
            List<SysMenuConfigDto> returnData=createTreeMenus(dtos);
            return returnData;
        } else {
            return null;
        }
    }

    /**
     * @Author: Away
     * @Description: 回调组装菜单树形结构
     * @Param: sourceData
     * @Return java.util.List<com.pingxun.biz.menu.app.dto.SysMenuConfigDto>
     * @Date 2018/2/8 16:21
     * @Copyright 重庆平讯数据
     */
    private List<SysMenuConfigDto> packMenuTree(List<SysMenuConfigDto> parentData, List<SysMenuConfigDto> childData) {
        if (ObjectHelper.isNotEmpty(parentData) && parentData.size() > 0) {
            for (SysMenuConfigDto temp : parentData) {
                if (ObjectHelper.isNotEmpty(childData) && childData.size() > 0) {
                    for (SysMenuConfigDto temp2 : childData) {
                        if (temp2.getMenuPid() == temp.getId()) {
                            temp.getNodes().add(temp2);
                            childData.remove(temp2);
                            packMenuTree(temp.getNodes(), childData);
                        }
                    }
                }
            }
        }
        return parentData;
    }

    public static List<SysMenuConfigDto> createTreeMenus(List<SysMenuConfigDto> menus) {
        List<SysMenuConfigDto> treeMenus = null;
        if (null != menus && !menus.isEmpty()) {
            // 创建根节点
            SysMenuConfigDto root = new SysMenuConfigDto();
            root.setMenuName("菜单根目录");

            // 组装Map数据
            Map<Long, SysMenuConfigDto> dataMap = new HashMap<>();
            for (SysMenuConfigDto menu : menus) {
                dataMap.put(menu.getMenuId(), menu);
            }

            // 组装树形结构
            Set<Map.Entry<Long, SysMenuConfigDto>> entrySet = dataMap.entrySet();
            for (Map.Entry<Long, SysMenuConfigDto> entry : entrySet) {
                SysMenuConfigDto menu = entry.getValue();
                if (null == menu.getMenuPid() || 0 == menu.getMenuPid()) {
                    root.getNodes().add(menu);
                } else {
                    dataMap.get(menu.getMenuPid()).getNodes().add(menu);
                }
            }

            // 对树形结构进行二叉树排序
            root.sortChildren();
            treeMenus = root.getNodes();
        }
        return treeMenus;
    }

    /**
     * @Author: Away
     * @Description: 根据条件查找分页数据
     * @Param: pageable
     * @Param: condition
     * @Return org.springframework.data.domain.Page<com.pingxun.biz.menu.app.dto.SysMenuConfigDto>
     * @Date 2018/2/23 14:00
     * @Copyright 重庆平讯数据
     */
    public Page<SysMenuConfigDto> findByConditions(Pageable pageable, SysMenuConfigDto condition) throws Exception {
        Page<SysMenuConfig> sourceData = this.sysMenuConfigRepository.findByConditions(pageable, condition);
        List<SysMenuConfigDto> returnList = new ArrayList<>();
        Page<SysMenuConfigDto> returnData = null;
        if (ObjectHelper.isNotEmpty(sourceData)) {
            for (SysMenuConfig temp : sourceData.getContent()) {
                returnList.add(temp.to(SysMenuConfigDto.class));
            }
            returnData = new PageImpl<SysMenuConfigDto>(returnList, pageable, sourceData.getTotalElements());
        } else {
            returnData = new PageImpl<SysMenuConfigDto>(returnList, pageable, 0);
        }
        return returnData;
    }

    /**
     * @Author: Away
     * @Description: 删除
     * @Param: id
     * @Return void
     * @Date 2018/2/23 14:12
     * @Copyright 重庆平讯数据
     */
    public void deleteSysMenuConf(Long id) throws Exception {
        if (ObjectHelper.isNotEmpty(id)) {
            SysMenuConfig sourceData = this.sysMenuConfigRepository.findOne(id);
            if (ObjectHelper.isNotEmpty(sourceData)) {
                this.sysMenuConfigRepository.delete(sourceData);
            } else {
                throw new BusinessException("PX004", "根据ID删除对象出错，无此数据");
            }
        } else {
            throw new BusinessException("PX001", "根据ID删除对象出错，参数为空");
        }
    }

}
