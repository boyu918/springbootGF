package com.zby.manage.model.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UseCyclinBarier {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->{
        System.out.println(Thread.currentThread()+"cyclin barier log");
    });
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            try {
                System.out.println("task 1 start");
                System.out.println("task 1 step 1");
                cyclicBarrier.await();
                System.out.println("task 1 step 2");
                cyclicBarrier.await();
                System.out.println("task 1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                Thread.sleep(5000l);
                System.out.println("task 2 start");
                System.out.println("task 2 step 1");
                cyclicBarrier.await();
                System.out.println("task 2 step 2");
                cyclicBarrier.await();
                System.out.println("task 2 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        System.out.println("main wait");
        executorService.shutdown();
    }
}
