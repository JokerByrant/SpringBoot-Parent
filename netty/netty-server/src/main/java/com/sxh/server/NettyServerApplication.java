package com.sxh.server;

import com.sxh.server.im.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author sxh
 * @date 2020/4/24
 */
@SpringBootApplication
public class NettyServerApplication {
    public static void main(String[] args) {
        //获取application的上下文
        ApplicationContext applicationContext = SpringApplication.run(NettyServerApplication.class, args);

        // 启动netty server
        NettyServer nettyServer = applicationContext.getBean(NettyServer.class);
        nettyServer.start();
    }
}
