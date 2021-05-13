package com.zby.manage.controller;

import com.zby.manage.service.RedisServise;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-03-30
 * Time: 3:33 PM
 */
@RestController
@RequestMapping("/redis")
@Log4j
public class RedisController {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisServise redisServise;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

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
    private static Integer count = 500;
    @PostMapping("/testlock")
    public String testLock() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        String lockkey = "myKey";
        Runnable runnable = () -> {
            String unLockIdentify = null;
            try {
                unLockIdentify = redisServise.lock(lockkey);
                log.info(Thread.currentThread().getName() + "正在运行");
                log.info("redis----数量"+ --count);
                countDownLatch.countDown();
            } finally {
                redisServise.unlock(lockkey,unLockIdentify);
            }
        };
        for (int i = 0; i < 10; i++) {
            threadPoolTaskExecutor.execute(runnable);
        }
        countDownLatch.await();
        return "all thread is over";
    }
}
