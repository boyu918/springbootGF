package com.zby.manage.model.proxy.staticProxy;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-03-24
 * Time: 9:45 AM
 */
public class Cinema implements Moive{
    public Moive moive;
    public Cinema(Moive moive){
        this.moive = moive;
    }

    @Override
    public void play() {
        System.out.println("电影还没开始，放一段广告");
        moive.play();
        System.out.println("电影结束，再放一段广告");
    }
}
