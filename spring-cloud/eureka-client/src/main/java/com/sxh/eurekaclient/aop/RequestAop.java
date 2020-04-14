package com.sxh.eurekaclient.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sxh
 * @date 2020/4/14
 */
@Aspect
@Component
public class RequestAop {
    
    private Logger logger = LoggerFactory.getLogger(RequestAop.class);
    
    @Pointcut("execution(public * com.sxh.eurekaclient.controller..*(..))")
    public void pointcutMethod(){}
    
    @Before("pointcutMethod()")
    public void printHost(JoinPoint joinPoint) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String requestURI = request.getRequestURI();
        
        logger.info("请求主机地址：" + requestURI);
    }
}
