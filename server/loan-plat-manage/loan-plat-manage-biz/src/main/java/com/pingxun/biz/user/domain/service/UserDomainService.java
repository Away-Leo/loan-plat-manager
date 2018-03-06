package com.pingxun.biz.user.domain.service;

import com.pingxun.biz.user.app.dto.UserDto;
import com.pingxun.biz.user.domain.entity.User;
import com.pingxun.biz.user.domain.repository.UserRepository;
import com.pingxun.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDomainService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @Author: Away
     * @Description: 按照用户、密码、类型登录
     * @Param: userDto
     * @Return com.pingxun.biz.user.app.dto.UserDto
     * @Date 2018/2/7 14:29
     * @Copyright 重庆平讯数据
     */
//    public UserDto findByUsernameAndAndPasswordAndType(UserDto userDto) throws Exception{
//        if(ObjectHelper.isNotEmpty(userDto)&&ObjectHelper.isNotEmpty(userDto.getUsername())&&ObjectHelper.isNotEmpty(userDto.getPassword())){
//
//            User sourceData=this.userRepository.findByUsernameAndPasswordAndTypeNot(userDto.getUsername(),userDto.getPassword(),"user");
//        }else{
//            throw new BusinessException("PX001","按照用户、密码、类型登录查找用户出错，参数为空");
//        }
//    }

    /**
     * @Author: Away
     * @Description: 按照条件查找
     * @Param: pageable
     * @Param: conditions
     * @Return org.springframework.data.domain.Page<com.pingxun.biz.user.app.dto.UserDto>
     * @Date 2018/3/1 14:50
     * @Copyright 重庆平讯数据
     */
    public Page<UserDto> findManageUserPageByCondition(Pageable pageable,UserDto conditions){
        Page<User> sourceData=this.userRepository.findManageUserPageByCondition(pageable, conditions);
        List<UserDto> returnListData=new ArrayList<>();
        if(ObjectHelper.isNotEmpty(sourceData.getContent())){
            for(User temp:sourceData){
                returnListData.add(temp.to(UserDto.class));
            }
        }
        return new PageImpl<UserDto>(returnListData,pageable,sourceData.getTotalElements());
    }

}
