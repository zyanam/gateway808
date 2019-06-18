package com.cccts.gateway808.server.inboundhandlers;

import com.cccts.gateway808.ServerLog;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;


/**
 * 入栈日志，接收时的日志
 */
@ChannelHandler.Sharable
public class LogInboundHandler extends SimpleChannelInboundHandler<ByteBuf> {
   private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        if (msg.isReadable()) {

            String hexDump = ByteBufUtil.hexDump(msg);

            ServerLog.DEBUG_LOG.trace(hexDump);

            ServerLog.DATA_LOG.info(hexDump);

            ReferenceCountUtil.retain(msg);
            ctx.fireChannelRead(msg);
        }

        count ++;

        ServerLog.DEBUG_LOG.trace("count=" + count);
    }
}
