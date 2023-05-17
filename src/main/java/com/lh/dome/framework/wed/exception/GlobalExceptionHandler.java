package com.lh.dome.framework.wed.exception;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.lh.dome.common.constant.ErrorCodeConstants;
import com.lh.dome.common.constant.HttpStatus;
import com.lh.dome.common.domain.RespResult;
import com.lh.dome.common.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.nio.file.AccessDeniedException;

/**
 * 全局异常处理器
 *
 * @author lihong
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 权限校验异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public RespResult handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
        return RespResult.error("没有权限，请联系管理员授权", HttpStatus.FORBIDDEN);
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public RespResult handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                          HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return RespResult.error("不支持该类型的请求", HttpStatus.BAD_METHOD);
    }

    /**
     * 文件存储异常
     *
     * @param e       e
     * @param request 请求
     * @return {@code RespResult}
     */
    @ExceptionHandler(FileStorageException.class)
    public RespResult FileStorageException(ServiceException e, HttpServletRequest request){
        log.error(e.getMessage(), e);
        String message = e.getMessage();
        if (StringUtils.isBlank(message)) {
            message = "文件存储异常";
        }
        return RespResult.error(message, e.getErrorCode(), HttpStatus.BAD_REQUEST);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public RespResult handleServiceException(ServiceException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        String message = e.getMessage();
        if (StringUtils.isBlank(message)) {
            message = "操作失败";
        }
        return RespResult.error(message, e.getErrorCode(), HttpStatus.BAD_REQUEST);
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(SystemException.class)
    public RespResult handleSystemException(SystemException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return RespResult.error("系统异常，请联系管理员", e.getErrorCode(), HttpStatus.ERROR);
    }

    /**
     * 认证异常
     */
    @ExceptionHandler(AuthException.class)
    public RespResult handleAuthException(AuthException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生认证异常.", requestURI, e);
        return RespResult.error("登录状态已过期，请重新登录", e.getErrorCode(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * 没有角色权限
     *
     * @param e       e
     * @param request 请求
     * @return {@link RespResult}
     */
    @ExceptionHandler(NotRoleException.class)
    public RespResult handleNotRoleException(NotRoleException e, HttpServletRequest request){
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生权限异常.", requestURI, e);
        return RespResult.error("没有操作权限", HttpStatus.UNAUTHORIZED);
    }

    /**
     * 权限不足
     *
     * @param e       e
     * @param request 请求
     * @return {@link RespResult}
     */
    @ExceptionHandler(NotPermissionException.class)
    public RespResult handleNotPermissionException(NotPermissionException e, HttpServletRequest request){
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生权限异常.", requestURI, e);
        return RespResult.error("权限不足", HttpStatus.UNAUTHORIZED);
    }

    /**
     * 处理未登录异常
     *
     * @param e       e
     * @param request 请求
     * @return {@link RespResult}
     */
    @ExceptionHandler(NotLoginException.class)
    public RespResult handleNotLoginException(NotLoginException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生认证异常.", requestURI, e);
        return RespResult.error("登录状态已过期，请重新登录", ErrorCodeConstants.AUTH_INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public RespResult handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return RespResult.error("系统异常，请联系管理员");
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public RespResult handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return RespResult.error("系统异常，请联系管理员");
    }

    /**
     * 幂等异常
     */
    @ExceptionHandler(IdempotentException.class)
    public RespResult idempotentException(IdempotentException e,HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return RespResult.error("不要重复提交请求");
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return RespResult.error(message, ErrorCodeConstants.PARAMETER_VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Object handleConstraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        String message = e.getConstraintViolations().stream().findFirst().get().getMessage();
        return RespResult.error(message, ErrorCodeConstants.PARAMETER_VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
    }

    /**
     * 上传文件过大异常
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public RespResult handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生上传文件过大异常.", requestURI, e);
        return RespResult.error("上传文件大小超过最大限制", ErrorCodeConstants.MAX_UPLOAD_FILE_ERROR, HttpStatus.BAD_REQUEST);
    }

    /**
     * 请求不支持异常
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public RespResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生请求不支持异常.", requestURI, e);
        return RespResult.error("请求类型不支持，请检查Content-Type是否设置正确", ErrorCodeConstants.CONTENT_TYPE_ERROR, HttpStatus.BAD_REQUEST);
    }
}
