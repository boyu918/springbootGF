package com.zby.manage.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author zby
 * @time 2019/5/23 16:28
 */
@Configuration
public class AddInterceptor extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userIntercepter())
                .addPathPatterns("/user/**");
    }
    @Bean
    public UserIntercepter userIntercepter(){
        return new UserIntercepter();
    }
}
