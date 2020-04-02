package com.sxh.eurekaclient.service.impl;

import com.sxh.eurekaclient.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author sxh
 * @date 2020/4/2
 */
@Service
public class UserServiceImpl implements IUserService {
    @Override
    public void out() {
        System.out.println("hello world!");
    }
}
