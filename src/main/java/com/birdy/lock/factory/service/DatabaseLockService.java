package com.birdy.lock.factory.service;

import com.birdy.lock.enums.LockType;

/**
 * @Author 115998
 * @Date 2024/10/29 20:21
 */
public interface DatabaseLockService {


    /**
     * 获取锁状态
     *
     * @param lockKey
     * @param timeout
     * @return
     * @see LockType
     */
    boolean acquireLock(String lockKey, Long timeout);

    /**
     * 锁释放
     *
     * @param lockKey
     */
    void releaseLock(String lockKey, LockType lockType);
}
