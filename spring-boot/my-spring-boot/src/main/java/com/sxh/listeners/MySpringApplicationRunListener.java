package com.sxh.listeners;

import com.sxh.event.MySimpleEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 自定义监听器
 * @author sxh
 * @date 2020/5/20
 */
public class MySpringApplicationRunListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartingEvent) {
            System.out.println("====== Application is starting！This is a Message from Custom Listener！ =====");
        } else if (event instanceof ApplicationStartedEvent) {
            System.out.println("====== Application started success！This is a Message from Custom Listener！ =====");
        } else if (event instanceof MySimpleEvent) { // 自定义事件
            System.out.println("====== This is a Message from MySimpleEvent!! ====");
            
            MyFunctionTest myFunctionTest = this::test;
            myFunctionTest.test();
        }
    }
    
    @FunctionalInterface // 该注解用于提醒编译器去检查该接口是否只包含一个抽象方法
    private interface MyFunctionTest{
        void test();
    }
    
    private void test() {
        System.out.println("Hello World!");
    }
}
