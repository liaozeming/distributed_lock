package com.haoqian.redission_lock.distributedLock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁接口
 */
public interface DistributedLocker {

    /**
     * 获取锁，如果锁不可用，则当前线程处于休眠状态，直到获得锁为止。
     *
     * @param lockKey
     */
    void lock(String lockKey);

    /**
     * 获取锁,如果锁不可用，则当前线程处于休眠状态，直到获得锁为止。
     * 如果获取到锁后，执行结束后调用 unlock(String lockKey) 解锁或达到超时时间后会自动释放锁
     * 默认时间单位是 TimeUnit.SECONDS
     *
     * @param lockKey
     * @param leaseTime
     */
    void lock(String lockKey, int leaseTime);

    /**
     * 获取锁,如果锁不可用，则当前线程处于休眠状态，直到获得锁为止。
     * 如果获取到锁后，执行结束后调用 unlock(String lockKey) 解锁或达到超时时间后会自动释放锁
     *
     * @param lockKey   // 锁唯一名称
     * @param leaseTime // 持有锁的时间
     * @param unit      // 指定 leaseTime 的时间单位
     */
    void lock(String lockKey, int leaseTime, TimeUnit unit);

    /**
     * 尝试获取锁，获取到立即返回true,未获取到立即返回false
     *
     * @param lockKey
     * @return
     */
    boolean tryLock(String lockKey);

    /**
     * 尝试获取锁，在等待时间内获取到锁则返回true,否则返回false,
     * 如果获取到锁，执行完后程序调用 unlock(String lockKey) 释放锁或在给定的超时时间leaseTime后释放锁
     *
     * @param lockKey
     * @param waitTime  // 尝试获取锁的等待时间
     * @param leaseTime // 持有锁的时间
     * @param unit      // 指定 leaseTime 的时间单位
     * @return
     */
    boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException;

    /**
     * 锁是否被任意一个线程锁持有
     *
     * @param lockKey
     * @return
     */
    boolean isLocked(String lockKey);

    /**
     * 释放锁
     *
     * @param lockKey
     */
    void unlock(String lockKey);
}