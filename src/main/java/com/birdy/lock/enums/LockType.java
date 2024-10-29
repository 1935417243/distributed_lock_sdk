package com.birdy.lock.enums;

/**
 * 锁类型
 *
 * @Author 115998
 * @Date 2024/10/23 17:21
 */
public enum LockType {
    // 默认使用MySQL作为分布式锁
    MYSQL,
    //redis
    REDIS
}
