package com.birdy.lock.factory.service.impl;

import com.birdy.lock.enums.LockType;
import com.birdy.lock.factory.service.DatabaseLockService;

/**
 * redis 分布式锁
 *
 * @Author birdy
 * @Date 2024/10/29 20:27
 */
public class RedisDatabaseLockService implements DatabaseLockService {


    @Override
    public boolean acquireLock(String lockKey, Long timeout) {
        return false;
    }

    @Override
    public void releaseLock(String lockKey, LockType lockType) {

    }
}
