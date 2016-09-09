package com.server.java.cache.redis;


import java.util.Random;

import com.server.java.cache.CacheManager;
import com.server.java.cache.ICache;

/**
 * Created by yrou on 3/21/2016.
 */
public class RedisLock {
    /** 加锁标志 */
    public static final String LOCKED = "TRUE";
    /** 毫秒与毫微秒的换算单位 1毫秒 = 1000000毫微秒 */
    public static final long MILLI_NANO_CONVERSION = 1000 * 1000L;
    /** 默认超时时间（毫秒） */
    public static final long DEFAULT_TIME_OUT = 1000;

    /** 锁的超时时间（秒），过期删除 */
    public static final int EXPIRE = 3 * 60;

    private String key;
    // 锁状态标志
//    private boolean locked = false;

    /**
     * This creates a RedisLock
     * @param key key
     */
    public RedisLock(String key) {
        this.key = key + "_lock";
    }

    /**
     * 加锁
     * 应该以：
     * lock();
     * try {
     *      doSomething();
     * } finally {
     *      unlock()；
     * }
     * 的方式调用
     * @param timeout 超时时间
     * @return 成功或失败标志
     */
    public boolean lock(long timeout) {
        return lock(timeout, EXPIRE);
    }

    /**
     * 加锁
     * 应该以：
     * lock();
     * try {
     *      doSomething();
     * } finally {
     *      unlock()；
     * }
     * 的方式调用
     * @param timeout 超时时间
     * @param expire 锁的超时时间（秒），过期删除
     * @return 成功或失败标志
     */
    public boolean lock(long timeout, int expire) {
        long nano = System.nanoTime();
        timeout *= MILLI_NANO_CONVERSION;
        ICache cache = null;
        try {
            cache = CacheManager.getCache();
            while ((System.nanoTime() - nano) < timeout) {
                if (cache.setnx(this.key, LOCKED) == 1) {
                    cache.expire(this.key, expire);
//                    this.locked = true;
//                    return this.locked;
                    return true;
                }
                // 短暂休眠，避免出现活锁
                Thread.sleep(3, new Random().nextInt(500));
            }
        } catch (Exception e) {
            throw new RuntimeException("Locking error", e);
        } finally {
            if (cache != null) {
                CacheManager.releaseCache(cache);
            }
        }
        return false;
    }

    /**
     * 加锁
     * 应该以：
     * lock();
     * try {
     *      doSomething();
     * } finally {
     *      unlock()；
     * }
     * 的方式调用
     * @return 成功或失败标志
     */
    public boolean lock() {
        return lock(DEFAULT_TIME_OUT);
    }

    /**
     * 解锁
     * 无论是否加锁成功，都需要调用unlock
     * 应该以：
     * lock();
     * try {
     *      doSomething();
     * } finally {
     *      unlock()；
     * }
     * 的方式调用
     */
    public void unlock() {
        ICache cache = null;
        try {
            cache = CacheManager.getCache();
//            if (this.locked) {
                cache.del(this.key);
//            }
        } finally {
            if (cache != null) {
                CacheManager.releaseCache(cache);
            }
        }
    }
}
