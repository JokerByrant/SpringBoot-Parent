package com.sxh.eurekaclient.entity;

import com.sxh.eurekaclient.service.IUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @author sxh
 * @date 2020/3/13
 */
public class User {
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

    
    // @TODO: 此处注入未生效，找出原因
    @Autowired
    public void setUserService(IUserService userService) {
        userService.out();
        this.userService = userService;
    }
    
    public IUserService getUserService() {
        return this.userService;
    }
}
