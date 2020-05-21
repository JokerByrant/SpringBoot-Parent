package com.sxh.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * afterPropertiesSet()方法会在当前Bean的所有属性被初始化之后调用
 * @author sxh
 * @date 2020/5/21
 */
@Component
public class TestInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("This is a Message from TestInitializingBean!");
    }

    /*
    @PostConstruct标注的方法先于实现了InitializingBean的Bean中的afterPropertiesSet()执行
    具体分析见：https://www.cnblogs.com/java-chen-hao/p/11835120.html
    */
    @PostConstruct
    public void test() {
        System.out.println("This is a Message from PostConsturt111!");
    }
}
