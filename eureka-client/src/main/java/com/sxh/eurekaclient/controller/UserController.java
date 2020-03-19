package com.sxh.eurekaclient.controller;

import com.sxh.eurekaclient.entity.User;
import com.sxh.eurekaclient.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author sxh
 * @date 2020/3/13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    public Result<User> getUser(@PathVariable Integer id) {
        User user = new User();
        user.setId(id);
        user.setUserName("张三");
        user.setPassword("123456");

        logger.info("获取用户信息成功！");

        return new Result<>(user);
    }
}
