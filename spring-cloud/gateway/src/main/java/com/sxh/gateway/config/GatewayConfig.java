package com.sxh.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * gateway配置类
 * @author sxh
 * @date 2020/4/14
 */
@Configuration
public class GatewayConfig {
    @Value("${service-url.user-service}")
    private String userService;
    
    // 通过Java Bean对路由进行配置
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route2", r -> r.path("/testConfig")
                        .uri(userService + "/testConfig"))
                .build();
    }
}
