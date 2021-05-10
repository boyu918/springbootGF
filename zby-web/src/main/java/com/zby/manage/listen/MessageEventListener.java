package com.zby.manage.listen;

import lombok.extern.log4j.Log4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Log4j
public class MessageEventListener implements ApplicationListener<MessageEvent> {
    @Override
    public void onApplicationEvent(MessageEvent messageEvent) {
        log.info("get message :" +messageEvent.getMessage());
    }
}
