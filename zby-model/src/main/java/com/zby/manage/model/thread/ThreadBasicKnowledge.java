package com.zby.manage.model.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description:Thread
 * User: 小卡掌柜
 * Date: 2020-03-16
 * Time: 3:50 PM
 */
public class ThreadBasicKnowledge {

    public static class ExThread extends Thread{
        public ExThread(String name){
            super(name);
        }

        public ExThread() {
            super();
        }

        //        @SneakyThrows
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("exthread run");
        }
    }


    public static class RunableThread implements Runnable{
        @Override
        public void run() {
            System.out.println("RunableThread run");
        }
    }
    public static class CallBackCCC<String> implements Callable<String>{
        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public String call() throws Exception {
            Thread.sleep(7000);
            return (String) "e333";
        }
    }
    public static ThreadPoolTaskExecutor executor;
    //调用wait 和notify 需要调用对象是异步的对象
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Thread.sleep(100); //当前线程进入到准备态，不放弃对象锁，一般是给其他线程时间运行。
//        Thread.interrupted();//报异常 比如死锁，当然要处理异常，否则还是不会停止线程
//        Thread.yield(); //当前线程让出cpu
//        Thread thread = new Thread();//创建线程  创建状态
//        thread.start();    //可运行状态，等待分配cpu
//        thread.run();    //线程体   本身无线程，直接调用等于运行run函数
//        thread.setPriority(Thread.MAX_PRIORITY);//设置线程等级 1~10 越高级，分配到cpu的几率越高
//        thread2.join();  //线程等待子线程的终止。也就是在子线程调用了join()方法后面的代码，只有等到子线程结束了才能执行
//        Object a;
//        a.wait();//Obj.wait()，与Obj.notify()必须要与synchronized(Obj)一起使用
//        a.notify();//从功能上来说wait就是说线程在获取对象锁后，主动释放对象锁，同时本线程休眠


        //三种实现thread 方式
        ExThread exThread = new ExThread();
        exThread.start();
        System.out.println(111111);
//        exThread.wait();//Obj.wait()必须要与synchronized(Obj)一起使用
//        exThread.join();
        ExThread exThread1 = new ExThread();
        exThread1.start();
        System.out.println(222222);




        RunableThread runableThread = new RunableThread();
        Thread runThread  = new Thread(runableThread);
        runThread.start();


        CallBackCCC callBackCCC = new CallBackCCC();
        FutureTask f = new FutureTask(callBackCCC);

        Thread thread = new Thread(f);
        thread.start();

//        Thread.sleep(1000);
        int  i =10;
        while (i > 0){
            if (f.isDone()){
                System.out.println(f.get());
                i=0;
            }else {
                System.out.println(i);
            }
            Thread.sleep(1000);
            i--;
        }
    }

}
