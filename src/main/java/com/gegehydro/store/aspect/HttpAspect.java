package com.gegehydro.store.aspect;

import com.gegehydro.store.util.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hydro
 * create on 2017/10/17
 */
@Aspect
@Component
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     * 定义日志切面规则
     */
    @Pointcut("execution(public * com.gegehydro.store.controller..*.*(..))")
    public void log() {
    }

    /**
     * 输出访问日志
     *
     * @param joinPoint 操作信息
     */
    @Before("log()")
    public void doBeforeLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url={}, method={}, ip={}, class_method={}, args={}", request.getRequestURI(), request.getMethod(), IpUtil.getIpAddress(request), joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    /**
     * 输出返回结果日志
     *
     * @param object 操作信息
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterLog(Object object) {
        logger.info("response={}", object);
    }

}
