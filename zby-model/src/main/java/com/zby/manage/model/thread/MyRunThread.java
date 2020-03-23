package com.zby.manage.model.thread;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-03-23
 * Time: 10:32 AM
 */
public class MyRunThread implements Runnable{
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("my runnable thread");
    }
}
