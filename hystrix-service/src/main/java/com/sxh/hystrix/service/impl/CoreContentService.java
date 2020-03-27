package com.sxh.hystrix.service.impl;

import com.sxh.hystrix.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sxh
 * @date 2020/3/27
 */
public class CoreContentService implements ContentService {
    private Logger logger = LoggerFactory.getLogger(CoreContentService.class);
    
    @Override
    public void doSomething() {
        logger.info("do some core import things");
    }
}
