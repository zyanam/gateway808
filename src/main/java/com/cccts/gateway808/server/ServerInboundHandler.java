package com.cccts.gateway808.server;

import com.cccts.protocol808.Message808;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.time.LocalDateTime;

public class ServerInboundHandler extends SimpleChannelInboundHandler<Message808> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message808 msg) throws Exception {
        System.out.println(LocalDateTime.now() + "业务处理：" + ByteBufUtil.hexDump(msg.originalBuf));
        ctx.fireChannelRead(msg);
    }
}
