package com.lh.demo.common.domain;



import com.lh.demo.common.constant.ErrorCodeConstants;
import com.lh.demo.common.constant.HttpStatusConstants;
import com.lh.demo.common.utils.StringUtils;

import java.io.Serial;
import java.util.HashMap;

/**
 * 操作消息提醒
 * 
 * @  underdog
 */
public class RespResult extends HashMap<String, Object>
{
    @Serial
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "statusCode";


    /** 错误代码 */
    public static final String ERROR_CODE = "errorCode";


    /** 返回内容 */
    public static final String MSG_TAG = "message";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public RespResult()
    {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * 
     * @param code 状态码
     * @param msg 返回内容
     */
    public RespResult(int code, String msg)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * 
     * @param code 状态码
     * @param errorCode 错误代码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public RespResult(int code, String errorCode, String msg, Object data)
    {
        super.put(CODE_TAG, code);
        super.put(ERROR_CODE,errorCode);
        if (StringUtils.isNotNull(msg)) {
            super.put(MSG_TAG, msg);
        }
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    public RespResult(int code, String errorCode, Object data)
    {
        super.put(CODE_TAG, code);
        super.put(ERROR_CODE,errorCode);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static RespResult success()
    {
        return RespResult.success(null);
    }

    /**
     * 返回成功消息
     *
     * @param data 数据对象
     * @return 成功消息
     */
    public static RespResult success(Object data)
    {
        return RespResult.success(data, ErrorCodeConstants.OK);
    }

    /**
     * 返回成功消息
     *
     * @param data 数据对象
     * @param errCode 错误编码
     * @return 成功消息
     */
    public static RespResult success(Object data, String errCode)
    {
        if (data == null) {
            data = new HashMap<>();
        }
        return new RespResult(HttpStatusConstants.SUCCESS, errCode, data);
    }

//    /**
//     * 返回警告消息
//     *
//     * @param msg 返回内容
//     * @return 警告消息
//     */
//    public static RespResult warn(String msg)
//    {
//        return RespResult.warn(msg, null);
//    }
//
//    /**
//     * 返回警告消息
//     *
//     * @param msg 返回内容
//     * @param data 数据对象
//     * @return 警告消息
//     */
//    public static RespResult warn(String msg, Object data)
//    {
//        return new RespResult(HttpStatus.WARN,null, msg, data);
//    }

    /**
     * 返回错误消息
     * 
     * @return 错误消息
     */
    public static RespResult error()
    {
        return RespResult.error("操作失败");
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @return 错误消息
     */
    public static RespResult error(String msg)
    {
        return RespResult.error(msg, null);
    }


    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static RespResult error(String msg, Object data)
    {
        return error(msg, ErrorCodeConstants.BUSINESS_ERROR, data);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param code 状态码
     * @return 错误消息
     */
    public static RespResult error(String msg, int code)
    {
        return error(msg, ErrorCodeConstants.BUSINESS_ERROR, null, code);
    }
    public static RespResult error(String msg, String errCode, Object data)
    {
        return error(msg, errCode, data, HttpStatusConstants.ERROR);
    }

    public static RespResult error(String msg, String errCode, int statusCode)
    {
        return error(msg, errCode, null, statusCode);
    }

    public static RespResult error(String msg, String errCode, Object data, int statusCode)
    {
        return new RespResult(statusCode, errCode, msg, data);
    }

    /**
     * 方便链式调用
     *
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public RespResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}
