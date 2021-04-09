package com.zby.manage.model.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class UseCountDownLatch {
    public static CountDownLatch useCountDownLatch = new CountDownLatch(2);
    public static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        List<FutureTask<String>> tasks = new ArrayList<>();
        System.out.println("main start");
        tasks.add((FutureTask<String>) executorService.submit(()->{
            System.out.println("this is first thread");
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            useCountDownLatch.countDown();
            System.out.println("this is first thread end");
            return "first thread";
        }));
        tasks.add((FutureTask<String>) executorService.submit(()->{
            System.out.println("this is second thread");
            try {
                Thread.sleep(5000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            useCountDownLatch.countDown();
            System.out.println("this is second thread end");
            return "second thread";
        }));
        useCountDownLatch.await();
        tasks.forEach(task->{
            try {
                System.out.println(task.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("main end");

    }
}
