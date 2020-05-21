package com.sxh.publisher;

import com.sxh.event.MySimpleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 自定义一个事件发布者，通过@PostConstruct完成事件发布
 * @author sxh
 * @date 2020/5/21
 */
@Component
public class MySimplePublisher {
    @Autowired
    private ApplicationContext applicationContext;
    
    @PostConstruct
    public void publishEvent() {
        applicationContext.publishEvent(new MySimpleEvent(""));
    }
}
