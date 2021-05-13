package com.zby.manage.service.impl;

import com.zby.manage.service.RedisServise;
import lombok.extern.log4j.Log4j;
import org.assertj.core.util.Lists;
import org.mockito.internal.util.collections.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.UUID;

@Service
@Log4j
public class RedisServiceImpl implements RedisServise {
    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX"; //px命令是毫秒 ex是秒
    /**
     * 锁的超时时间 10s
     */
    int expireTime = 10 * 1000;

    /**
     * 锁等待，防止线程饥饿
     */
    int acquireTimeout  = 1 * 1000;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public String lock(String lockKey) {
        return (String) redisTemplate.execute((RedisCallback) redisConnection -> {
            //获取时间毫秒值
            long expireAt = System.currentTimeMillis() + expireTime + 1;
            String uuid = UUID.randomUUID().toString();
            RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
            RedisSerializer keySerializer = redisTemplate.getKeySerializer();
            while (System.currentTimeMillis() < expireAt){
                Object result =  redisConnection.execute("set", keySerializer.serialize(lockKey),
                        valueSerializer.serialize(uuid),
                        SET_IF_NOT_EXIST.getBytes(StandardCharsets.UTF_8),
                        SET_WITH_EXPIRE_TIME.getBytes(StandardCharsets.UTF_8),
                        String.valueOf(expireAt).getBytes(StandardCharsets.UTF_8));
                if (LOCK_SUCCESS.equals(result)) {
                    return uuid;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            return uuid;
        });
    }

    @Override
    public Boolean unlock(String key,String uuid) {
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setResultType(Integer.class);
        redisScript.setScriptText("if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end");
        Object result = null;
        try {
            result = redisTemplate.execute(redisScript, Collections.singletonList(key),uuid);
            if (RELEASE_SUCCESS.equals(result)) {
                log.info("release lock success, requestToken:" + uuid);
                return true;
            }}catch (Exception e){
            log.error("release lock due to error",e);
        }
        return false;
    }
}
