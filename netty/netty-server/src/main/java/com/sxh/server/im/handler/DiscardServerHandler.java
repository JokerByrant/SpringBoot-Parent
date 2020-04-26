package com.sxh.server.im.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 抛弃处理器
 * @author sxh
 * @date 2020/4/24
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // 继承ChannelInboundHandlerAdapter类，这个类提供了许事件的处理方法
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        // 丢弃收到的数据
        System.out.println(msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        // 出现异常，关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
