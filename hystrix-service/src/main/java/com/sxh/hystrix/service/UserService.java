package com.sxh.hystrix.service;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sxh.message.ResponseMessage;
import com.sxh.util.HttpUtil;
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
    
    private static final String epidemic_url = "https://lab.isaaclin.cn/nCoV/api/area";
    
    // Hystrix使用了命令模式，@HystrixCommand注解等于是指定了需要执行的命令
    @HystrixCommand(fallbackMethod = "fallbackMethod1")
    public ResponseMessage getUser(Long id) {
        return restTemplate.getForObject(api + "user/{1}", ResponseMessage.class, id);
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod2")
    public ResponseMessage getEpidemicData() {
        JSONObject object = HttpUtil.doGet(epidemic_url);
        return new ResponseMessage("数据获取成功！", object);
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod1", 
                    commandKey = "testCommand",
                    groupKey = "getUserGroup",
                    threadPoolKey = "getUserThreadPool")
    public ResponseMessage testCommand(Long id) {
        return restTemplate.getForObject(api + "user/{1}", ResponseMessage.class, id);
    }

    /**
     * 声明的参数需要包含controller声明的参数
     * @param id
     * @return
     */
    public ResponseMessage fallbackMethod1(@PathVariable Long id) {
        ResponseMessage message = new ResponseMessage();
        message.setCode(500);
        message.setMessage("调用失败！触发fallbackMethod1");
        message.setData(id);
        return message;
    }

    public ResponseMessage fallbackMethod2() {
        ResponseMessage message = new ResponseMessage();
        message.setCode(500);
        message.setMessage("调用失败！触发fallbackMethod2");
        return message;
    }
    
    
    
}
