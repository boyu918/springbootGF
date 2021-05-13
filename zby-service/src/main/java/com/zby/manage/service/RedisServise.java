package com.zby.manage.service;

public interface RedisServise {
    /**
     * 分布式锁 通过setnx来实现分布式锁
     * @param lockKey redis的key
     * @return 返回自动生成的uuid 返回值为该key的解锁值，保证加锁解锁为同一个线程，除非锁失效
     */
    String lock(String lockKey);

    /**
     * 分布式锁 通过key 和uuid的解锁值来进行解锁操作
     * @param key  操作对应的分布式锁key
     * @param uuid  解锁的校验值uuid，通过加锁所得
     */
    Boolean unlock(String key,String uuid);
}
