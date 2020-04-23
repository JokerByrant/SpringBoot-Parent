package com.sxh.adminserver1;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sxh
 * @date 2020/4/23
 */
@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class AdminServer1Application {
    public static void main(String[] args) {
        SpringApplication.run(AdminServer1Application.class, args);
    }
}
