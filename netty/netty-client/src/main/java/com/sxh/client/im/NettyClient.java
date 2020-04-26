package com.sxh.client.im;

import com.sxh.client.im.handler.SubscribeResponseHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author sxh
 * @date 2020/4/26
 */
@Component
public class NettyClient {
    private Logger logger = LoggerFactory.getLogger(NettyClient.class);
    
    private Bootstrap bootstrap;
    
    private EventLoopGroup eventLoopGroup;
    
    private GenericFutureListener<ChannelFuture> connectListener;

    public NettyClient() {
        eventLoopGroup = new NioEventLoopGroup();
    }
    
    // 连接处理
    public void doConnect(String host, int port) {
        try {
            bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class)
                    .group(eventLoopGroup)
                    .remoteAddress(host, port)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            // 初始化通道
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    
                    pipeline.addLast("SubscribeResponseHandler", new SubscribeResponseHandler());
                }
            });

            ChannelFuture channelFuture = bootstrap.connect();
            // 添加连接监听事件
            channelFuture.addListener(connectListener);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("客户端连接失败！" + e.getMessage());
        }
    }

    public void setConnectListener(GenericFutureListener<ChannelFuture> connectListener) {
        this.connectListener = connectListener;
    }
}
