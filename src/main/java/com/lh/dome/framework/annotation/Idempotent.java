package com.lh.dome.framework.annotation;

import java.lang.annotation.*;

/**
 * 检查接口幂等注解
 *
 * @author lihong
 * @date 2023/04/29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {
    // 幂等Key的前缀，默认为空串
    String value() default "";
    // 幂等Key的过期时间，默认为5分钟，单位为秒
    long expireSeconds() default 10;
}
