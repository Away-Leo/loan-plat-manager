package com.pingxun;

import com.pingxun.biz.menu.app.dto.SysUrlsDto;
import com.pingxun.biz.menu.app.service.SysUrlsAppService;
import com.pingxun.core.common.RequestMappingHandlerConfig;
import com.pingxun.core.common.base.BaseRepositoryFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 启动类
 * Created by dujy on 2017-05-20.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.pingxun.biz"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class//指定自己的工厂类
)
@EnableAspectJAutoProxy
public class Main {

    @Autowired
    private RequestMappingHandlerConfig requestMappingHandlerConfig;

    @Autowired
    private SysUrlsAppService sysUrlsAppService;

    public static final Logger logger= LoggerFactory.getLogger(Main.class);

    public static void main(String[] args){
        System.setProperty("loan.appName","loan-plat");
        SpringApplication.run(Main.class,args);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addExposedHeader("Set-Cookie");
        return corsConfiguration;
    }

    /**
     * 跨域过滤器
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }

    /**
     * 扫描URL，如果数据库中不存在，则保存入数据库
     */
    @PostConstruct  //这个注解很重要，可以在每次启动的时候检查是否有URL更新，RequestMappingHandlerMapping只能在controller层用。这里我们放在主类中
    public void detectHandlerMethods(){
        final RequestMappingHandlerMapping requestMappingHandlerMapping = requestMappingHandlerConfig.requestMappingHandlerMapping ();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        Set<RequestMappingInfo> mappings = map.keySet();
        List<SysUrlsDto> saveData=new ArrayList<>();
        for(RequestMappingInfo info : mappings) {
            String urlName=info.getName();
            String urlparm = info.getPatternsCondition().toString();
            String url = urlparm.substring(1, urlparm.length()-1);
            saveData.add(new SysUrlsDto(url,urlName));
        }
        try {
            int save=this.sysUrlsAppService.batchSaveSysUrlsBeforeDeleteAll(saveData);
            logger.info("已保存URL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+save+"条");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("写入链接数据出错",e);
        }
    }

}
