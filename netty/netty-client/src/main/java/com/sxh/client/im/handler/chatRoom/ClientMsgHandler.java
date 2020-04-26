package com.sxh.client.im.handler.chatRoom;

import com.sxh.util.Message;
import com.sxh.util.MessageUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Scanner;

/**
 * 客户端消息接收类,SimpleChannelInboundHandler<I>--->只接收类型为I的消息
 * @author sxh
 * @date 2020/4/26
 */
public class ClientMsgHandler extends SimpleChannelInboundHandler<Message> {
    private Logger logger = LoggerFactory.getLogger(ClientMsgHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        try {
            MessageUtils.printMsg(msg);

            Scanner scanner = new Scanner(System.in);
            System.out.println("please input something: ");
            String input = scanner.nextLine();

            Message message = new Message("Client", new Date(), input);
            ctx.writeAndFlush(message);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
