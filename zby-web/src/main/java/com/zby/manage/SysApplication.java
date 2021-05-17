package com.zby.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zby
 * @time 2019/5/21 9:44
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.zby.manage.dao.mapper")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.zby")
public class SysApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class,args);
    }
}
