package com.sxh;

import com.sxh.bean.MySimpleBean;
import com.sxh.bean.TestBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * 实现一个对SpringBoot启动程序的拓展
 * @author sxh
 * @date 2020/5/20
 */
@SpringBootApplication
public class MySpringBootApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MySpringBootApplication.class, args);
        
        // 自定义初始化器中注册的Bean
        TestBean testBean = context.getBean("testBean", TestBean.class);
        System.out.println("id = " + testBean.getId() + "，name = " + testBean.getName());

        // 自定义自动装配器中注册的Bean
        MySimpleBean mySimpleBean = context.getBean(MySimpleBean.class);
        System.out.println(mySimpleBean);
    }
}
