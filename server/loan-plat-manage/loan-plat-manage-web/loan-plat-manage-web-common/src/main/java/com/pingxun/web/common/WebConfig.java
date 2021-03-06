package com.pingxun.web.common;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by dujy on 2017-05-26.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Value("${cw.port}")
    private int port;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        registry.addInterceptor(new CwInterceptor()).addPathPatterns("/**").excludePathPatterns("/css/**", "/js/**", "/images/**", "/services/**",
                "/templates/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        super.addResourceHandlers(registry);
    }

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

//    @Bean
//    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
//        return new EmbeddedServletContainerCustomizer() {
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.setSessionTimeout(60, TimeUnit.MINUTES);
//            }
//        };
//    }

}
