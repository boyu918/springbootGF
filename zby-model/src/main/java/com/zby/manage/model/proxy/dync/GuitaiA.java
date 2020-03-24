package com.zby.manage.model.proxy.dync;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-03-24
 * Time: 10:45 AM
 */
public class GuitaiA implements InvocationHandler{

    private Object pingtai;
    public GuitaiA(Object pingtai){
        this.pingtai = pingtai;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("销售开始 销售平台是"+this.getClass().getSimpleName());
        method.invoke(pingtai,args);
        System.out.println("销售结束");
        return null;
    }
}
