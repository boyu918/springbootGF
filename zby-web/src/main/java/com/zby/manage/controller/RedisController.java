package com.zby.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-03-30
 * Time: 3:33 PM
 */
@RestController
@RequestMapping("/redis")
public class RedisController {


    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/add")
    public String add(String key,String value){
        redisTemplate.opsForValue().set(key,value);
        return (String) redisTemplate.opsForValue().get(key);
    }
    @PostMapping("/delete")
    public String delete(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
        return "1";
    }
}
