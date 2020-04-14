package com.sxh.eurekaclient.controller;

import com.sxh.message.ResponseMessage;
import com.sxh.pojo.UserPojo;
import org.springframework.web.bind.annotation.*;

/**
 * 给gateway网关测试用
 * @author sxh
 * @date 2020/4/14
 */
@RestController
@RequestMapping("/gateway")
public class GatewayController {

    @GetMapping("/{id}")
    public ResponseMessage getUser(@PathVariable Integer id) {
        UserPojo userPojo = new UserPojo(id, "张三", "男");
        return new ResponseMessage("请求成功！", userPojo);
    }

    @GetMapping("/getTest")
    public ResponseMessage getTest() {
        return new ResponseMessage("请求了getTest接口！");
    }

    @PostMapping("/postTest")
    public ResponseMessage postTest() {
        return new ResponseMessage("请求了postTest接口！");
    }

    @GetMapping("/paramTest")
    public ResponseMessage paramTest(@RequestParam("username") String username) {
        return new ResponseMessage("请求了paramTest接口！", username);
    }

    @GetMapping("/test")
    public ResponseMessage test() {
        return new ResponseMessage("请求了test接口！");
    }
}
