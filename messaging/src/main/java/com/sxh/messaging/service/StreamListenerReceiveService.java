package com.sxh.messaging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.stereotype.Service;

/**
 * 用于接收channel端推送的消息
 * @author sxh
 * @date 2020/4/10
 */
@Service
public class StreamListenerReceiveService {
    
    private Logger logger = LoggerFactory.getLogger(StreamListenerReceiveService.class);
    
    @StreamListener(Sink.INPUT) // Sink.INPUT对应配置文件中的spring.cloud.stream.bindings.input
    public void receiveByStreamListener1(String receiveMsg) {
        logger.info("INPUT receiveByStreamListener: " + receiveMsg);
    }
    
    @StreamListener(Source.OUTPUT)
    public void receiveByStreamListener2(String receiveMsg) {
        logger.info("OUTPUT receiveByStreamListener: " + receiveMsg);
    }
}
