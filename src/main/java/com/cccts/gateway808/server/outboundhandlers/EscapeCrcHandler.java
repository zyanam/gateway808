package com.cccts.gateway808.server.outboundhandlers;

import com.cccts.gateway808.ServerLog;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class EscapeCrcHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ServerLog.DEBUG_LOG.trace("转义");
        super.write(ctx, msg, promise);
    }
}
