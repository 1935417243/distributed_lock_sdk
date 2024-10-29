package com.birdy.lock.interceptor;

import com.birdy.lock.annotations.DistributedScheduled;
import com.birdy.lock.enums.LockType;
import com.birdy.lock.factory.DatabaseLockFactory;
import com.birdy.lock.factory.service.DatabaseLockService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class ScheduledTaskInterceptor {

    @Pointcut("@annotation(com.birdy.lock.annotations.DistributedScheduled)")
    public void distributedScheduledPointcut() {

    }

    @Around("distributedScheduledPointcut()")
    public Object aroundScheduledTask(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法上的注解信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedScheduled annotation = method.getAnnotation(DistributedScheduled.class);


        // 获取注解中的lockKey和timeout
        String lockKey = annotation.lockKey();
        long timeout = annotation.timeout();
        LockType lockType = annotation.lockType();

        // 获取类名和方法名称
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = method.getName();

        // 拼接锁的唯一key：类名_方法名_注解中的lockKey
        String uniqueLockKey = className + "_" + methodName + "_" + lockKey;
        DatabaseLockService databaseLockService = DatabaseLockFactory.get(lockType);

        boolean acquired = databaseLockService.acquireLock(uniqueLockKey, timeout);

        if (acquired) {
            try {
                log.info("任务{}开始执行...", methodName);
                // 执行定时任务
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                log.error("{}定时任务执行中发生异常: {}", methodName, throwable.getMessage());
                throw throwable;
            } finally {
                log.info("任务{}执行完成，释放锁。", methodName);
                // 任务完成后释放锁
                databaseLockService.releaseLock(uniqueLockKey, lockType);
            }
        } else {
            log.info("任务{}已在另一个Pod上运行。", methodName);
            return null;
        }
    }
}
