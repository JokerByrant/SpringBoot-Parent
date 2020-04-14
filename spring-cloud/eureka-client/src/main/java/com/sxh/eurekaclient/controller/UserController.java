package com.sxh.eurekaclient.controller;

import com.sxh.eurekaclient.entity.User;
import com.sxh.eurekaclient.service.IUserService;
import com.sxh.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    // static类型的元素不能直接使用autowired注入，因为类加载器加载静态变量时，Spring上下文尚未加载
//    private static IUserService userService;

    @Autowired
    private IUserService autowiredUserService;

    //    @Value("${test-config}")
    private String testConfig;

    @GetMapping("/{id}")
    public ResponseMessage getUser(@PathVariable Integer id) {
        User user = new User();
        user.setId(id);
        user.setUserName("张三");
        user.setPassword("123456");

//        test();
//        user.getUserService().out();

        logger.info("获取用户信息成功！");
        logger.info(testConfig);

        return new ResponseMessage(user);
    }

    private static void test() {
        System.out.println("===============start test================");
//        userService.out();
    }

    // 1.构造器方式注入static元素
//    @Autowired
//    private UserController(IUserService userService) {
//        logger.error("注入userService");
//        UserController.userService = userService;
//    }

    // 2. setter方式注入static元素
//    @Autowired
//    public void setUserService(IUserService userService) {
//        logger.error("注入userService");
//        UserController.userService = userService;
//    }

    // 3.通过注入非static元素和@PostConstruct注解结合方式注入static元素。@PostConstruct==>在构造器初始化后调用
//    @PostConstruct 
//    private void beforeInit() {
//        logger.error("注入userService");
//        userService = autowiredUserService;
//    }
}
