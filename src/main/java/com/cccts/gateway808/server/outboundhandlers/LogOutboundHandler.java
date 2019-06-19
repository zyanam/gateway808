package com.cccts.gateway808.server.outboundhandlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * 出栈日志，也就是回复的时候的日志
 */
public class LogOutboundHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
        System.out.println("LogOutboundHandler.write");
    }
}


