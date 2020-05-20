package com.sxh.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author sxh
 * @date 2020/5/20
 */
@Component
public class MyCommandLinesRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("==== This is a Message from MyCommandLinesRunner！====");
    }
}
