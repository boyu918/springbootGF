package com.zby.manage.controller;

import com.zby.manage.model.thread.MyRunThread;
import com.zby.manage.model.thread.ThreadBasicKnowledge;
import com.zby.manage.model.thread.ThreadWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zby
 * @time 2019/5/24 10:00
 */
@RestController
@RequestMapping("/thread")
public class ThreadController {
    //注入线程池
    @Autowired
    private ThreadPoolTaskExecutor executor;

    @PostMapping("/threadPool")
    public String threadPoolTest(){
        MyRunThread myRunThread = new MyRunThread();
        //使用线程池去执行线程
        executor.execute(myRunThread);
        return "1";
    }
    @PostMapping("/threadBasicKnowledge")
    public String threadBasicKnowledge(){
        ThreadBasicKnowledge threadBasicKnowledge = new ThreadBasicKnowledge();
        //main 函数基础示例
        return "1";
    }
    @PostMapping("/waitAndNotify")
    public String waitAndNotify(){
        ThreadWait threadWait = new ThreadWait();
        //main 函数
        //建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC
        // 这个问题用Object的wait()，notify()就可以很方便的解决
        return "1";
    }

}
