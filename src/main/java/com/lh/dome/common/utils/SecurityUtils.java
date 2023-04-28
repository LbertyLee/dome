package com.lh.dome.common.utils;


import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.lh.dome.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;


/**
 * 安全服务工具类
 * 
 * @author underdog
 */
@Slf4j
public class SecurityUtils
{

    /**
     * 用户ID
     **/
    public static Long getUserId()
    {
        try
        {
            return StpUtil.getLoginIdAsLong();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new SystemException("获取用户ID异常");
        }
    }

    /**
     * 编码bcrypt
     *
     * @param text 文本
     * @return {@link String}
     */
    public static String encodeBcrypt(String text) {
        return BCrypt.hashpw(text, BCrypt.gensalt(10));
    }

    /**
     * 匹配bcrypt散列
     *
     * @param plaintext 明文
     * @param hash      哈希
     * @return {@link Boolean}
     */
    public static Boolean matchBcryptHash(String plaintext, String hash) {
        return BCrypt.checkpw(plaintext, hash);
    }

}
