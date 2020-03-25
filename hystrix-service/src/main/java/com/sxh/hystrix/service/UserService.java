package com.sxh.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sxh.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

/**
 * @author sxh
 * @date 2020/3/25
 */
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.eureka-client}")
    private String api;
    
    @HystrixCommand(fallbackMethod = "fallbackMethod1")
    public ResponseMessage getUser(Long id) {
        return restTemplate.getForObject(api + "user/{1}", ResponseMessage.class, id);
    }

    /**
     * 声明的参数需要包含controller声明的参数
     * @param id
     * @return
     */
    public ResponseMessage fallbackMethod1(@PathVariable Long id) {
        return new ResponseMessage("调用失败！");
    }
    
    
    
}
