package com.sxh.cp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author sxh
 * @date 2020/4/28
 */
@Component
@PropertySource("classpath:my2.properties")
@ConfigurationProperties(prefix = "my2")
@Data
public class MyProperties2 {
    private int age;
    
    private String name;
    
    private String email;
}
