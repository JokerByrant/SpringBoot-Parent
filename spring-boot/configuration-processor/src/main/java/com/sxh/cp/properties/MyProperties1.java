package com.sxh.cp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sxh
 * @date 2020/4/28
 */
@Component
@ConfigurationProperties(prefix = "my1")
@Data
public class MyProperties1 {
    private int age;
    
    private String name;
}
