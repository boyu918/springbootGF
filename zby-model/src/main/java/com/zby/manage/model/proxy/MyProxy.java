package com.zby.manage.model.proxy;

import com.zby.manage.model.proxy.dync.GuitaiA;
import com.zby.manage.model.proxy.dync.MaoTaiJiu;
import com.zby.manage.model.proxy.dync.SellWine;
import com.zby.manage.model.proxy.dync.WuliangYe;
import com.zby.manage.model.proxy.staticProxy.Cinema;
import com.zby.manage.model.proxy.staticProxy.RealMoive;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-03-24
 * Time: 9:42 AM
 */
public class MyProxy {
    public static void main(String[] args) {
        //静态代理
        RealMoive realMoive = new RealMoive();
        Cinema cinema = new Cinema(realMoive);
        cinema.play();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
        //动态代理
        InvocationHandler proxy1 = new GuitaiA(new MaoTaiJiu());
        SellWine sellMaotai =  (SellWine) Proxy.newProxyInstance(MaoTaiJiu.class.getClassLoader(),MaoTaiJiu.class.getInterfaces(),proxy1);
        sellMaotai.sellWine();

        InvocationHandler proxy2 = new GuitaiA(new WuliangYe());
        SellWine sellWu = (SellWine) Proxy.newProxyInstance(WuliangYe.class.getClassLoader(),WuliangYe.class.getInterfaces(),proxy2);
        sellWu.sellWine();

        System.out.println(sellMaotai.getClass().getName());
    }
}
