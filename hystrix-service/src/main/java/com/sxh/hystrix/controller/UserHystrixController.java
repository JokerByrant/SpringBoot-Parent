package com.sxh.hystrix.controller;

import com.sxh.hystrix.service.UserService;
import com.sxh.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sxh
 * @date 2020/3/21
 */
@RestController
@RequestMapping("/user")
public class UserHystrixController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/testFallback/{id}")
    public ResponseMessage testFallback(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
