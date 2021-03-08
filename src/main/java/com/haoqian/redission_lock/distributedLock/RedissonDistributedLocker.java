package com.haoqian.redission_lock.distributedLock;

import com.haoqian.redission_lock.distributedLock.DistributedLocker;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

public class RedissonDistributedLocker implements DistributedLocker {

    private RedissonClient redissonClient;

    public RedissonDistributedLocker() {
    }

    public RedissonDistributedLocker(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
    }

    @Override
    public void lock(String lockKey, int leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(leaseTime, TimeUnit.SECONDS);
    }

    @Override
    public void lock(String lockKey, int leaseTime, TimeUnit unit) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(leaseTime, unit);
    }

    @Override
    public boolean tryLock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        return lock.tryLock();
    }

    @Override
    public boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockKey);
        return lock.tryLock(waitTime, leaseTime, unit);
    }

    @Override
    public boolean isLocked(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        return  lock.isLocked();
    }

    @Override
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

}