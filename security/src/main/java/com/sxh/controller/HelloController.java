package com.sxh.controller;

import com.sxh.message.ResponseMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 一池春水倾半城
 * @date 2019/9/6
 */
@RestController
public class HelloController extends BaseController {

    @GetMapping("/hello")
    public ResponseMessage hello() {
        String username = getCurrentUsername();
        return new ResponseMessage("hello", username);
    }
}
