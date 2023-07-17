package com.lh.dome.common.utils.uuid;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

/**
 * mybatis身份证生成器
 *
 * @  lihong
 * @date 2023/06/01
 */
public class MybatisIdGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        return IdUtils.randomSnowflake();
    }

    @Override
    public boolean assignId(Object idValue) {
        return IdentifierGenerator.super.assignId(idValue);
    }

    @Override
    public String nextUUID(Object entity) {
        return IdentifierGenerator.super.nextUUID(entity);
    }
}
