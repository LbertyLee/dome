package com.lh.demo.common.exception;


import com.lh.demo.common.constant.ErrorCodeConstants;

/**
 * 身份验证异常
 *
 * @  lihong
 * @date 2023/04/21
 */
public final class AuthException extends RuntimeException
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
    public AuthException()
    {
    }

    public AuthException(String message)
    {
        this.message = message;
        this.errorCode = ErrorCodeConstants.AUTH_ERROR;
    }

    public AuthException(String message, String errorCode)
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
    public AuthException setMessage(String message)
    {
        this.message = message;
        return this;
    }

    public AuthException setDetailMessage(String detailMessage)
    {
        this.detailMessage = detailMessage;
        return this;
    }
}