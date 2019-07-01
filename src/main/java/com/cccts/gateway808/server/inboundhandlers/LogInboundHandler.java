package com.cccts.gateway808.server.inboundhandlers;

import com.cccts.gateway808.ServerLog;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.apache.logging.log4j.core.jmx.Server;


/**
 * 入栈日志，接收时的日志
 */
@ChannelHandler.Sharable
public class LogInboundHandler extends ChannelInboundHandlerAdapter {
   private int count;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
//        ServerLog.DEBUG_LOG.error(cause.getMessage());
//        cause.printStackTrace();
        ServerLog.DEBUG_LOG.info(cause.getMessage());
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof ByteBuf) {
            ByteBuf in = (ByteBuf)msg;
            if (in.isReadable()) {

                String hexDump = ByteBufUtil.hexDump(in);

                ServerLog.DEBUG_LOG.trace(hexDump);

                ServerLog.DATA_LOG.info(hexDump);

//                ReferenceCountUtil.retain(in);
//                in.retain();
                ctx.fireChannelRead(in);
            }else {
                in.release();
            }

            count++;
        }
    }
}
