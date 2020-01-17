package com.zby.manage.config;

import com.zby.manage.model.UserIoc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zby
 * @time 2019/5/21 14:26
 */
@Configuration
public class TestBean {
    @Value("${zby.name}")
    private String name;
    @Value("${zby.age}")
    private Integer age;
    @Bean
    public UserIoc beanUser(){
        UserIoc user = new UserIoc();
        user.setAge(age);
        user.setName(name);
        return user;
    }
}
