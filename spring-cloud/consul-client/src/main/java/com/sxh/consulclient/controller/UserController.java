package com.sxh.consulclient.controller;

import com.alibaba.fastjson.JSONObject;
import com.sxh.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sxh
 * @date 2020/3/13
 */
@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    public ResponseMessage getUser(@PathVariable Integer id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "zhangsan");
        jsonObject.put("id", id);

        logger.info("获取用户信息成功！");

        return new ResponseMessage(jsonObject);
    }
}
