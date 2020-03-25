package com.sxh.eurekaclient.controller;

import com.sxh.eurekaclient.entity.User;
import com.sxh.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sxh
 * @date 2020/3/13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    public ResponseMessage getUser(@PathVariable Integer id) {
        User user = new User();
        user.setId(id);
        user.setUserName("张三");
        user.setPassword("123456");

        logger.info("获取用户信息成功！");

        return new ResponseMessage(user);
    }
}
