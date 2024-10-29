package com.birdy.lock.factory;

import com.birdy.lock.enums.LockType;
import com.birdy.lock.factory.service.DatabaseLockService;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 分布式锁工厂类
 *
 * @Author birdy
 * @Date 2024/10/29 20:20
 */
public class DatabaseLockFactory {

    private static Map<LockType, DatabaseLockService> databaseLockServiceMap = new ConcurrentHashMap<LockType, DatabaseLockService>();

    /**
     * 注册
     *
     * @param type
     * @param databaseLockService
     */
    public static void register(LockType type, DatabaseLockService databaseLockService) {
        Assert.notNull(type, "type can't be null");
        databaseLockServiceMap.put(type, databaseLockService);
    }


    /**
     * get
     *
     * @param type
     * @return
     */
    public static DatabaseLockService get(LockType type) {
        return databaseLockServiceMap.get(type);
    }
}
