package com.birdy.lock.factory.service.impl;

import com.birdy.lock.enums.LockType;
import com.birdy.lock.factory.service.DatabaseLockService;
import org.springframework.stereotype.Service;

/**
 * mysql 数据库锁实现
 *
 * @Author 115998
 * @Date 2024/10/29 20:23
 */
@Service
public class MysqlDatabaseLockService implements DatabaseLockService {


    @Override
    public boolean acquireLock(String lockKey, Long timeout) {
        return false;
    }

    @Override
    public void releaseLock(String lockKey, LockType lockType) {

    }
}
