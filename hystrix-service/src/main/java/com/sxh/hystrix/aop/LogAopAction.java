package com.sxh.hystrix.aop;

import com.sxh.annotation.LogAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 * @author sxh
 * @date 2020/3/26
 */
@Aspect
@Component
public class LogAopAction {
    private Logger logger = LoggerFactory.getLogger(LogAopAction.class);
    
    @Pointcut("execution(public * com.sxh.hystrix.controller..*(..))")
    private void pointCutMethod(){}
    
    @After("pointCutMethod()") // 使用上面定义的切点
    public void recordLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LogAnnotation logAnnotation = signature.getMethod().getAnnotation(LogAnnotation.class);
        
        if (logAnnotation != null) {
            logger.info("请求方法：" + logAnnotation.actionname());
            logger.info("所属模块：" + logAnnotation.module());
            logger.info("请求类型：" + logAnnotation.annotationType());
        }
    }
}
