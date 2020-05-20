package com.sxh;

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
        TestBean testBean = context.getBean("testBean", TestBean.class);
        System.out.println("id = " + testBean.getId() + "，name = " + testBean.getName());
    }
}
