package com.birdy.lock.annotations;

import com.birdy.lock.enums.LockType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于标识分布式锁的key
 *
 * @Author 115998
 * @Date 2024/10/28 16:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedScheduled {

    /**
     * 锁的key
     *
     * @return
     */
    String lockKey();

    /**
     * 锁的超时时间，默认1分钟
     *
     * @return
     */
    long timeout() default 60 * 1000;

    /**
     * 锁的类型，默认使用MYSQL
     *
     * @return
     */
    LockType lockType() default LockType.MYSQL;
}
