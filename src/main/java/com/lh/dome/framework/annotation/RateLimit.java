package com.lh.dome.framework.annotation;

import com.lh.dome.common.exception.RateLimitException;
import jakarta.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 速率限制
 *
 * @author lihong
 * @date 2023/07/18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RateLimit {

    /** 限流速率，每秒允许的请求数 */
    long value();

    /**
     * 速率限制方面
     *
     * @author lihong
     * @date 2023/07/18
     */

}
@Aspect
@Component
class RateLimitAspect {

    @Resource
    private RedissonClient redissonClient;

    @Before("@annotation(rateLimit)")
    public void rateLimitCheck(JoinPoint joinPoint, RateLimit rateLimit) {
        String methodName = joinPoint.getSignature().getName();
        String key = "rate_limit_" + methodName;

        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        rateLimiter.trySetRate(RateType.OVERALL, rateLimit.value(), 1, RateIntervalUnit.SECONDS);

        if (!rateLimiter.tryAcquire()) {
            throw new RateLimitException("超过了请求限制。请稍后再试");
        }
    }
}
