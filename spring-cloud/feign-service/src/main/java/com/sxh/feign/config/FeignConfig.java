package com.sxh.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sxh
 * @date 2020/4/3
 */
@Configuration
public class FeignConfig {

    // 增加配置，使Feign打印详细的日志信息
    @Bean
    Logger.Level level() {
        return Logger.Level.FULL;
    }
}
