package com.sxh.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sxh
 * @date 2020/4/9
 */
@RefreshScope
@RestController
public class ConfigController {
//    @Value("${test-config}")
    private String testConfig;

    @GetMapping("/testConfig")
    public String getConfigInfo() {
        return testConfig;
    }
}
