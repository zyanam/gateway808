package com.cccts.gateway808.server.inboundhandlers;

import com.cccts.gateway808.ServerLog;
import com.cccts.protocol808.Message808;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 回复处理器，用于回复终端通用应答
 */
public class ResponseHandler extends SimpleChannelInboundHandler<Message808> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
//        System.out.println(cause.getMessage());

        ServerLog.DEBUG_LOG.error(cause.getMessage());
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message808 msg) throws Exception {
        ServerLog.DEBUG_LOG.trace("回复处理");
        switch (msg.head.msgID){
            case 0x0200:
            {
                ServerLog.DEBUG_LOG.trace("回复0200");
            }
            break;
        }
    }
}
