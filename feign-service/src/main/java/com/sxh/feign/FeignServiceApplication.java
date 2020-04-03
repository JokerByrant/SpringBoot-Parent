package com.sxh.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Feign整合了Ribbon和Hystrix，拥有负载均衡和服务容错功能
 * @author sxh
 * @date 2020/4/3
 */
@EnableFeignClients // 启动Feign客户端功能
@EnableDiscoveryClient
@SpringBootApplication
public class FeignServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignServiceApplication.class, args);
    }
}
