package com.lh.demo.common.utils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 常见工具类
 *
 * @author lihong
 * @date 2023/08/01
 */
public class CommonUtils {

    /**
     * 默认IP地址
     */
    public final static String ERROR_IP = "127.0.0.1";

    public static String getUserIP(HttpServletRequest request) {
        // 优先取 X-Real-IP
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("0:0:0:0:0:0:0:1".equals(ip)) {
                ip = ERROR_IP;
            }
        }
        if ("unknown".equalsIgnoreCase(ip)) {
            ip = ERROR_IP;
            return ip;
        }
        int index = ip.indexOf(',');
        if (index >= 0) {
            ip = ip.substring(0, index);
        }

        return ip;
    }

}
