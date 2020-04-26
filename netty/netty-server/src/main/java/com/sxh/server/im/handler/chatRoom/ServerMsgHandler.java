package com.sxh.server.im.handler.chatRoom;

import com.sxh.util.Message;
import com.sxh.util.MessageUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Scanner;

/**
 * 消息处理
 * @author sxh
 * @date 2020/4/26
 */
public class ServerMsgHandler extends ChannelInboundHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(ServerMsgHandler.class);
    
    // 客户端初次连接处理
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("用户进入了聊天室...");
        Message message = new Message("Server", new Date(), "Hello, this is a msg from netty server...");
        ByteBuf buffer = ctx.alloc().buffer();
        String content = MessageUtils.encodeMsg(message);
        buffer.writeBytes(content.getBytes());

        ctx.writeAndFlush(buffer);
    }

    // 读取客户端传输的消息并处理
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // 经过了消息解码器TransferMsgHandler的处理，msg已经被转换为而来Message类型
            Message content = (Message) msg;
            MessageUtils.printMsg(content);
            Scanner scanner = new Scanner(System.in);
            System.out.println("please enter something: ");
            String reply = scanner.nextLine();

            Message message = new Message("Server", new Date(), reply);
            ctx.writeAndFlush(message);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
