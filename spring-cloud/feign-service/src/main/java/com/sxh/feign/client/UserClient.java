package com.sxh.feign.client;

import com.sxh.message.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author sxh
 * @date 2020/4/3
 */
@FeignClient(value = "${service-url.eureka-client}", fallback = UserClientFallBackService.class)
public interface UserClient {

    @GetMapping("/user/{id}")
    ResponseMessage getUser(@PathVariable Integer id);
}
