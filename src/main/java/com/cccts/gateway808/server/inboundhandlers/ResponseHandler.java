package com.cccts.gateway808.server.inboundhandlers;

import com.cccts.protocol808.Message808;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 回复处理器，用于回复终端通用应答
 */
public class ResponseHandler extends SimpleChannelInboundHandler<Message808> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message808 msg) throws Exception {
        System.out.println("回复处理");
    }
}
