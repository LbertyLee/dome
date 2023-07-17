package com.lh.dome.common.exception;


import com.lh.dome.common.constant.ErrorCodeConstants;

/**
 * 系统异常
 *
 * @  lihong
 * @date 2023/04/21
 */
public final class SystemException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 错误明细，内部调试错误
     *
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public SystemException()
    {
    }

    public SystemException(String message)
    {
        this.message = message;
        this.errorCode = ErrorCodeConstants.BUSINESS_ERROR;
    }

    public SystemException(String message, String errorCode)
    {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getDetailMessage()
    {
        return detailMessage;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
    public SystemException setMessage(String message)
    {
        this.message = message;
        return this;
    }

    public SystemException setDetailMessage(String detailMessage)
    {
        this.detailMessage = detailMessage;
        return this;
    }
}