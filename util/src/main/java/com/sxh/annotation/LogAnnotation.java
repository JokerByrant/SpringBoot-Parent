package com.sxh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解
 * @author sxh
 * @date 2020/3/26
 */
@Retention(RetentionPolicy.RUNTIME) //注解会在class中存在，运行时可通过反射获取
@Target(ElementType.METHOD)//目标是方法
public @interface LogAnnotation {
    // 请求名称
    String actionname() default "";
    // 请求模块
    String module() default "";
    // 请求类型
    String actiontype() default "";
}
