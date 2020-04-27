package com.sxh.controller;

import com.sxh.message.ResponseMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 一池春水倾半城
 * @date 2019/9/6
 */
@RestController
@RequestMapping
public class HelloController extends BaseController {

    @GetMapping("/hello")
    public ResponseMessage hello() {
        String username = getCurrentUsername();
        return new ResponseMessage("hello", username);
    }

    @GetMapping("/login")
    public ModelAndView loginPage(String error){
        //前往登录页面
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }
}
