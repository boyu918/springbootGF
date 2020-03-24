package com.zby.manage.model.proxy.staticProxy;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-03-24
 * Time: 9:43 AM
 */
public class RealMoive implements Moive{
    @Override
    public void play() {
        System.out.println("开始播放电影  电影是《肖申克的救赎》");
    }
}
