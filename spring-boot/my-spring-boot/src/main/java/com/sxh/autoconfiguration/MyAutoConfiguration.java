package com.sxh.autoconfiguration;

import com.sxh.bean.MySimpleBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义自动装配类，这个类会被@EnableAutoConfiguration注解加载
 * @author sxh
 * @date 2020/5/26
 */
@Configuration
@ConditionalOnProperty(name = "sxh.autoConfiguration", matchIfMissing = true)
public class MyAutoConfiguration {
    
    // 注册MySimpleBean类
    @Bean
    public MySimpleBean simpleBean() {
        return new MySimpleBean(1002, "李四");
    }
}
