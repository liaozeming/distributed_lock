package com.haoqian.redission_lock.Utils;

import com.haoqian.redission_lock.distributedLock.DistributedLocker;

import java.util.concurrent.TimeUnit;


/**
 * redisson 分布式锁帮助类
 */
public class RedissonLockUtil {
    private static DistributedLocker redissonLocker;

    public static void setLocker(DistributedLocker locker) {
        redissonLocker = locker;
    }

    /**
     * 获取锁
     *
     * @param lockKey
     */
    public static void lock(String lockKey) {
        redissonLocker.lock(lockKey);
    }


    /**
     * 获取锁，超时释放
     *
     * @param lockKey
     * @param leaseTime 持有锁的时间(单位：秒)
     */
    public static void lock(String lockKey, int leaseTime) {
        redissonLocker.lock(lockKey, leaseTime);
    }

    /**
     * 获取锁，超时释放，指定时间单位
     *
     * @param lockKey
     * @param leaseTime 持有锁的时间
     * @param unit      leaseTime的单位
     */
    public static void lock(String lockKey, int leaseTime, TimeUnit unit) {
        redissonLocker.lock(lockKey, leaseTime, unit);
    }

    /**
     * 尝试获取锁，获取到立即返回true,获取失败立即返回false
     *
     * @param lockKey
     * @return
     */
    public static boolean tryLock(String lockKey) {
        return redissonLocker.tryLock(lockKey);
    }

    /**
     * 尝试获取锁，在给定的waitTime时间内尝试，获取到返回true,获取失败返回false,获取到后再给定的leaseTime时间超时释放
     *
     * @param lockKey
     * @param waitTime
     * @param leaseTime
     * @param unit
     * @return
     * @throws InterruptedException
     */
    public static boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException {
        return redissonLocker.tryLock(lockKey, waitTime, leaseTime, unit);
    }

    /**
     * 锁是否被任意一个线程锁持有
     *
     * @param lockKey
     * @return
     */
    public static boolean isLocked(String lockKey) {
        return redissonLocker.isLocked(lockKey);
    }

    /**
     * 解锁
     *
     * @param lockKey
     */
    public static void unlock(String lockKey) {
        redissonLocker.unlock(lockKey);
    }
}