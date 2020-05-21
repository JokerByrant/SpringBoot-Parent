package com.sxh.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义一个事件
 * @author sxh
 * @date 2020/5/21
 */
public class MySimpleEvent extends ApplicationEvent {
    public MySimpleEvent(Object source) {
        super(source);
    }
}
