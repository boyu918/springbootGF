package com.zby.manage.controller;

import com.zby.manage.listen.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class MessageContoller {
    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping("/msg/event")
    public String msgPublish(){
        applicationContext.publishEvent(new MessageEvent(this,"this is a event message"));
        return "event message";
    }
}
