package com.sxh.client.im.handler.chatRoom;

import com.sxh.util.Message;
import com.sxh.util.MessageUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

/**
 * 消息编码类，发出消息之前先将消息进行编码
 * @author sxh
 * @date 2020/4/26
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        String content = MessageUtils.encodeMsg(msg);
        buffer.writeBytes(content.getBytes(StandardCharsets.UTF_8));

        ctx.writeAndFlush(buffer);
    }
}
