package com.lh.dome.framework.annotation;

import com.lh.dome.common.exception.IdempotentException;
import com.lh.dome.common.utils.RedisUtils;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 检查接口幂等注解
 *
 * @  lihong
 * @date 2023/04/29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {


    /*** 幂等Key的前缀，默认为空串*/
    String key() default "";

    /*** 到期时间*/
    long expireSeconds() default 10;

}

@Component
@Aspect
class IdempotentInterceptor {

    /*** 等幂前缀*/
    private static final String IDEMPOTENT_PREFIX = "idempotent:";

    @Resource
    private RedisUtils redisUtils;


    @Around("@annotation(idempotent)")
    public Object around(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Throwable {
        // 构造幂等Key
        String idempotentKey = IDEMPOTENT_PREFIX + idempotent.key() + ":" + getMethodParams(joinPoint);
        // 判断幂等Key是否存在，如果存在，则说明已经处理过，直接返回
        if (Boolean.TRUE.equals(redisUtils.hasKey(idempotentKey))) {
            throw new IdempotentException("请勿重复操作");
        }
        // 如果幂等Key不存在，则在Redis中存储该幂等Key，并设置过期时间
        redisUtils.setCacheObject(idempotentKey, (int) idempotent.expireSeconds(), TimeUnit.SECONDS);
        // 执行目标方法
        return joinPoint.proceed();
    }

    // 获取方法参数的字符串形式，用于构造幂等Key
    private String getMethodParams(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            sb.append(arg.toString());
        }
        return sb.toString();
    }
}
