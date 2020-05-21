package com.sxh.runners;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author sxh
 * @date 2020/5/20
 */
@Component // 与CommandLineRunner一样的解释
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=== This is a message from MyApplicationRunner！ === ");
    }
}
