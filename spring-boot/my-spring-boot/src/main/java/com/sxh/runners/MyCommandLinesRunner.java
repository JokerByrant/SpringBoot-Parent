package com.sxh.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author sxh
 * @date 2020/5/20
 */
@Component // 需要将其添加到Spring容器中，SpringBoot在启动时会在容器中寻找所有CommandLineRunner的Bean实例
@Order(0) // 通过@Order可以调整调用顺序
public class MyCommandLinesRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("==== This is a Message from MyCommandLinesRunner！====");
    }
}
