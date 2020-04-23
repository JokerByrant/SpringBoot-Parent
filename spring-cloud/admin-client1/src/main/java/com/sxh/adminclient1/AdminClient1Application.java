package com.sxh.adminclient1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sxh
 * @date 2020/4/23
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AdminClient1Application {
    public static void main(String[] args) {
        SpringApplication.run(AdminClient1Application.class, args);
    }
}
