package com.sxh.feign.controller;

import com.sxh.feign.client.UserClient;
import com.sxh.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sxh
 * @date 2020/4/3
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserClient userClient;

    @GetMapping("/{id}")
    public ResponseMessage getUser(@PathVariable Integer id) {
        return userClient.getUser(id);
    }
}
