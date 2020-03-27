package com.sxh.hystrix.service.impl;

import com.sxh.hystrix.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sxh
 * @date 2020/3/27
 */
public class SimpleContentService implements ContentService {
    private Logger logger = LoggerFactory.getLogger(SimpleContentService.class);

    @Override
    public void doSomething() {
        logger.info("do some simple import things");
    }
}
