package com.sxh.hystrix.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动注入测试
 * @author sxh
 * @date 2020/3/26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableContentService {
    public interface ContentService {
        void doSomething();
    }
    
    public class SimpleContentService implements ContentService{
        @Override
        public void doSomething() {
            System.out.println("do something");
        }
    }
}
