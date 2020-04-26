package com.monitor.datamonitorsystem.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 切面
 * @author zxl
 * @time 2020/4/26 13:49
 */
@Aspect
@Component
public class WebLogAspect {

    /*** 普通log */
    private final Logger NORMAL_LOG = LogManager.getLogger("dealPack");

    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
    @Pointcut("execution(public * com.monitor.datamonitorsystem.controller.*.*(..))")
    public void webLog(){}

    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
    @Pointcut("execution(public * com.monitor.datamonitorsystem.service.impl.*.*(..))")
    public void webLogs(){}

    /**
     * 前置通知：在连接点之前执行的通知
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog() || webLogs()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        NORMAL_LOG.info("URL : " + request.getRequestURL().toString());
        NORMAL_LOG.info("HTTP_METHOD : " + request.getMethod());
        NORMAL_LOG.info("IP : " + request.getRemoteAddr());
        NORMAL_LOG.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        NORMAL_LOG.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        NORMAL_LOG.info("RESPONSE : " + ret);
    }

}

