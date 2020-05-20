package com.sxh.listeners;

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
        }
    }
}
