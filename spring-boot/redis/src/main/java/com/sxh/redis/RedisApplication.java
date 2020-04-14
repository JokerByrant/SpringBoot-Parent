package com.sxh.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @author sxh
 * @date 2020/4/13
 */
@SpringBootApplication
public class RedisApplication {
    @Autowired
    private LettuceConnectionFactory factory;
    
    @Value("${subscribe.url}")
    private String subscribeUrl;
    
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);

//        factory.getConnection().subscribe( ,subscribeUrl.getBytes());
    }
}
