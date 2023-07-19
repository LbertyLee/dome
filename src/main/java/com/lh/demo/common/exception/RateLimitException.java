package com.lh.demo.common.exception;

import com.lh.demo.common.constant.ErrorCodeConstants;

public class RateLimitException extends RuntimeException{

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

    public RateLimitException(){

    }

    public RateLimitException(String message)
    {
        this.message = message;
        this.errorCode = ErrorCodeConstants.AUTH_ERROR;
    }

    public RateLimitException(String message, String errorCode)
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
    public RateLimitException setMessage(String message)
    {
        this.message = message;
        return this;
    }

    public RateLimitException setDetailMessage(String detailMessage)
    {
        this.detailMessage = detailMessage;
        return this;
    }
}
