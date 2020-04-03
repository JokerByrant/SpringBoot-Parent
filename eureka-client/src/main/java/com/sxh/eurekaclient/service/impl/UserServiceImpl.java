package com.sxh.eurekaclient.service.impl;

import com.sxh.eurekaclient.service.IUserService;
import com.sxh.eurekaclient.service.TestWiredService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sxh
 * @date 2020/4/2
 */
@Service
public class UserServiceImpl implements IUserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    private TestWiredService testWiredService;
    
    @Override
    public void out() {
        System.out.println("hello world!");
        this.testWiredService.test();
    }

    @Autowired
    public void setTestWiredService(TestWiredService testWiredService) {
        logger.info("注入TestWiredService成功！");
        this.testWiredService = testWiredService;
    }
}
