package com.sxh.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author sxh
 * @date 2020/5/20
 */
@Component
@Order(1) // 通过@Order可以调整调用顺序
public class MyCommandLinesRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("==== This is a Message from MyCommandLinesRunner2！====");
    }
}
