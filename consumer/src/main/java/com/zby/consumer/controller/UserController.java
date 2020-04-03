package com.zby.consumer.controller;

import com.zby.manage.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-04-02
 * Time: 3:05 PM
 */
@RestController
@ComponentScan(basePackages = {"com.zby.manage"})
public class UserController {


    @Autowired
    private UserApi userApi;
    @Autowired
    private RestTemplate restTemplate;
    @PostMapping("/hello")
    public String testRibbon(){
        return restTemplate.postForObject("http://ZBY-SPRINGBOOT/user/hello",null,String.class);
    }
    @PostMapping("/feign/hello")
    public String testFeign(){
        return userApi.hello();
    }
}
