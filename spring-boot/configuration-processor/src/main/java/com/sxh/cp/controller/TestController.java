package com.sxh.cp.controller;

import com.sxh.cp.properties.MyProperties1;
import com.sxh.cp.properties.MyProperties2;
import com.sxh.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sxh
 * @date 2020/4/28
 */
@RestController
public class TestController {
    
    @Autowired
    private MyProperties1 myProperties1;
    
    @Autowired
    private MyProperties2 myProperties2;

    @GetMapping("/test1")
    public ResponseMessage test1() {
        return new ResponseMessage(myProperties1);
    }

    @GetMapping("/test2")
    public ResponseMessage test2() {
        return new ResponseMessage(myProperties2);
    }
}
