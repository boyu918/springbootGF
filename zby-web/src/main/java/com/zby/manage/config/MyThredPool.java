package com.zby.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-03-23
 * Time: 10:11 AM
 */
@Configuration
public class MyThredPool {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //线程池核心线程数
        threadPoolTaskExecutor.setCorePoolSize(50);
        //线程池最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(200);
        //费核心线程保留时间
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        //拒绝策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        return threadPoolTaskExecutor;
    }
}
