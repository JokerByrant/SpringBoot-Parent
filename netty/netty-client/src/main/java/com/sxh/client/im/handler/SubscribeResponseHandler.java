package com.sxh.client.im.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sxh
 * @date 2020/4/26
 */
public class SubscribeResponseHandler extends ChannelInboundHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(SubscribeResponseHandler.class);
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info(msg.toString());
    }
}
