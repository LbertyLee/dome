package com.lh.demo.framework.annotation;

import com.lh.demo.common.domain.LogBean;
import com.lh.demo.common.utils.CommonUtils;
import com.lh.demo.common.utils.SecurityUtils;
import com.lh.demo.common.utils.mongodb.MongodbUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Component
@Aspect
public class MongoDbLogAspect {
    @Resource
    private MongodbUtils mongodbUtils;
    //切点的注解  是指那些方法需要被执行"AOP"
    @Pointcut("execution(* com.lh.demo.*.controller.*(..))")
    public void logPoinCut(){}//定义一个切入点
    @AfterReturning(value="logPoinCut()",returning="rtv")
    //JoinPoint是连接点的意思我们要获取到的如类名，方法名，请求参数等都是从连接点中取出来的
    public void afterLog(JoinPoint joinpoint, Object rtv) {
        System.out.println("进去切点。。。。。");
        LogBean logBean = new LogBean();
        logBean.setCreateDate(new Date());
        //获取类名
        String classname = joinpoint.getTarget().getClass().getSimpleName();
        logBean.setClassName(classname);
        //获取方法名
        String method = joinpoint.getSignature().getName();
        logBean.setMethod(method);
        //获取请求参数
        String reqparam = "";
        logBean.setReqParam(reqparam);
        //返回值
        if (rtv != null) {
            logBean.setReqParam(rtv.toString());
        }
        //获取request对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取ip地址是封装好的一个类
        String ip = CommonUtils.getUserIP(request);
        logBean.setIp(ip);
        //保存mongodb
        mongodbUtils.add(logBean);
        System.out.println("日志存储成功.........");
    }
}
