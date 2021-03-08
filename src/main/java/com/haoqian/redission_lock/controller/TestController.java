package com.haoqian.redission_lock.controller;

import com.haoqian.redission_lock.Utils.RedissonLockUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    /**
     * lock 会阻塞直到获取到锁
     */
    @GetMapping("/testLock1")
    public void testLock1() {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        RedissonLockUtil.lock("RedissonLock");
                        System.out.println(LocalTime.now() + "---" + Thread.currentThread().getName() + "获取到锁");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        RedissonLockUtil.unlock("RedissonLock");
                        System.out.println(LocalTime.now() + "---" + Thread.currentThread().getName() + "释放锁");
                    }

                }
            }).start();
        }
    }

    /**
     * tryLock
     * 1. 获取到锁后超过leaseTime会自动释放锁
     * 2. 超过waitTime未获取到锁会返回false放弃获取锁
     */
    @GetMapping("/testLock2")
    public void testLock2() {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        boolean result = RedissonLockUtil.tryLock("Lock:lock2", 3, 2, TimeUnit.SECONDS);
                        System.out.println(LocalTime.now() + "---" + Thread.currentThread().getName() + "是否获取到锁" + result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                    }

                }
            }).start();
        }
    }

    /**
     * 获取几次要释放几次，否则会阻塞
     */
    @GetMapping("/testLock3")
    public void testLock3() {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        RedissonLockUtil.lock("RedissonLock");
                        RedissonLockUtil.lock("RedissonLock");
                        System.out.println(LocalTime.now() + "---" + Thread.currentThread().getName() + "获取到锁");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        RedissonLockUtil.unlock("RedissonLock");
                        // RedissonLockUtil.unlock("RedissonLock");
                        System.out.println(LocalTime.now() + "---" + Thread.currentThread().getName() + "释放锁");
                    }

                }
            }).start();
        }
    }

}
