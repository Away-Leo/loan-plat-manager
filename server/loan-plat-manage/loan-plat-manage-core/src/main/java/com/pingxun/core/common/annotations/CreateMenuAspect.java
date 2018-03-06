package com.pingxun.core.common.annotations;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
* @Title: CreateMenuAspect.java
* @Description:  创建菜单注解解析器
* @author Away
* @date 2018/2/7 17:23
* @copyright 重庆平讯数据
* @version V1.0
*/
@Aspect
@Component
public class CreateMenuAspect {

    @Pointcut("@annotation(com.pingxun.core.common.annotations.CreateMenu)") // 注解声明切点
    public void annotationPointcut() {
    };

    @Before("annotationPointcut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CreateMenu annotation = method.getAnnotation(CreateMenu.class);
        System.out.println("注解式拦截 : " + annotation.url()+";name:"+annotation.name()+";des"+annotation.description());
    }

}
