package com.zby.manage.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

public class ZbyForkJoin extends RecursiveTask<Integer> {
    private int start;
    private int end;

    private static final int THRED_HOLD = 30;


    public ZbyForkJoin(int start, int end) {
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRED_HOLD;
        if (canCompute) { // 不需要拆分
            for (int i = start; i <= end; i++) {
                sum += i;
            }

            System.out.println("thread: " + Thread.currentThread() + " start: " + start + " end: " + end);
        } else {
            int mid = (end + start) / 2;
            ZbyForkJoin left = new ZbyForkJoin(start, mid);
            ZbyForkJoin right = new ZbyForkJoin(mid + 1, end);
            left.fork();
            right.fork();

            sum = left.join() + right.join();
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ZbyForkJoin zbyForkJoin = new ZbyForkJoin(0,200);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ForkJoinTask<Integer> count = forkJoinPool.submit(zbyForkJoin);
        int num = count.get();
        System.out.println(num);
        Stream.of(1,2,3,4).parallel().reduce((a,b)->{return a+b;});
    }
}
