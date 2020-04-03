package com.sxh.eurekaclient.entity;

import com.sxh.eurekaclient.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sxh
 * @date 2020/3/13
 */
public class User {
    
    Logger logger = LoggerFactory.getLogger(User.class);
    
    {
        logger.info("测试类加载时机！");
    }
    
    private int id;

    private String userName;

    private String password;
    
    private IUserService userService;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    // 此处注入不会生效，因为在类加载阶段User.class尚未加载
    @Autowired
    public void setUserService(IUserService userService) {
        logger.info("此处注入不会生效，因为User.class在类加载阶段并未加载");
        this.userService = userService;
    }
    
    public IUserService getUserService() {
        return this.userService;
    }
}
