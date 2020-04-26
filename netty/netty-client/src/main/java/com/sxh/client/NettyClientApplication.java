package com.sxh.client;

import com.sxh.client.im.NettyClient;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoop;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

/**
 * @author sxh
 * @date 2020/4/26
 */
@SpringBootApplication
public class NettyClientApplication implements CommandLineRunner {
    @Autowired
    private NettyClient nettyClient;
    
    private Logger logger = LoggerFactory.getLogger(NettyClientApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(NettyClientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("开始与netty服务器建立连接...");
        // 连接监听事件
        GenericFutureListener<ChannelFuture> connectListener = (ChannelFuture f) -> {
            final EventLoop eventLoop = f.channel().eventLoop();
            if (!f.isSuccess()) { // 连接失败，进行重试
                logger.error("连接失败，在10s后尝试重新连接...");
                // 每10s执行一次
                eventLoop.schedule(() -> nettyClient.doConnect("localhost", 8888), 10, TimeUnit.SECONDS);
            } else { 
                logger.info("连接IM服务器成功！");
                logger.info("" + f.channel().remoteAddress() + f.channel().localAddress());
                
                f.channel().writeAndFlush("1122334455");
            }
        }; 
        
        nettyClient.setConnectListener(connectListener);
        nettyClient.doConnect("localhost", 8888);
    }
}
