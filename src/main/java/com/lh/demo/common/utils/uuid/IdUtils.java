package com.lh.demo.common.utils.uuid;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.lang.Snowflake;

import java.util.Random;

/**
 * ID生成器工具类
 * 
 * @  lihong
 */
public class IdUtils
{
    /**
     * 获取随机UUID
     * 
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 随机UUID
     */
    public static String fastUUID()
    {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID()
    {
        return UUID.fastUUID().toString(true);
    }


    /**
     *  雪花算法-生成id
     */
    public static Long randomSnowflake() {
        // 创建 Snowflake ID 生成器
        long workerId = 1;
        long datacenterId = 1;
        Snowflake snowflake = Singleton.get(Snowflake.class, workerId, datacenterId);
        // 生成雪花 ID
        return snowflake.nextId();
    }

    /**
     *  雪花算法-生成id
     */
    public static String randomSnowflakeString() {
        long workerId = 1;
        long datacenterId = 1;
        Snowflake snowflake = Singleton.get(Snowflake.class, workerId, datacenterId);
        return snowflake.nextIdStr();
    }

    /**
     *  随机生成length位数数字
     * @param length
     * @return
     */
    public static String generateRandomNumber(int length) {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
}
