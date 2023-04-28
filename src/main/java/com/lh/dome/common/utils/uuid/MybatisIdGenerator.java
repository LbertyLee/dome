package com.lh.dome.common.utils.uuid;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

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
