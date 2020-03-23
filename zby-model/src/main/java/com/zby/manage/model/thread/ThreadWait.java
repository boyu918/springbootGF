package com.zby.manage.model.thread;

/**
 * Description:
 * User: 小卡掌柜
 * Date: 2020-03-18
 * Time: 2:12 PM
 */
//建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC
// 这个问题用Object的wait()，notify()就可以很方便的解决。代码如下：


public class ThreadWait {

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        WaitThread waitThreadA = new WaitThread("A",c,a);
        WaitThread waitThreadB = new WaitThread("B",a,b);
        WaitThread waitThreadC = new WaitThread("C",b,c);


        new Thread(waitThreadA).start();
        Thread.sleep(100);
        new Thread(waitThreadB).start();
        Thread.sleep(100);
        new Thread(waitThreadC).start();
        Thread.sleep(100);
    }
}
