package com.lh.dome.common.exception;


import com.lh.dome.common.constant.ErrorCodeConstants;

/**
 * 幂等异常
 *
 * @author lihong
 * @date 2023/04/29
 */
public class IdempotentException extends RuntimeException{
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

    public IdempotentException(){

    }

    public IdempotentException(String message)
    {
        this.message = message;
        this.errorCode = ErrorCodeConstants.AUTH_ERROR;
    }

    public IdempotentException(String message, String errorCode)
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
    public IdempotentException setMessage(String message)
    {
        this.message = message;
        return this;
    }

    public IdempotentException setDetailMessage(String detailMessage)
    {
        this.detailMessage = detailMessage;
        return this;
    }

}
