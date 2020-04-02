package com.zby.manage.listen;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-03-27
 * Time: 2:23 PM
 */
@WebListener
public class Sessionlisten implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("监听到sesion创建");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("监听到session销毁");

    }
}
