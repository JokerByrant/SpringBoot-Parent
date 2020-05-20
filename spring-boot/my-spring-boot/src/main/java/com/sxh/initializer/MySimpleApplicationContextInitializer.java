package com.sxh.initializer;

import com.sxh.bean.TestBean;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 自定义SpringBoot初始化器
 * @author sxh
 * @date 2020/5/20
 */
public class MySimpleApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        applicationContext.getBeanFactory().registerSingleton("testBean", new TestBean("1001", "张三"));
    }
}
