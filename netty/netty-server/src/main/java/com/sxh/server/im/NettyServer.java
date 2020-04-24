package com.sxh.server.im;

import com.sxh.server.im.handler.DiscardServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * netty服务端启动类
 * @author sxh
 * @date 2020/4/24
 */
@Component
public class NettyServer {
    // 启动服务类
    private final ServerBootstrap serverBootstrap = new ServerBootstrap();
    // 开启两个线程池
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workGroup = new NioEventLoopGroup();
    // 本机ip
    private String ip;
    // 启动端口
    private int port = 8888;
    
    private Logger logger = LoggerFactory.getLogger(NettyServer.class);
    
    public void start() {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            logger.error("获取本机Ip信息失败，" + e.getMessage());
        }
        
        serverBootstrap.group(bossGroup,workGroup)
            // 非阻塞模式
            .channel(NioServerSocketChannel.class)
            //连接缓冲池的大小
            .option(ChannelOption.SO_BACKLOG, 1024)
            //设置通道Channel的分配器
            .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            //设置为前端websocket可以连接
            .childHandler(new ChannelInitializer<SocketChannel>(){
    
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
    
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    // 添加抛弃处理器
                    pipeline.addLast("discardHandler", new DiscardServerHandler());
                }
            });

        ChannelFuture channelFuture = null;
        //启动成功标识
        boolean startFlag = false;
        //启动失败时，多次启动，直到启动成功为止
        while(!startFlag) {
            try {
                channelFuture = serverBootstrap.bind(port).sync();
                startFlag = true;
            } catch (InterruptedException e) {
                logger.info("端口号：" + port + "已被占用！");
                port++;
                logger.info("尝试一个新的端口：" + port);

                //重新便规定端口号
                serverBootstrap.localAddress(new InetSocketAddress(port));
            }
        }

        //服务端启动监听事件
        channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) { // 启动成功
                    logger.info("Started Successed:" + ip + ":" + port);
                } else { // 启动失败
                    logger.info("Started Failed:" + ip + ":" + port);
                }
            }
        });

        try {
            // 7 监听通道关闭事件
            // 应用程序会一直等待，直到channel关闭
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("发生其他异常", e);
        } finally {
            // 8 优雅关闭EventLoopGroup，
            // 释放掉所有资源包括创建的线程
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
