package com.cccts.gateway808.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

import java.time.LocalDateTime;

/**
 * 入栈日志，接收时的日志
 */
public class LogInboundHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        if (msg.isReadable()) {
            System.out.println(LocalDateTime.now() + "记录日志:" + ByteBufUtil.hexDump(msg));
            ReferenceCountUtil.retain(msg);
            ctx.fireChannelRead(msg);
        }
    }
}
