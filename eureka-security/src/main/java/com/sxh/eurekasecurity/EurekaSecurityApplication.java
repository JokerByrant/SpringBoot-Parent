package com.sxh.eurekasecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author sxh
 * @date 2020/3/13
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaSecurityApplication.class, args);
    }
}
