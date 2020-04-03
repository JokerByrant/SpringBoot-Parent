package com.sxh.eurekaclient.service.impl;

import com.sxh.eurekaclient.service.TestWiredService;
import org.springframework.stereotype.Service;

/**
 * @author sxh
 * @date 2020/4/3
 */
@Service
public class TestWiredServiceImpl implements TestWiredService {
    @Override
    public void test() {
        System.out.println("注入成功！");
    }
}
